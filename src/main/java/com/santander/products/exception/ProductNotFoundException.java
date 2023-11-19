package com.santander.products.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public ProductNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
