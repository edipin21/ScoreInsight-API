package com.example.sport_api.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.sport.BoxScore;
import com.example.sport_api.models.sport.PlayerGame;
import com.example.sport_api.repositories.playerGameRepository;

@Service
public class playerGameService {

    private static final Logger logger = LogManager.getLogger(playerGameService.class);

    @Autowired
    private playerGameRepository playerGameRepository;

    public List<PlayerGame> getPlayerGameStatByCompetitionAndDate(Integer competition, String date) {
        try {
            Date theDate = Date.valueOf(date);

            List<PlayerGame> playerGames = playerGameRepository.findByCompetitionAndDate(competition, theDate);
            return playerGames;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
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
}
