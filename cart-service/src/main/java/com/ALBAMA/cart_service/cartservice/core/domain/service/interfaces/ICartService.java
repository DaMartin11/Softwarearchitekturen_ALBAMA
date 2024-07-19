package com.ALBAMA.cart_service.cartservice.core.domain.service.interfaces;

import com.ALBAMA.cart_service.cartservice.core.domain.model.Cart;
import com.ALBAMA.cart_service.cartservice.port.cart.exception.CartNotFoundException;
import com.ALBAMA.cart_service.cartservice.port.cart.exception.ItemNotInCartException;

import java.util.UUID;

public interface ICartService {


    Cart createCart(UUID userId);

    Cart addToCart(UUID userId, UUID productId, int quantity);

    Cart updateCart(UUID userId, UUID productId, int quantity) throws CartNotFoundException, ItemNotInCartException;

    boolean clearCart(UUID userId) throws CartNotFoundException;

    Cart getCart(UUID userId) throws CartNotFoundException;


}
