package com.inventory.platform.order.mapper;



import com.inventory.platform.order.dto.OrderRequest;
import com.inventory.platform.order.dto.OrderResponse;
import com.inventory.platform.order.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private OrderMapper() {
    }

    public static Order toEntity(OrderRequest request) {

        return Order.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();
    }

    public static OrderResponse toResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static List<OrderResponse> toResponse(List<Order> orders) {

        List<OrderResponse> responses = new ArrayList<>();

        for (Order order : orders) {
            responses.add(toResponse(order));
        }

        return responses;
    }
}