package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stelv on 6/16/2018.
 */

public class ForecastItem {
    @Getter @Setter @Expose @SerializedName("condition") private ForecastCondition condition;
    @Getter @Setter @Expose @SerializedName("forecast") private List<ForecastCondition> forecastConditions;
    @Getter @Setter @Expose @SerializedName("lat") private String latitude;
    @Getter @Setter @Expose @SerializedName("long") private String longitude;
}
