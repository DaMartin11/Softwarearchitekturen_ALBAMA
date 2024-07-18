package com.ALBAMA.cart_service.cartservice.port.cart.exception;

public class CartNotFoundException extends Exception{
    public CartNotFoundException() {
        super("Cart not found.");
    }

}
