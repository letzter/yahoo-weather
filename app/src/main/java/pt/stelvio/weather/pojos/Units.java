package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/17/2018.
 */

public class Units {
    @Getter @Setter @Expose @SerializedName("distance") private String distance;
    @Getter @Setter @Expose @SerializedName("pressure") private String pressure;
    @Getter @Setter @Expose @SerializedName("speed") private String speed;
    @Getter @Setter @Expose @SerializedName("temperature") private String temperature;
}
