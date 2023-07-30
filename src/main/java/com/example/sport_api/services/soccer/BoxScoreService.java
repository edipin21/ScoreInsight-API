package com.example.sport_api.services.soccer;

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
import com.example.sport_api.repositories.soccer.BoxScoreRepository;

@Service
public class BoxScoreService {

    private static final Logger logger = LogManager.getLogger(BoxScoreService.class);

    @Autowired
    private BoxScoreRepository boxScoreRepository;

    public BoxScore getBoxScoreByCompetitionAndGameId(Integer competition, Integer gameId) {

        try {
            BoxScore boxScore = boxScoreRepository.findByCompetitionAndGameId(competition, gameId);
            return boxScore;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }
    }

    public List<BoxScore> getBoxScoresByCompetitionAndDate(Integer competition, String dateTime) {

        try {
            Date theDate = Date.valueOf(dateTime);

            List<BoxScore> boxScores = boxScoreRepository.findByCompetitionAndDate(competition, theDate);
            return boxScores;
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
