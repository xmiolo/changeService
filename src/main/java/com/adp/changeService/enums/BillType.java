package com.adp.changeService.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum BillType {
    ONE(BigDecimal.ONE),
    TWO(new BigDecimal("2")),
    FIVE(new BigDecimal("5")),
    TEN(new BigDecimal("10")),
    TWENTY(new BigDecimal("20")),
    FIFTY(new BigDecimal("50")),
    HUNDRED(new BigDecimal("100"));

    private final BigDecimal value;

    BillType(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
