package com.adp.changeService.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum CoinType {
    PENNY(new BigDecimal("0.01")), NICKEL(new BigDecimal("0.05")), DIME(new BigDecimal("0.10")), QUARTER(new BigDecimal("0.25"));

    private final BigDecimal value;

    CoinType(BigDecimal value){
        this.value = value;
    }
}
