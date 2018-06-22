package pt.stelvio.weather.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import pt.stelvio.weather.interfaces.ForecastInteractor;
import pt.stelvio.weather.interfaces.MainContract;
import pt.stelvio.weather.interfaces.MainContract.ForecastView;
import pt.stelvio.weather.pojos.ForecastResult;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by stelv on 6/14/2018.
 */

public class ForecastPresenter implements MainContract.Presenter{
    @NonNull
    private ForecastInteractor interactor;

    @NonNull
    private Scheduler backgroundScheduler;

    @NonNull
    private Scheduler mainScheduler;

    @NonNull
    private CompositeSubscription subscriptions;

    private ForecastView view;

    @Inject
    public ForecastPresenter(@NonNull ForecastInteractor interactor) {
        this.interactor = interactor;
        subscriptions = new CompositeSubscription();
    }

    public void bind(ForecastView view) {
        this.view = view;
    }

    public void setSchedulers(@NonNull Scheduler backgroundScheduler,
                              @NonNull Scheduler mainScheduler) {
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
    }

    public void unbind() {
        view = null;
    }

    public void requestData() {
        view.onFetchDataStarted();
        subscriptions.clear();

        Subscription subscription = interactor.getForecast()
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<ForecastResult>() {
                    @Override
                    public void onCompleted() {
                        view.onFetchDataCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFetchDataError(e);
                    }

                    @Override
                    public void onNext(ForecastResult forecastResult) {
                        view.updateUi(forecastResult.getQuery().getQueryResults().getForecastChannels());
                    }
                });

        subscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        requestData();
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
