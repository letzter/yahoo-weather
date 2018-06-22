package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/17/2018.
 */

public class Location {
    @Getter @Setter @Expose @SerializedName("city") private String city;
    @Getter @Setter @Expose @SerializedName("country") private String country;
    @Getter @Setter @Expose @SerializedName("region") private String region;
}
