package com.example.sport_api.util.bettingUtil;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.repositories.betting.BettingEventRepository;

public class BettingEventUtils {

    public static void saveBettingEventsToDB(List<BettingEvent> bettingEvents,
            BettingEventRepository bettingEventRepository) throws DataAccessException {
        bettingEventRepository.saveAll(bettingEvents);
    }
}
