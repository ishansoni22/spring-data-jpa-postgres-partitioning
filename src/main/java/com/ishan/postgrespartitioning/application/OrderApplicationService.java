package com.ishan.postgrespartitioning.application;

import com.ishan.postgrespartitioning.domain.Order;
import com.ishan.postgrespartitioning.domain.Order.ArchiveStatus;
import com.ishan.postgrespartitioning.domain.OrdersJpaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderApplicationService {

  @Autowired
  private OrdersJpaRepository ordersJpaRepository;

  @Transactional
  public Order performActionOnOrder(OrderCommand orderCommand) {
    Optional<Order> orderOptional = ordersJpaRepository
        .findByOrderIdAndArchiveStatus(orderCommand.getOrderId(), ArchiveStatus.ACTIVE);

    if (orderOptional.isPresent()) {
      Order order = orderOptional.get();
      order.performAction(orderCommand.getAction());
      ordersJpaRepository.save(order);
    }

    return orderOptional.orElse(null);
  }

}
