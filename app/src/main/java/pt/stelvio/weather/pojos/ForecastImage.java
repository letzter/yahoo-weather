package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/16/2018.
 */

public class ForecastImage {
    @Getter @Setter @Expose @SerializedName("height") private String height;
    @Getter @Setter @Expose @SerializedName("width") private String width;
    @Getter @Setter @Expose @SerializedName("url") private String url;
}
