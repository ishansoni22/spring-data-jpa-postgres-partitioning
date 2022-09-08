# spring-data-jpa-postgres-partitioning
A spring data jpa application made to work with partitioned postgres tables.

**List Partitioning**

Created the order entity for an e-commerce application.
Created two entities (one partitioned and the other un-partitioned) representing the same concept (order).
Benchmark performance metrics for both *[To do]*.

The unpartitioned order table will contain all orders.

The partitioned orders table will have two partitions:
- Active partition table. This table will contain all orders that have not been delivered.
- Archived partition table. This table will contain all orders that have been delivered.

The idea is that all active orders will live in a single table. Once an order is delivered, it will be moved to the archived table. This will ensure that the active orders table contains very less data at any point in time compared to the archived table which may improve query performance when working with active orders. *Alternatively, can we add a boolean archived flag and create a bitmap index on it to speed things up instead of partitioning?*

**Range Partitioning**

Created a temperature measurement entity that captures the maximum temperature of a city for a day (measurement_date).

The temperature entity is partitioned based on the measurement_date. The partitons are created monthly.
The idea is that all temperature readings for a month will stay in a single table. Since you'll mostly be interested in temperature readings for today or yesterday, you'll fire the following query: select * from temperature_measurements where measurement_date = TODAY/YESTERDAY. Since you've mentioned the partition key in the where clause, postgres will only query that partition thus improving performance.

It'll also be easy to drop entire partitions using this approach when they become old.

> Important points to note:

1. If you have explicitly defined a primary key, your parition key should either be that primary key or a part of it.
2. In these examples, I do not have any primary key for the partitioned tables.
3. Constraints won't work as a whole for a partitioned table. It starts to behave like a NoSQL store. You can however create constraints for a particular partitioned table, but these constraints will only work for that partition. 
4. JPA requires each entity to have a primary key. If your table does not have a primary key, mark any business key that can serve as the primary key with @Id. Make sure to index that key.
5. With spring-data-jpa query methods, you cannot update the primary key of an entity. If your partition key is part of the primary key, you won't be able to update the partiton key unless you use native queries.
