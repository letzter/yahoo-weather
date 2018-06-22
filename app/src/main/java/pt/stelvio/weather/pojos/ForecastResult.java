package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by stelv on 6/14/2018.
 */

public class ForecastResult {
    @Getter @Setter @Expose @SerializedName("query") private ForecastQuery query;
}
