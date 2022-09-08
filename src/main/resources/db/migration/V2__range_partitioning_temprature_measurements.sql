create table temperature_measurements (
  measurement_id varchar,
	measurement_date date,
	city varchar,
	country varchar,
	temperature float
) partition by range(measurement_date);

-- (inclusive, exclusive)
-- Postgres does not automatically creates these partitions. How do you create these partitions on the fly?
-- Don't forget to create index on measurement_date
create table temperature_measurements_y2022m09 partition of temperature_measurements for values from ('2022-09-01') to ('2022-10-01');

create table temperature_measurements_y2022m10 partition of temperature_measurements for values from ('2022-10-01') to ('2022-11-01');
