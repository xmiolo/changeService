package com.adp.changeService.dto;

import com.adp.changeService.enums.CoinType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCoinQuantityRequest {

    @Enumerated(EnumType.STRING)
    private CoinType coinType;

    @NotNull
    private float quantity;
}
