package com.inventory.platform.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {

    private Long orderId;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private BigDecimal totalPrice;

}