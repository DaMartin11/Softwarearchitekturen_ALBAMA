package com.ALBAMA.productservice.productservice.port.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException () {
        super("There is no product with the given id.");
    }

}
