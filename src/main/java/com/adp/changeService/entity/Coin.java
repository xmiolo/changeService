package com.adp.changeService.entity;

import com.adp.changeService.enums.CoinType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CoinType type;

    private int quantity;

    public Coin(CoinType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Coin() {

    }
}
