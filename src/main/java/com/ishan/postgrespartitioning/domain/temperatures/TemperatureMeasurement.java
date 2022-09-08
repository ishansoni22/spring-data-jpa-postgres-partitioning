package com.ishan.postgrespartitioning.domain.temperatures;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "temperature_measurements")
@Getter
@Setter
public class TemperatureMeasurement {

  @Id
  private String measurementId;

  private LocalDate measurementDate;

  private String city;

  private String country;

  private Double temperature;

}
