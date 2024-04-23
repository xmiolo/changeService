package com.adp.changeService.controller;

import com.adp.changeService.dto.UpdateCoinQuantityRequest;
import com.adp.changeService.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @PutMapping("/quantity")
    public ResponseEntity<?> updateCoinQuantity(@RequestBody UpdateCoinQuantityRequest request) {
        coinService.updateCoinQuantity(request);
        return ResponseEntity.ok("Coin quantity updated successfully");
    }
}
