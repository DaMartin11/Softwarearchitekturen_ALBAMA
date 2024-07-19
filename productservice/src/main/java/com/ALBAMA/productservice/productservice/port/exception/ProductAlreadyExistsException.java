package com.ALBAMA.productservice.productservice.port.exception;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException() {
        super("Product already exists.");
    }

}
