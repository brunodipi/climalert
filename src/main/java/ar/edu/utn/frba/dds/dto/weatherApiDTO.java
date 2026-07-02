package ar.edu.utn.frba.dds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class weatherApiDTO {
  private Current current;

  @Data
  public static class Current {
    @JsonProperty("temp_c")
    private double tempC;

    @JsonProperty("wind_kph")
    private double windKph;
  }
}