package com.ishan.postgrespartitioning.application.orders;

import com.ishan.postgrespartitioning.domain.orders.Order.OrderAction;
import java.util.Objects;
import lombok.Getter;

@Getter
public class PerformActionOnOrderCommand {

  private String orderId;
  private OrderAction action;

  public PerformActionOnOrderCommand(String orderId, OrderAction action) {
    this.orderId = Objects.requireNonNull(orderId);
    this.action = Objects.requireNonNull(action);
  }

}
