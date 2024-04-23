package com.adp.changeService.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class ChangeRequest {

    @NotNull(message = "Bill amount is required")
    @Positive(message = "Bill amount must be positive")
    private BigDecimal billAmount;

    @Value("true")
    private boolean lessCoin;
}
