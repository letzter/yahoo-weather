package pt.stelvio.weather.interfaces;


import pt.stelvio.weather.pojos.ForecastResult;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by stelv on 6/13/2018.
 */

public interface ForecastInteractor {
    @GET("/v1/public/yql?q=select%20*%20from%20weather.forecast" +
            "%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)" +
            "%20where%20text%3D%22dallas%2C%20tx%22)&format=json")
    Observable<ForecastResult> getForecast();
}
