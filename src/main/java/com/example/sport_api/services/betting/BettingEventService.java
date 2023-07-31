package com.example.sport_api.services.betting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.repositories.betting.BettingEventRepository;

@Service
public class BettingEventService {

    private static final Logger logger = LogManager.getLogger(BettingEventService.class);

    @Autowired
    private BettingEventRepository bettingEventRepository;

    public List<BettingEvent> getBettingEventsByCompetitionAndSeason(Integer competition, Integer season) {

        try {
            List<BettingEvent> bettingEvents = bettingEventRepository.findByCompetitionAndSeason(competition, season);

            return bettingEvents;
        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving bettingEvents: " + e.getMessage());
            throw e;
        }

    }

    public boolean isValidSeason(Integer season) {
        List<Integer> seasons = Arrays.asList(2022, 2023, 2024);
        return seasons.contains(season);
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

}
