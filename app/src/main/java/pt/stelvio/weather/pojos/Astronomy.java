package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/16/2018.
 */

public class Astronomy {
    @Getter @Setter @Expose @SerializedName("sunrise") private String sunrise;
    @Getter @Setter @Expose @SerializedName("sunset") private String sunset;
}
