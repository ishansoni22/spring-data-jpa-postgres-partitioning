package com.ishan.postgrespartitioning.domain.orders;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
//Your application does not care about partitions. It works with the parent table
public class Order {

  public enum OrderAction {
    SHIPPED, DELIVERED;
  }

  public enum ArchiveStatus {
    ACTIVE, ARCHIVED;
  }

  @Id
  private String orderId;

  private String userId;

  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Enumerated(EnumType.STRING)
  private ArchiveStatus archiveStatus;

  public void performAction(OrderAction action) {
    if (OrderAction.SHIPPED.equals(action)) {
      this.status = OrderStatus.SHIPPED;
    } else if (OrderAction.DELIVERED.equals(action)) {
      this.status = OrderStatus.DELIVERED;
      this.archiveStatus = ArchiveStatus.ARCHIVED;

    } else {
      //Throw Exception
    }
  }

}
