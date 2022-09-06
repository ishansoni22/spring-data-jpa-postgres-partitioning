package com.ishan.postgrespartitioning.port.adapter.http;

import com.ishan.postgrespartitioning.application.OrderApplicationService;
import com.ishan.postgrespartitioning.application.OrderCommand;
import com.ishan.postgrespartitioning.domain.Order;
import com.ishan.postgrespartitioning.domain.Order.ArchiveStatus;
import com.ishan.postgrespartitioning.domain.Order.OrderAction;
import com.ishan.postgrespartitioning.domain.OrdersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/orders")
public class OrderController {

  @Autowired
  private OrdersJpaRepository ordersJpaRepository;

  @Autowired
  private OrderApplicationService orderApplicationService;

  @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Order> getOrder(@PathVariable("orderId") String orderId) {
    return ordersJpaRepository.findByOrderIdAndArchiveStatus(
        orderId, ArchiveStatus.ACTIVE
    ).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping(value = "/{orderId}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Order> performActionOnOrder(
      @PathVariable("orderId") String orderId,
      @RequestBody String action) {
    return ResponseEntity.ok(orderApplicationService
        .performActionOnOrder(new OrderCommand(orderId, OrderAction.valueOf(action))));
  }

}
