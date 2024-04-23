package com.adp.changeService.service;

import com.adp.changeService.dto.UpdateCoinQuantityRequest;
import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.CoinType;
import com.adp.changeService.exception.ChangeException;
import com.adp.changeService.exception.CoinException;
import com.adp.changeService.repository.CoinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public Coin getCoinByType(CoinType coinType) {
        return coinRepository.findByType(coinType)
                .orElseThrow(() -> new ChangeException("Coin not found: " + coinType));
    }

    public void updateCoinQuantity(Coin coin, int newQuantity) {
        coin.setQuantity(newQuantity);
        coinRepository.save(coin);
    }

    @Override
    public BigDecimal getTotalValueAvailableForChange() {
        List<Coin> allCoins = coinRepository.findAll();
        return allCoins.stream()
                .map(coin -> coin.getType().getValue().multiply(BigDecimal.valueOf(coin.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public void updateCoinQuantity(UpdateCoinQuantityRequest updateCoinQuantityRequest) {
        if(updateCoinQuantityRequest.getQuantity() > 0 && Math.round(updateCoinQuantityRequest.getQuantity()) == updateCoinQuantityRequest.getQuantity()){
            Coin coin = getCoinByType(updateCoinQuantityRequest.getCoinType());
            updateCoinQuantity(coin, (int)updateCoinQuantityRequest.getQuantity());
        } else
            throw new CoinException("Only integers are allowed");
    }
}
