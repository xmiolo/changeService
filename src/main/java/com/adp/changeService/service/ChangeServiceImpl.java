package com.adp.changeService.service;

import com.adp.changeService.dto.ChangeResponse;
import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.CoinType;
import com.adp.changeService.exception.InsufficientChangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ChangeServiceImpl implements ChangeService {

    @Autowired
    private CoinService coinService;

    @Override
    public ResponseEntity<ChangeResponse> calculateChange(BigDecimal billAmount, boolean lessCoin) {

        if (billAmount.compareTo(coinService.getTotalValueAvailable()) > 0) {
            throw new InsufficientChangeException("Not enough coins to make change");
        }

        ChangeResponse changeResponse = new ChangeResponse(billAmount);
        BigDecimal remainingAmount = billAmount;

        List<CoinType> coinList = provideOptimalChange(lessCoin);

        for(CoinType coinType : coinList) {
            Coin coin = coinService.getCoinByType(coinType);
            int avaliableCount = coin.getQuantity();
            int divisionCount = remainingAmount.divide(coinType.getValue(), RoundingMode.FLOOR).intValue();
            divisionCount = Math.min(divisionCount, avaliableCount);

            if (divisionCount > 0) {
                changeResponse.getCoinsReturned().put(coinType, divisionCount);
                coinService.updateCoinQuantity(coin, avaliableCount - divisionCount);
                remainingAmount = remainingAmount.subtract(coinType.getValue().multiply(new BigDecimal(divisionCount)));
            }
        }

        return ResponseEntity.ok(changeResponse);
    }

    private static List<CoinType> provideOptimalChange(boolean lessCoin) {
        List<CoinType> coinList = new ArrayList<>(Arrays.asList(CoinType.values()));
        if(lessCoin) {
            Collections.reverse(coinList);
        }
        return coinList;
    }
}
