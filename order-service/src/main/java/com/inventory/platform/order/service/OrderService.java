package com.inventory.platform.order.service;



import com.inventory.platform.order.dto.OrderRequest;
import com.inventory.platform.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getAllOrders();

    OrderResponse updateOrder(Long id, OrderRequest request);

    void deleteOrder(Long id);
}