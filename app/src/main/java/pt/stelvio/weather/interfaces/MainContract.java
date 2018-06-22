package pt.stelvio.weather.interfaces;

import pt.stelvio.weather.pojos.ForecastChannels;

/**
 * Created by stelv on 6/21/2018.
 */

public interface MainContract {

    interface ForecastView {
        void onFetchDataStarted();

        void onFetchDataCompleted();

        void updateUi(ForecastChannels forecast);

        void onFetchDataError(Throwable e);
    }

    interface Presenter {
        void requestData();

        void subscribe();

        void unsubscribe();

        void onDestroy();
    }
}
