package com.ALBAMA.payment_service.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ChargeRequest {
    private List<ChargeEntry> chargeEntries;

}
