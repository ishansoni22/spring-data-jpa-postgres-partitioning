package com.ishan.postgrespartitioning.domain;

import com.ishan.postgrespartitioning.domain.Order.ArchiveStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersJpaRepository extends JpaRepository<Order, String> {

  Optional<Order> findByOrderIdAndArchiveStatus(String orderId, ArchiveStatus archiveStatus);

}
