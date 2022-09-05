package com.ishan.postgrespartitioning.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "old_orders")
public class UnPartitionedOrder {

  @Id
  private String id;

  private String userId;

  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

}
