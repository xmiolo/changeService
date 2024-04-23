package com.adp.changeService.service;

import com.adp.changeService.dto.ChangeResponse;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface ChangeService {
    ResponseEntity<ChangeResponse> calculateChange(BigDecimal billAmount, boolean lessCoin);
}
