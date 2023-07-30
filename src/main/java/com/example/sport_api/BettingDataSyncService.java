package com.example.sport_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalBettingApiEndpoints;
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.repositories.betting.BettingEventRepository;
// import com.example.sport_api.repositories.betting.SportsBookRepository;
import com.example.sport_api.repositories.soccer.CompetitionRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BettingDataSyncService {

    static final int[] seasonsArr = { 2022, 2023, 2024 };

    @Autowired
    private ExternalApiDataFetcherUtil eApiDataFetcherUtil;

    @Autowired
    private BettingEventRepository bEventRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    // @Autowired
    // private SportsBookRepository sportsBookRepository;

    public void fetchBettingEventesAndUpdate() throws JsonProcessingException {

        try {
            int count = 0;
            List<BettingEvent> bettingEventes = new ArrayList<>();
            List<Integer> competitionIntegers = competitionRepository.findAllCompetitionsNumbers();
            TypeReference<List<BettingEvent>> bettingEventTypeRef = new TypeReference<>() {
            };
            competitionIntegers.sort(null);

            ObjectMapper objectMapper = eApiDataFetcherUtil.initializeObjectMapper();

            // Partial loop for sanity checks - delete count
            for (Integer competition : competitionIntegers) {
                count += 4;
                for (int i = 0; i < seasonsArr.length; i++) {
                    if (count > 8)
                        break;
                    String bettingEventesJson = eApiDataFetcherUtil
                            .fetchData(
                                    ExternalBettingApiEndpoints.BETTING_EVENTS_BY_SEASON_RESOURCE_URL + competition
                                            + "/"
                                            + seasonsArr[i]
                                            + "?key=");
                    bettingEventes = objectMapper.readValue(bettingEventesJson, bettingEventTypeRef);

                    bEventRepository.saveAll(bettingEventes);
                }
            }
        } catch (Exception e) {
            eApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

}
