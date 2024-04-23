package com.adp.changeService.service;

import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.CoinType;
import com.adp.changeService.exception.InsufficientChangeException;
import com.adp.changeService.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public Coin getCoinByType(CoinType type) {
        return coinRepository.findByType(type)
                .orElseThrow(() -> new InsufficientChangeException("Coin not found: " + type));
    }

    @Override
    public void updateCoinQuantity(Coin coin, int newQuantity) {
        coin.setQuantity(newQuantity);
        coinRepository.save(coin);
    }

    @Override
    public BigDecimal getTotalValueAvailable() {
        List<Coin> allCoins = coinRepository.findAll();
        return allCoins.stream()
                .map(coin -> coin.getType().getValue().multiply(BigDecimal.valueOf(coin.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
