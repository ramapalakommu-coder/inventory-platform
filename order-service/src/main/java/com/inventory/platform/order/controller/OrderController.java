package com.inventory.platform.order.controller;


import com.inventory.platform.order.dto.OrderRequest;
import com.inventory.platform.order.dto.OrderResponse;
import com.inventory.platform.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "Operations related to Order Management")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new Order")
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {

        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Order by Id")
    public OrderResponse getOrderById(@PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    @GetMapping
    @Operation(summary = "Get All Orders")
    public List<OrderResponse> getAllOrders() {

        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Order")
    public OrderResponse updateOrder(@PathVariable Long id,
                                     @Valid @RequestBody OrderRequest request) {

        return orderService.updateOrder(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Order")
    public void deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);
    }
}