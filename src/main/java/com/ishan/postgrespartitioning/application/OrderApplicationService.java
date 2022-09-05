package com.ishan.postgrespartitioning.application;

import com.ishan.postgrespartitioning.domain.Order;
import com.ishan.postgrespartitioning.domain.OrderId;
import com.ishan.postgrespartitioning.domain.OrdersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderApplicationService {

  @Autowired
  private OrdersJpaRepository ordersJpaRepository;

  @Transactional
  public Order performActionOnOrder(OrderCommand orderCommand) {
    return ordersJpaRepository.findById(new OrderId(orderCommand.getOrderId()))
        .map(order -> order.performAction(orderCommand.getAction()))
        .map(order -> ordersJpaRepository.save(order))
        .orElse(null);
  }

}
