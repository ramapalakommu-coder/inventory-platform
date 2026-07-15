package com.inventory.platform.order.service.external;

import com.inventory.platform.order.client.ProductClient;
import com.inventory.platform.order.client.dto.ProductResponse;
import com.inventory.platform.order.exception.ProductNotFoundException;
import com.inventory.platform.order.exception.ProductServiceUnavailableException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceCaller {

    private final ProductClient productClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "productFallback")
    public ProductResponse getProduct(Long productId) {
        return productClient.getProductById(productId);
    }

    public ProductResponse productFallback(Long productId, Exception ex) {

        if (ex instanceof FeignException.NotFound) {
            throw new ProductNotFoundException(
                    "Product not found with id : " + productId);
        }

        throw new ProductServiceUnavailableException(
                "Product Service is temporarily unavailable.");
    }
}