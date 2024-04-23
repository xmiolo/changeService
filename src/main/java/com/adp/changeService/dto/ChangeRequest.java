package com.adp.changeService.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
public class ChangeRequest {

    private BigDecimal billAmount;

    @Value("true")
    private boolean lessCoin;
}
