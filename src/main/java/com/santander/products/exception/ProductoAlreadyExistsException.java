package com.santander.products.exception;

public class ProductoAlreadyExistsException extends RuntimeException {
    public ProductoAlreadyExistsException() {
        super();
    }

    public ProductoAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

    public ProductoAlreadyExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
