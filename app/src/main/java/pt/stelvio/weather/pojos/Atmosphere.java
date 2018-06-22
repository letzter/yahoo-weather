package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/16/2018.
 */

public class Atmosphere {
    @Getter @Setter @Expose @SerializedName("humidity") private String humidity;
    @Getter @Setter @Expose @SerializedName("pressure") private String pressure;
    @Getter @Setter @Expose @SerializedName("rising") private String rising;
    @Getter @Setter @Expose @SerializedName("visibility") private String visibility;
}
