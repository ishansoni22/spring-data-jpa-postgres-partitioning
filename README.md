# spring-data-jpa-postgres-partitioning
Spring data jpa application made to work with partitioned postgres tables.

**List Partitioning [WIP]**

Created the order entity for an e-commerce application.
Created two entities (one partitioned and the other un-partitioned) representing the same concept (order). 
Benchmark performance metrics for both *[To do]*.

The unpartitioned order table will contain all orders.

The partitioned orders table will have two partitions:
- Active partition table. This table will contain all orders that have not been delivered.
- Archived partition table. This table will contain all orders that have been delivered.

The idea is that all active orders will live in a single table. Once an order is delivered, it will be moved to the archived table. This will ensure that the active orders table contains very less data at any point in time compared to the archived table which may improve query performance when working with active orders.

This will also help in writing ETL processes over the archived table (Data warehousing).

**Range Partitioning [To do]**
