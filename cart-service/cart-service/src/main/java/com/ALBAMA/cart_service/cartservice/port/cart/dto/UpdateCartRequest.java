package com.ALBAMA.cart_service.cartservice.port.cart.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCartRequest {

    private UUID userId;
    private UUID productId;
    private int quantity;
}
