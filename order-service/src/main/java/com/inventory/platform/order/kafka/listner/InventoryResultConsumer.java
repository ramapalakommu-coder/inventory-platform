package com.inventory.platform.order.kafka.listner;



import com.inventory.platform.order.entity.Order;
import com.inventory.platform.order.entity.OrderStatus;
import com.inventory.platform.order.event.InventoryFailedEvent;
import com.inventory.platform.order.event.InventoryReservedEvent;
import com.inventory.platform.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryResultConsumer {

    private final OrderRepository repository;

    public InventoryResultConsumer(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @KafkaListener(
            topics = "inventory-reserved-topic",
            groupId = "order-group")
    public void inventoryReserved(InventoryReservedEvent event) {

        log.info("Received InventoryReservedEvent : {}", event);

        Order order = repository.findById(event.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found : " + event.getOrderId()));

        order.setOrderStatus(OrderStatus.CONFIRMED);

        repository.save(order);

        log.info("Order {} status updated to CONFIRMED", order.getId());
    }

    @Transactional
    @KafkaListener(
            topics = "inventory-failed-topic",
            groupId = "order-group")
    public void inventoryFailed(InventoryFailedEvent event) {

        log.info("Received InventoryFailedEvent : {}", event);

        Order order = repository.findById(event.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found : " + event.getOrderId()));

        order.setOrderStatus(OrderStatus.CANCELLED);

        repository.save(order);

        log.info("Order {} status updated to CANCELLED", order.getId());
    }
}