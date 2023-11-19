package com.santander.products.exception;

public class ProductoNotSavedException extends RuntimeException {
    public ProductoNotSavedException() {
        super();
    }

    public ProductoNotSavedException(String errorMessage) {
        super(errorMessage);
    }

    public ProductoNotSavedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
