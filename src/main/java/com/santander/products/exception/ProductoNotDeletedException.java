package com.santander.products.exception;

public class ProductoNotDeletedException extends RuntimeException {
    public ProductoNotDeletedException() {
        super();
    }

    public ProductoNotDeletedException(String errorMessage) {
        super(errorMessage);
    }

    public ProductoNotDeletedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
