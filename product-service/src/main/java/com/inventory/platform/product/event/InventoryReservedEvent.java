package com.inventory.platform.product.event;

import lombok.Data;
import lombok.Generated;

@Data
@Generated

public class InventoryReservedEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;

    public InventoryReservedEvent() {
    }

    public InventoryReservedEvent(Long orderId, Long productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters & Setters
}