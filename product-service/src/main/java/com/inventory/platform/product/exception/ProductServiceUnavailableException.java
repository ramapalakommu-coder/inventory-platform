package com.inventory.platform.product.exception;

public class ProductServiceUnavailableException
        extends RuntimeException {

    public ProductServiceUnavailableException(String message) {
        super(message);
    }
}