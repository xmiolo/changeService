package com.adp.changeService.service;

import com.adp.changeService.dto.ChangeRequest;
import com.adp.changeService.dto.ChangeResponse;
import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.BillType;
import com.adp.changeService.enums.CoinType;
import com.adp.changeService.exception.BillException;
import com.adp.changeService.exception.ChangeException;
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
    public ResponseEntity<ChangeResponse> calculateChange(ChangeRequest changeRequest) {
        BigDecimal billAmount = changeRequest.getBillAmount();
        boolean lessCoin = changeRequest.isLessCoin();
        ChangeResponse changeResponse = new ChangeResponse(billAmount);

        if(!isValidBillValue(billAmount)) {
            throw new BillException("Bill value not allowed");
        }

        if (billAmount.compareTo(coinService.getTotalValueAvailableForChange()) > 0) {
            throw new ChangeException("Not enough coins to make change");
        }

        BigDecimal remainingAmount = billAmount;

        List<CoinType> coinList = provideOptimalChange(lessCoin);

        for(CoinType coinType : coinList) {
            Coin coin = coinService.getCoinByType(coinType);
            int availableCount = coin.getQuantity();
            int divisionCount = remainingAmount.divide(coinType.getValue(), RoundingMode.FLOOR).intValue();
            divisionCount = Math.min(divisionCount, availableCount);

            if (divisionCount > 0) {
                changeResponse.getCoinsReturned().put(coinType, divisionCount);
                coinService.updateCoinQuantity(coin, availableCount - divisionCount);
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

    private boolean isValidBillValue(BigDecimal billAmount) {
        return Arrays.stream(BillType.values())
                .anyMatch(billValue -> billValue.getValue().equals(billAmount));
    }
}
