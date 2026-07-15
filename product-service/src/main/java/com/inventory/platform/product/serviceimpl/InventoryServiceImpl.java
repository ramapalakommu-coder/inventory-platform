package com.inventory.platform.product.serviceimpl;

import com.inventory.platform.product.entity.Product;
import com.inventory.platform.product.kafka.event.OrderCreatedEvent;
import com.inventory.platform.product.repository.ProductRepository;
import com.inventory.platform.product.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void reserveInventory(OrderCreatedEvent event) {

        Product product = productRepository.findById(event.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < event.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setQuantity(
                product.getQuantity() - event.getQuantity()
        );

        productRepository.save(product);

    }
}
