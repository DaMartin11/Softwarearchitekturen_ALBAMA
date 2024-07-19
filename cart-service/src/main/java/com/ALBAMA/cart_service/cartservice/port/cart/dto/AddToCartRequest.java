package com.ALBAMA.cart_service.cartservice.port.cart.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String userId;
    private String productId;
    private int quantity;

}
