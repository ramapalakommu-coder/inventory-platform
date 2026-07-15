package com.inventory.platform.product.kafka.consumer;

import com.inventory.platform.product.event.InventoryFailedEvent;
import com.inventory.platform.product.event.InventoryReservedEvent;
import com.inventory.platform.product.kafka.event.InventoryEventPublisher;
import com.inventory.platform.product.kafka.event.OrderCreatedEvent;
import com.inventory.platform.product.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j

public class InventoryConsumer {

    private final InventoryService inventoryService;
    private final InventoryEventPublisher eventPublisher;

    public InventoryConsumer(InventoryService inventoryService,
                             InventoryEventPublisher eventPublisher) {

        this.inventoryService = inventoryService;
        this.eventPublisher = eventPublisher;
    }

    @KafkaListener(
            topics = "order-created-topic",
            groupId = "product-group")
    public void consume(OrderCreatedEvent event) {

        log.info("Received OrderCreatedEvent : {}", event);

        try {

            inventoryService.reserveInventory(event);

            log.info("Inventory reserved successfully");

            eventPublisher.publishInventoryReserved(
                    new InventoryReservedEvent(
                            event.getOrderId(),
                            event.getProductId(),
                            event.getQuantity()));

        } catch (Exception ex) {
            log.error("Inventory reservation failed", ex);
            eventPublisher.publishInventoryFailed(
                    new InventoryFailedEvent(
                            event.getOrderId(),
                            ex.getMessage()));
        }
    }
}