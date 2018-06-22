package pt.stelvio.weather.components;

import dagger.Component;
import pt.stelvio.weather.modules.ForecastModule;
import pt.stelvio.weather.activities.MainActivity;

/**
 * Created by stelv on 6/17/2018.
 */

@Component(modules = {ForecastModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
