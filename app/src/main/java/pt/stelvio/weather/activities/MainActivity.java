package pt.stelvio.weather.activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import pt.stelvio.weather.R;
import pt.stelvio.weather.WeatherApp;
import pt.stelvio.weather.adapters.ForecastDayAdapter;
import pt.stelvio.weather.connectivity.NetworkSchedulerService;
import pt.stelvio.weather.interfaces.MainContract.ForecastView;
import pt.stelvio.weather.pojos.ForecastChannels;
import pt.stelvio.weather.pojos.ForecastCondition;
import pt.stelvio.weather.presenters.ForecastPresenter;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ForecastView {

    private ForecastPresenter presenter;
    private ViewSwitcher viewSwitcher;
    private TextView city;
    private TextView status;
    private TextView currentTemperature;
    private TextView currentWeekday;
    private TextView currentWeekdayMaxTemp;
    private TextView currentWeekdayMinTemp;
    private RecyclerView recyclerView;
    private ForecastDayAdapter forecastListAdapter;
    private TextView forecastDescription;
    private TextView forecastSunrise;
    private TextView forecastSunset;
    private TextView forecastHumidity;
    private TextView forecastWind;
    private TextView forecastPressure;
    private TextView forecastVisibility;
    private LinearLayout forecastMainView;
    private ProgressBar progressRing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);

        ((WeatherApp) getApplication()).getAppComponent().inject(this);
        presenter.setSchedulers(Schedulers.io(), AndroidSchedulers.mainThread());
        presenter.bind(this);

        bindViews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastListAdapter = new ForecastDayAdapter(new ArrayList<ForecastCondition>());
        recyclerView.setAdapter(forecastListAdapter);

        scheduleJob();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        } catch (EventBusException e) {
            e.printStackTrace();
        }
        setDisplayedView();
    }

    @Override
    protected void onPause() {
        try {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        } catch (EventBusException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    private void scheduleJob() {
        JobInfo myJob = new JobInfo.Builder(0, new ComponentName(this, NetworkSchedulerService.class))
                .setRequiresCharging(true)
                .setMinimumLatency(1000)
                .setOverrideDeadline(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.schedule(myJob);
        }
    }

    private void setDisplayedView() {
        if (isConnected()) {
            progressRing.setVisibility(View.VISIBLE);
            forecastMainView.setVisibility(View.GONE);
            viewSwitcher.setDisplayedChild(0);
            presenter.requestData();
        } else {
            viewSwitcher.setDisplayedChild(1);
        }
    }

    private void bindViews() {
        viewSwitcher = findViewById(R.id.viewSwitcherActivityMain);
        progressRing = findViewById(R.id.progressActivityMain);
        forecastMainView = findViewById(R.id.forecastActivityMain);
        city = findViewById(R.id.cityActivityMain);
        status = findViewById(R.id.statusActivityMain);
        currentTemperature = findViewById(R.id.currentTemperatureActivityMain);
        currentWeekday = findViewById(R.id.forecast_weekday);
        currentWeekdayMaxTemp = findViewById(R.id.forecast_max_temp);
        currentWeekdayMinTemp = findViewById(R.id.forecast_min_temp);
        recyclerView = findViewById(R.id.forecastRecyclerView);
        forecastDescription = findViewById(R.id.forecast_description);
        forecastSunrise = findViewById(R.id.forecast_sunrise);
        forecastSunset = findViewById(R.id.forecast_sunset);
        forecastHumidity = findViewById(R.id.forecast_humidity);
        forecastWind = findViewById(R.id.forecast_wind);
        forecastPressure = findViewById(R.id.forecast_pressure);
        forecastVisibility = findViewById(R.id.forecast_visibility);
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Inject
    public void setPresenter(ForecastPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onFetchDataStarted() {

    }

    @Override
    public void onFetchDataCompleted() {

    }

    @Override
    public void updateUi(ForecastChannels forecast) {
        StringBuilder cityName = new StringBuilder();
        cityName.append(forecast.getLocation().getCity());
        cityName.append(", ");
        cityName.append(forecast.getLocation().getRegion());

        String tempUnit = forecast.getUnits().getTemperature();
        StringBuilder temp = new StringBuilder();
        temp.append(forecast.getItem().getCondition().getTemp());
        temp.append(getResources().getString(R.string.degree_symbol));
        temp.append(tempUnit);

        city.setText(cityName);
        status.setText(forecast.getItem().getCondition().getText());
        currentTemperature.setText(temp);
        currentWeekday.setText(getResources().getString(R.string.today));
        currentWeekdayMaxTemp.setText(forecast.getItem().getForecastConditions().get(0).getHigh());
        currentWeekdayMinTemp.setText(forecast.getItem().getForecastConditions().get(0).getLow());
        forecastListAdapter.replaceWith(forecast.getItem().getForecastConditions());
        forecastListAdapter.removeItem(0);
        forecastDescription.setText(forecast.getDescription());
        forecastSunrise.setText(forecast.getAstronomy().getSunrise());
        forecastSunset.setText(forecast.getAstronomy().getSunset());
        forecastHumidity.setText(forecast.getAtmosphere().getHumidity());
        forecastWind.setText(forecast.getWind().getSpeed());
        forecastPressure.setText(forecast.getAtmosphere().getPressure());
        forecastVisibility.setText(forecast.getAtmosphere().getVisibility());
        progressRing.setVisibility(View.GONE);
        forecastMainView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFetchDataError(Throwable e) {

    }

    @SuppressWarnings("unused") @Subscribe
    public void connectionChangedCallback(@NonNull final String callback) {
        if (callback.equals(NetworkSchedulerService.CONNECTION_CHANGE)){
            setDisplayedView();
        }
    }
}
