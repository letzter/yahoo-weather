package pt.stelvio.weather.modules;

import dagger.Module;
import dagger.Provides;
import pt.stelvio.weather.interfaces.ForecastInteractor;
import pt.stelvio.weather.service.ForecastInteractorImpl;

/**
 * Created by stelv on 6/18/2018.
 */

@Module
public class ForecastModule {
    @Provides
    public ForecastInteractor providesForecastInteractor() {
        return new ForecastInteractorImpl();
    }
}
