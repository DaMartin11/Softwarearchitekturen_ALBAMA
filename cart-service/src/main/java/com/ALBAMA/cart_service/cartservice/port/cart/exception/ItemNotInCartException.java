package com.ALBAMA.cart_service.cartservice.port.cart.exception;

public class ItemNotInCartException extends Exception{
    public ItemNotInCartException() {
        super("Item not in cart.");
    }

}
