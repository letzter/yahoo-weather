package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/16/2018.
 */

public class ForecastQueryResults {
    @Getter @Setter @Expose @SerializedName("channel") private ForecastChannels forecastChannels;
}
