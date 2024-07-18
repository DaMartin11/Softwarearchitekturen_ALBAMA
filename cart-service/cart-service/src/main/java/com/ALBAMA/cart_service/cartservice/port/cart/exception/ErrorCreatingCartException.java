package com.ALBAMA.cart_service.cartservice.port.cart.exception;

public class ErrorCreatingCartException extends Exception{
    public ErrorCreatingCartException() {
        super("Cart could not be created");
    }

}
