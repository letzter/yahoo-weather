package pt.stelvio.weather.pojos;

import lombok.Getter;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stelv on 6/17/2018.
 */

public class ForecastCondition {
    @Getter @Setter @Expose @SerializedName("code") private String code;
    @Getter @Setter @Expose @SerializedName("date") private String date;
    @Getter @Setter @Expose @SerializedName("temp") private String temp;
    @Getter @Setter @Expose @SerializedName("text") private String text;
    @Getter @Setter @Expose @SerializedName("day") private String day;
    @Getter @Setter @Expose @SerializedName("high") private String high;
    @Getter @Setter @Expose @SerializedName("low") private String low;
}
