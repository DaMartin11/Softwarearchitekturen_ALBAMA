package com.ALBAMA.productservice.productservice.port.exception;

public class NoProductsException extends Exception{
    public NoProductsException() {
        super("There are no products available.");
    }

}
