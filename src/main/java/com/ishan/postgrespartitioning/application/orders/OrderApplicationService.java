package com.ishan.postgrespartitioning.application.orders;

import com.ishan.postgrespartitioning.domain.orders.Order;
import com.ishan.postgrespartitioning.domain.orders.Order.ArchiveStatus;
import com.ishan.postgrespartitioning.domain.orders.OrdersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderApplicationService {

  @Autowired
  private OrdersJpaRepository ordersJpaRepository;

  @Transactional
  public Order performActionOnOrder(PerformActionOnOrderCommand performActionOnOrderCommand) {
    return ordersJpaRepository.findByOrderIdAndArchiveStatus(
        performActionOnOrderCommand.getOrderId(),
        ArchiveStatus.ACTIVE
    ).map(order -> {
      order.performAction(performActionOnOrderCommand.getAction());
      return order;
    }).map(order -> ordersJpaRepository.save(order)).orElseThrow();
  }

}
