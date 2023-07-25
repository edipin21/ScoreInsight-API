package com.example.sport_api.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.sport.Game;
import com.example.sport_api.repositories.soccer.GameRepository;

@Service
public class GameService {

    private static final Logger logger = LogManager.getLogger(GameService.class);

    @Autowired
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGamesByDateTimeAndCompetitionId(Integer id, String date) {

        try {
            Date theDate = Date.valueOf(date);
            List<Game> games = gameRepository.findByDateAndCompetitionId(theDate, id);
            return games;
        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving games: " + e.getMessage());
            throw e;
        }

    }

    public boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Map<Integer, Integer> getGameIdAndCompetitionMap() {
        List<Object[]> results = gameRepository.findGameIdAndCompetition();

        Map<Integer, Integer> gameIdToCompetitionMap = new HashMap<>();
        for (Object[] result : results) {
            Integer gameId = (Integer) result[0];
            Integer competitionId = (Integer) result[1];

            if (gameId != null && competitionId != null) {
                gameIdToCompetitionMap.put(gameId, competitionId);
            }
        }

        return gameIdToCompetitionMap;
    }

    public boolean isValidGameId(Integer gameId) {

        try {
            List<Integer> gameIds = gameRepository.findAllGameIds();

            return gameIds.contains(gameId);

        } catch (DataAccessException e) {
            logger.error("Data access error occurred while retrieving game ids: " + e.getMessage());
            throw e;
        }

    }
}