package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/17/2018.
 */

public class Wind {
    @Getter @Setter @Expose @SerializedName("chill") private String chill;
    @Getter @Setter @Expose @SerializedName("direction") private String direction;
    @Getter @Setter @Expose @SerializedName("speed") private String speed;
}
