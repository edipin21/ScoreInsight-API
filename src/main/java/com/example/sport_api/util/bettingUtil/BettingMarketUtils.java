package com.example.sport_api.util.bettingUtil;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.repositories.betting.BettingMarketRepository;

public class BettingMarketUtils {

    public static void addCompetitionIdToBettingMarkets(List<BettingMarket> bettingMarkets, Integer CompetitionId) {
        bettingMarkets.forEach(bettingMarket -> bettingMarket.setCompetitionId(CompetitionId));
    }

    public static void saveBettingMarketsToDB(BettingMarketRepository bettingMarketRepository,
            List<BettingMarket> bettingMarkets) throws DataAccessException {
        bettingMarketRepository.saveAll(bettingMarkets);
    }
}
