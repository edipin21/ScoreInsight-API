package com.example.sport_api.services.betting;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalBettingApiEndpoints;
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.repositories.betting.BettingEventRepository;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.bettingUtil.BettingEventUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class BettingEventService {
    static final int[] seasonsArr = { 2022, 2023, 2024 };
    private static final Logger logger = LogManager.getLogger(BettingEventService.class);

    @Autowired
    private BettingEventRepository bettingEventRepository;

    @Autowired
    private CompetitionService competitionService;

    public void syncBettingEventesFromExternalApi() {

        try {
            List<Integer> competitionIntegers = competitionService.getSortedCompetitionIds();
            TypeReference<List<BettingEvent>> bettingEventTypeRef = new TypeReference<>() {
            };

            competitionIntegers.forEach(competitionId -> {
                for (int i = 0; i < seasonsArr.length; i++) {
                    String bettingEventesEndPoint = ExternalBettingApiEndpoints.BETTING_EVENTS_BY_SEASON_RESOURCE_URL
                            + competitionId
                            + "/" + seasonsArr[i];

                    List<BettingEvent> bettingEventes = ExternalApiDataFetcherUtil
                            .fetchListDataFromExternalApi(bettingEventesEndPoint, bettingEventTypeRef);

                    if (!bettingEventes.isEmpty())
                        BettingEventUtils.saveBettingEventsToDB(bettingEventes, bettingEventRepository);
                }
            });
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public List<BettingEvent> getBettingEventsByCompetitionAndSeason(Integer competition, Integer season) {

        try {
            List<BettingEvent> bettingEvents = bettingEventRepository.findByCompetitionAndSeason(competition, season);

            return bettingEvents;
        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving bettingEvents: " + e.getMessage());
            throw e;
        }

    }

    public List<BettingEvent> getBettingEventsByCompetitionAndDate(Integer competition, String date) {

        try {
            Date theDate = Date.valueOf(date);
            List<BettingEvent> bettingEvents = bettingEventRepository.findBettingEventByCompetitionAndDate(competition,
                    theDate);

            return bettingEvents;
        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving bettingEvents: " + e.getMessage());
            throw e;
        }

    }

    public Map<Integer, Integer> getEventIdAndCompetitionMap() {
        List<Object[]> results = bettingEventRepository.findBettingEventIdAndCompetition();

        Map<Integer, Integer> eventIdToCompetitionMap = new HashMap<>();
        for (Object[] result : results) {
            Integer eventId = (Integer) result[0];
            Integer competitionId = (Integer) result[1];

            if (eventId != null && competitionId != null) {
                eventIdToCompetitionMap.put(eventId, competitionId);
            }
        }

        return eventIdToCompetitionMap;
    }

    public boolean isValidSeason(Integer season) {
        List<Integer> seasons = Arrays.asList(2022, 2023, 2024);
        return seasons.contains(season);
    }
}
