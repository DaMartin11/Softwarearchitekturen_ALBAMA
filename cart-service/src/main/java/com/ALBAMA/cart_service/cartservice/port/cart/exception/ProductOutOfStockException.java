package com.ALBAMA.cart_service.cartservice.port.cart.exception;

public class ProductOutOfStockException extends Exception{

    public ProductOutOfStockException() {
        super("The desired product is not available in the requested quantity");
    }

}
