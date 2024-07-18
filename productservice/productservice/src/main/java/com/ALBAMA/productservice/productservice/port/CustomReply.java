package com.ALBAMA.productservice.productservice.port;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomReply {

    private UUID productId;
    private int quantity;
    private boolean inStock;
}
