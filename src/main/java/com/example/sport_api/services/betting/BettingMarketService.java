package com.example.sport_api.services.betting;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.repositories.betting.BettingMarketRepository;

@Service
public class BettingMarketService {

    private static final Logger logger = LogManager.getLogger(BettingMarketService.class);

    @Autowired
    private BettingMarketRepository bettingMarketRepository;

    public List<BettingMarket> getBettingMarketsByCompetitionAndEventId(Integer competition, Integer eventId) {

        try {
            List<BettingMarket> bettingMarkets = bettingMarketRepository.findByCompetitionAndEventId(competition,
                    eventId);
            return bettingMarkets;

        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving bettingMarkets: " + e.getMessage());
            throw e;
        }

    }

    public Boolean isEventIdValid(Integer eventId) {

        try {
            List<Integer> eventsIds = bettingMarketRepository.findAllEventsIds();

            return eventsIds.contains(eventId);
        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving eventsIds: " + e.getMessage());
            throw e;
        }

    }

}
