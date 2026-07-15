package com.inventory.platform.product.kafka.event;

import com.inventory.platform.product.event.InventoryFailedEvent;
import com.inventory.platform.product.event.InventoryReservedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventPublisher {
  private final Logger logger = LoggerFactory.getLogger(InventoryEventPublisher.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishInventoryReserved(InventoryReservedEvent event) {
        logger.info("Publishing InventoryReservedEvent: {}", event);
        kafkaTemplate.send(
                "inventory-reserved-topic",
                event);
    }

    public void publishInventoryFailed(InventoryFailedEvent event) {

        kafkaTemplate.send(
                "inventory-failed-topic",
                event);
    }
}