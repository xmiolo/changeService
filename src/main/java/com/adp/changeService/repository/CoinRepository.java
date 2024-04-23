package com.adp.changeService.repository;

import com.adp.changeService.entity.Coin;
import com.adp.changeService.enums.CoinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository <Coin, Long>{
    Optional<Coin> findByType(CoinType type);
}