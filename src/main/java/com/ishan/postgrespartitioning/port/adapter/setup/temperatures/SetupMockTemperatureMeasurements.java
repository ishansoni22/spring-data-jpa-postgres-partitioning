package com.ishan.postgrespartitioning.port.adapter.setup.temperatures;

import com.ishan.postgrespartitioning.domain.temperatures.TemperatureMeasurement;
import com.ishan.postgrespartitioning.domain.temperatures.TemperatureMeasurementJpaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Slf4j
public class SetupMockTemperatureMeasurements {

  @Autowired
  private TemperatureMeasurementJpaRepository temperatureJpaRepository;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @PostConstruct
  @Transactional
  public void setUp() {

    int total = 1000;
    int batchSize = 100;
    int batch = 1;

    List<TemperatureMeasurement> temperatures = new ArrayList<>();

    List<LocalDate> dates = List.of(
        LocalDate.parse("2022-09-01", DATE_FORMATTER),
        LocalDate.parse("2022-09-02", DATE_FORMATTER),
        LocalDate.parse("2022-09-03", DATE_FORMATTER),
        LocalDate.parse("2022-09-04", DATE_FORMATTER),
        LocalDate.parse("2022-09-05", DATE_FORMATTER),
        LocalDate.parse("2022-09-06", DATE_FORMATTER),
        LocalDate.parse("2022-09-07", DATE_FORMATTER),
        LocalDate.parse("2022-10-03", DATE_FORMATTER),
        LocalDate.parse("2022-10-04", DATE_FORMATTER),
        LocalDate.parse("2022-10-05", DATE_FORMATTER)
    );

    List<String> cities = List.of(
        "pune",
        "jalandhar",
        "hoshiarpur",
        "mumbai",
        "bangalore",
        "amritsar",
        "noida",
        "gurgaon",
        "nagpur",
        "delhi"
    );

    for (int i = 1; i <= total; i++) {

      Random random = new Random(i);

      Double temperature = random.nextDouble() * 100;

      TemperatureMeasurement temp = new TemperatureMeasurement();
      temp.setMeasurementId(UUID.randomUUID().toString());
      temp.setCity(cities.get(random.nextInt(cities.size())));
      temp.setCountry("india");
      temp.setTemperature(temperature);
      temp.setMeasurementDate(dates.get(random.nextInt(dates.size())));

      temperatures.add(temp);

      if (i % batchSize == 0) {
        log.info("Processing temperatures batch " + batch
            + ". Progress = " +  ((batch * batchSize) / (double) total) * 100.0d + " %");

        temperatureJpaRepository.saveAll(temperatures);
        temperatures.clear();
        ++batch;
      }
    }

  }

}
