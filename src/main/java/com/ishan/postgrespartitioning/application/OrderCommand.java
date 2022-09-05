package com.ishan.postgrespartitioning.application;

import com.ishan.postgrespartitioning.domain.Order.OrderAction;
import java.util.Objects;
import lombok.Getter;

@Getter
public class OrderCommand {

  private String orderId;
  private OrderAction action;

  public OrderCommand(String orderId, OrderAction action) {
    this.orderId = Objects.requireNonNull(orderId);
    this.action = Objects.requireNonNull(action);
  }

}
