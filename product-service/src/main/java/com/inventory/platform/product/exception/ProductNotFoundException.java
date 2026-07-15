package com.inventory.platform.product.exception;

    public class ProductNotFoundException extends RuntimeException {

        public ProductNotFoundException(String message) {
            super(message);
        }
    }

