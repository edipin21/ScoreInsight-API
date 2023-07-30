package com.example.sport_api.repositories.betting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.betting.BettingMarket;

public interface BettingMarketRepository extends JpaRepository<BettingMarket, Integer> {

}
