package com.adp.changeService.service;

import com.adp.changeService.dto.UpdateCoinQuantityRequest;
import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.CoinType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CoinService {

    Coin getCoinByType(CoinType type);
    void updateCoinQuantity(Coin coin, int amount);
    void updateCoinQuantity(UpdateCoinQuantityRequest updateCoinQuantityRequest);
    BigDecimal getTotalValueAvailableForChange();
}