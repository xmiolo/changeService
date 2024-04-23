package com.adp.changeService.init;

import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.CoinType;
import com.adp.changeService.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public void run(String... args) {
        List<Coin> coins = Arrays.stream(CoinType.values())
                .map(type -> new Coin(type, 100))
                .collect(Collectors.toList());
        coinRepository.saveAll(coins);
    }
}
