package com.ALBAMA.cart_service.cartservice.core.domain.service.interfaces;

import com.ALBAMA.cart_service.cartservice.core.domain.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ICartRepository extends CrudRepository<Cart,String> {
    Cart findByUserId(UUID userId);

}
