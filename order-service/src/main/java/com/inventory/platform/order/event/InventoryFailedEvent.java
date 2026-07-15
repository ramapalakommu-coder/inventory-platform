package com.inventory.platform.order.event;

import lombok.Data;

@Data
public class InventoryFailedEvent {

    private Long orderId;
    private String reason;

    public InventoryFailedEvent() {
    }

    public InventoryFailedEvent(Long orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    // Getters & Setters
}