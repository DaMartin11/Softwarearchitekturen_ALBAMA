package com.ALBAMA.cart_service.cartservice.port.cart.exception;

public class NotAuthorizedException extends Exception{

    public NotAuthorizedException() {
        super("Not authorized.");
    }

}
