package com.ALBAMA.payment_service.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeEntry {
    private String productId;

    private Integer quantity;
}
