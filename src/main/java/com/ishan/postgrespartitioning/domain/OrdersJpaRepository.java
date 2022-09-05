package com.ishan.postgrespartitioning.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// When using spring data jpa methods, you'll have to pass the primary key (here OrderId)
// to find an order. By default, the orderId is designed to work with the orders_active table.
// By doing this, i.e by mentioning the partition key in the where clause,
// the db will simply have search for this order in the orders_active table
// and will completely skip the orders_archived table thus improving performance
public interface OrdersJpaRepository extends JpaRepository<Order, OrderId> {

}
