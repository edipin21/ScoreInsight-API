package com.example.sport_api.services.betting;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.constants.ExternalBettingApiEndpoints;
import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.repositories.betting.BettingMarketRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.bettingUtil.BettingMarketUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class BettingMarketService {

    private static final Logger logger = LogManager.getLogger(BettingMarketService.class);

    @Autowired
    private BettingMarketRepository bettingMarketRepository;

    @Autowired
    private BettingEventService bettingEventService;

    public void syncBettingMarketsFromExternalApi() {

        try {
            String bettingMarketsEndPoint = "";
            Map<Integer, Integer> map = bettingEventService.getEventIdAndCompetitionMap();
            TypeReference<List<BettingMarket>> bettingMarketTypeRef = new TypeReference<>() {
            };
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer competition = entry.getValue();
                Integer eventId = entry.getKey();

                bettingMarketsEndPoint = ExternalBettingApiEndpoints.BETTING_MARKET_BY_EVENT_RESOURCE_URL + competition
                        + "/"
                        + eventId;
                List<BettingMarket> bettingMarkets = ExternalApiDataFetcherUtil
                        .fetchListDataFromExternalApi(bettingMarketsEndPoint, bettingMarketTypeRef);

                BettingMarketUtils.addCompetitionIdToBettingMarkets(bettingMarkets, competition);
                BettingMarketUtils.saveBettingMarketsToDB(bettingMarketRepository, bettingMarkets);

            }
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
        }

    }

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
