package com.ishan.postgrespartitioning.application.temperatures;

import java.util.Objects;
import lombok.Getter;

@Getter
public class UpdateTemperatureMeasurementCommand {

  private String measurementId;
  private Double newTemperature;

  public UpdateTemperatureMeasurementCommand(String measurementId, Double newTemperature) {
    this.measurementId = Objects.requireNonNull(measurementId);
    this.newTemperature = Objects.requireNonNull(newTemperature);

  }

}
