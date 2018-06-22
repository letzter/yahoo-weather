package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/16/2018.
 */

public class ForecastChannels {
    @Getter @Setter @Expose @SerializedName("astronomy") private Astronomy astronomy;
    @Getter @Setter @Expose @SerializedName("atmosphere") private Atmosphere atmosphere;
    @Getter @Setter @Expose @SerializedName("description") private String description;
    @Getter @Setter @Expose @SerializedName("image") private ForecastImage image;
    @Getter @Setter @Expose @SerializedName("item") private ForecastItem item;
    @Getter @Setter @Expose @SerializedName("location") private Location location;
    @Getter @Setter @Expose @SerializedName("units") private Units units;
    @Getter @Setter @Expose @SerializedName("wind") private Wind wind;
}
