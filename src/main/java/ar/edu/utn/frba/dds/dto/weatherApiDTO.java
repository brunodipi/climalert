package ar.edu.utn.frba.dds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class weatherApiDTO {
  private Current current;

  @Data
  public static class Condition {
    private String text;
    private String icon;
    private int code;
  }

  @Data
  public static class Current {
    @JsonProperty("last_updated_epoch")
    private long lastUpdatedEpoch;
    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("temp_c")
    private double tempC;
    @JsonProperty("temp_f")
    private double tempF;

    @JsonProperty("is_day")
    private int isDay;

    private Condition condition;

    @JsonProperty("wind_mph")
    private double windMph;
    @JsonProperty("wind_kph")
    private double windKph;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_dir")
    private String windDir;

    @JsonProperty("pressure_mb")
    private double pressureMb;
    @JsonProperty("pressure_in")
    private double pressureIn;

    @JsonProperty("precip_mm")
    private double precipMm;
    @JsonProperty("precip_in")
    private double precipIn;

    private double humidity;
    private int cloud;

    @JsonProperty("feelslike_c")
    private double feelslikeC;
    @JsonProperty("feelslike_f")
    private double feelslikeF;

    @JsonProperty("windchill_c")
    private double windchillC;
    @JsonProperty("windchill_f")
    private double windchillF;

    @JsonProperty("heatindex_c")
    private double heatindexC;
    @JsonProperty("heatindex_f")
    private double heatindexF;

    @JsonProperty("dewpoint_c")
    private double dewpointC;
    @JsonProperty("dewpoint_f")
    private double dewpointF;

    @JsonProperty("vis_km")
    private double visKm;
    @JsonProperty("vis_miles")
    private double visMiles;

    private double uv;

    @JsonProperty("gust_mph")
    private double gustMph;
    @JsonProperty("gust_kph")
    private double gustKph;

    @JsonProperty("will_it_rain")
    private int willItRain;
    @JsonProperty("chance_of_rain")
    private int chanceOfRain;
    @JsonProperty("will_it_snow")
    private int willItSnow;
    @JsonProperty("chance_of_snow")
    private int chanceOfSnow;

    @JsonProperty("wetbulb_c")
    private double wetbulbC;
    @JsonProperty("wetbulb_f")
    private double wetbulbF;
    @JsonProperty("short_rad")
    private double shortRad;
    @JsonProperty("diff_rad")
    private double diffRad;
    private double dni;
    private double gti;
  }
}