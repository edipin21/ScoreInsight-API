package com.example.sport_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalBettingApiEndpoints;
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.repositories.betting.BettingEventRepository;
import com.example.sport_api.repositories.betting.BettingMarketRepository;
import com.example.sport_api.repositories.soccer.CompetitionRepository;
import com.example.sport_api.services.betting.BettingEventService;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BettingDataSyncService {

    static final int[] seasonsArr = { 2022, 2023, 2024 };

    @Autowired
    private BettingEventRepository bettingEventRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private BettingMarketRepository bettingMarketRepository;

    @Autowired
    private BettingEventService bettingEventService;

    // @Autowired
    // private SportsBookRepository sportsBookRepository;

    public void fetchBettingEventesAndUpdateDB() throws JsonProcessingException {

        try {
            int count = 0;
            List<BettingEvent> bettingEventes = new ArrayList<>();
            List<Integer> competitionIntegers = competitionRepository.findAllCompetitionsNumbers();
            TypeReference<List<BettingEvent>> bettingEventTypeRef = new TypeReference<>() {
            };
            competitionIntegers.sort(null);

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();

            // Partial loop for sanity checks - delete count
            for (Integer competition : competitionIntegers) {
                count += 4;
                for (int i = 0; i < seasonsArr.length; i++) {
                    if (count > 8)
                        break;
                    String bettingEventesJson = ExternalApiDataFetcherUtil
                            .fetchData(
                                    ExternalBettingApiEndpoints.BETTING_EVENTS_BY_SEASON_RESOURCE_URL + competition
                                            + "/" + seasonsArr[i]);
                    bettingEventes = objectMapper.readValue(bettingEventesJson, bettingEventTypeRef);

                    bettingEventRepository.saveAll(bettingEventes);
                }
            }
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void fetchBettingMarketByEventAndUpdateDB() throws JsonMappingException, JsonProcessingException {

        int count = 0;

        Map<Integer, Integer> map = bettingEventService.getEventIdAndCompetitionMap();
        ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
        TypeReference<List<BettingMarket>> bettingMarketTypeRef = new TypeReference<>() {
        };
        // Partial loop for sanity checks - delete count
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (count >= 4)
                break;
            Integer competition = entry.getValue();
            Integer eventId = entry.getKey();
            System.out.println(competition + "      " + eventId);
            List<BettingMarket> bettingMarkets = new ArrayList<>();

            String bettingMarketsJson = ExternalApiDataFetcherUtil
                    .fetchData(ExternalBettingApiEndpoints.BETTING_MARKET_BY_EVENT_RESOURCE_URL + competition + "/"
                            + eventId);

            bettingMarkets = objectMapper.readValue(bettingMarketsJson, bettingMarketTypeRef);
            bettingMarkets.forEach(bettingMarket -> bettingMarket.setCompetitionId(competition));

            bettingMarketRepository.saveAll(bettingMarkets);
            count++;
        }
    }
}
