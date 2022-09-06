# spring-data-jpa-postgres-partitioning
A spring data jpa application made to work with partitioned postgres tables.

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

The working solution is in the *improvement/making-list-partition-work-with-jpa*

> Important point:

1. If you have explicitly defined a primary key, your parition key should either be that primary key or a part of it.
2. In the working solution (improvement/making-list-partition-work-with-jpa) example, I do not have any primary key for the partitioned table orders.
3. Constraints won't work for a partitioned table. It starts to behave like a NoSQL store.
4. JPA requires each entity to have a primary key. If your table does not have a primary key, mark any business key that can serve as the primary key with @Id. Make sure to index that key.
5. With spring-data-jpa query methods, you cannot update the primary key of an entity. If your partition key is part of the primary key, you won't be able to update the partiton key unless you use native queries (This is the problem with the solution in the master branch).

**Range Partitioning [To do]**
