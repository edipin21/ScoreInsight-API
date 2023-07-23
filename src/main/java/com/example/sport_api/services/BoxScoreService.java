package com.example.sport_api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.BoxScore;
import com.example.sport_api.repositories.BoxScoreRepository;

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

}
