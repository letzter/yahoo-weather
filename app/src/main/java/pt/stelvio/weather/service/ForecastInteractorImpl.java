package pt.stelvio.weather.service;

import pt.stelvio.weather.interfaces.ForecastInteractor;
import pt.stelvio.weather.pojos.ForecastResult;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by stelv on 6/14/2018.
 */

public class ForecastInteractorImpl implements ForecastInteractor {

    private ForecastInteractor service;

    public ForecastInteractorImpl() {
        // Configure Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        // Create the API Service
        service = retrofit.create(ForecastInteractor.class);
    }

    @Override
    public Observable<ForecastResult> getForecast() {
        return service.getForecast();
    }
}
