package com.ishan.postgrespartitioning.domain.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnPartitionedOrdersJpaRepository
    extends JpaRepository<UnPartitionedOrder, String> {

}
