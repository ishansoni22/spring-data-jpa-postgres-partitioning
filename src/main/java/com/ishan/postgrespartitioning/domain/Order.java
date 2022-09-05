package com.ishan.postgrespartitioning.domain;

import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  @EmbeddedId
  private OrderId id;

  private String userId;

  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  public Order performAction(OrderAction action) {
    if (OrderAction.SHIPPED.equals(action)) {
      this.status = OrderStatus.SHIPPED;
    } else if (OrderAction.DELIVERED.equals(action)) {
      this.status = OrderStatus.DELIVERED;

      //Todo

      // Doesn't work - org.hibernate.HibernateException: identifier of an instance of
      // com.ishan.postgrespartitioning.domain.Order was altered from
      // com.ishan.postgrespartitioning.domain.OrderId@7e2222ed to
      // com.ishan.postgrespartitioning.domain.OrderId@3ec65042
      // this.id = new OrderId(this.id.getId(), ArchiveStatus.ARCHIVED);

      // Doesn't work - org.hibernate.StaleStateException:
      // Batch update returned unexpected row count from update [0]; actual row count: 0; expected: 1
      this.id.markArchived();

    } else {
      //Throw Exception
    }
    return this;
  }

}
