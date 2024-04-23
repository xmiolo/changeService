package com.adp.changeService.dto;

import com.adp.changeService.enums.CoinType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class ChangeResponse {

    private BigDecimal changeAmount;
    private Map<CoinType, Integer> coinsReturned;

    public ChangeResponse(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
        this.coinsReturned = new HashMap<>();
    }
}
