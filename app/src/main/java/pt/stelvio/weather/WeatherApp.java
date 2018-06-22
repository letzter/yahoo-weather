package pt.stelvio.weather;

import android.app.Application;

import pt.stelvio.weather.components.AppComponent;
import pt.stelvio.weather.components.DaggerAppComponent;

/**
 * Created by stelv on 6/17/2018.
 */

public class WeatherApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
