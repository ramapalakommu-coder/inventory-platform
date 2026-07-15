package com.inventory.platform.order.serviceimpl;

import com.inventory.platform.order.client.ProductClient;
import com.inventory.platform.order.client.UserClient;
import com.inventory.platform.order.client.dto.ProductResponse;
import com.inventory.platform.order.dto.OrderRequest;
import com.inventory.platform.order.dto.OrderResponse;
import com.inventory.platform.order.entity.Order;
import com.inventory.platform.order.entity.OrderStatus;
import com.inventory.platform.order.event.OrderCreatedEvent;
import com.inventory.platform.order.exception.OrderNotFoundException;
import com.inventory.platform.order.exception.UserNotFoundException;
import com.inventory.platform.order.kafka.producer.OrderProducer;
import com.inventory.platform.order.mapper.OrderMapper;
import com.inventory.platform.order.repository.OrderRepository;
import com.inventory.platform.order.service.OrderService;
import com.inventory.platform.order.service.external.ProductServiceCaller;
import feign.FeignException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductServiceCaller productServiceCaller;
    private final OrderProducer orderProducer;


    @Override
    public OrderResponse createOrder(OrderRequest request) {

        try {
            userClient.getUserById(request.getUserId());
        } catch (FeignException.NotFound ex) {
            throw new UserNotFoundException(
                    "User not found with id : " + request.getUserId());
        }

        ProductResponse product =
                productServiceCaller.getProduct(request.getProductId());

        Order order = OrderMapper.toEntity(request);

        BigDecimal totalPrice = product.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CREATED);

        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent event =
                OrderCreatedEvent.builder()
                        .orderId(savedOrder.getId())
                        .userId(savedOrder.getUserId())
                        .productId(savedOrder.getProductId())
                        .quantity(savedOrder.getQuantity())
                        .totalPrice(savedOrder.getTotalPrice())
                        .build();
        orderProducer.publish(event);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return OrderMapper.toResponse(orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found with id : " + id)));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return OrderMapper.toResponse(orderRepository.findAll());
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found with id : " + id));

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        BigDecimal productPrice = new BigDecimal("500");

        BigDecimal totalPrice =
                productPrice.multiply(
                        BigDecimal.valueOf(request.getQuantity()));

        order.setTotalPrice(totalPrice);

        Order updatedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);

    }
}
