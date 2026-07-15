package com.inventory.platform.order.dto;



import com.inventory.platform.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private BigDecimal totalPrice;

    private OrderStatus orderStatus;

    private LocalDateTime createdAt;
}