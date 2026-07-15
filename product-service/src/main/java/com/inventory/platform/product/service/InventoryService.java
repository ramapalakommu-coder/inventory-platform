package com.inventory.platform.product.service;

import com.inventory.platform.product.kafka.event.OrderCreatedEvent;

public interface InventoryService {

    void reserveInventory(OrderCreatedEvent event);

}