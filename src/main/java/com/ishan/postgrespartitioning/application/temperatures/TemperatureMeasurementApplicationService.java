package com.ishan.postgrespartitioning.application.temperatures;

import com.ishan.postgrespartitioning.domain.temperatures.TemperatureMeasurement;
import com.ishan.postgrespartitioning.domain.temperatures.TemperatureMeasurementJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemperatureMeasurementApplicationService {

  @Autowired
  private TemperatureMeasurementJpaRepository temperatureMeasurementJpaRepository;

  @Transactional
  public TemperatureMeasurement updateTemperatureMeasurement(
      UpdateTemperatureMeasurementCommand updateTemperatureMeasurementCommand) {

    return temperatureMeasurementJpaRepository
        .findByMeasurementId(updateTemperatureMeasurementCommand.getMeasurementId())
        .map(temperature -> {
          temperature.setTemperature(updateTemperatureMeasurementCommand.getNewTemperature());
          return temperature;
        }).map(temperature -> temperatureMeasurementJpaRepository.save(temperature)).orElseThrow();

  }

}
