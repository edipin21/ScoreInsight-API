package com.example.sport_api.services.soccer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.BoxScore;
import com.example.sport_api.repositories.soccer.BoxScoreRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.soccerUtil.BoxScoreUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class BoxScoreService {

    private static final Logger logger = LogManager.getLogger(BoxScoreService.class);

    @Autowired
    private BoxScoreRepository boxScoreRepository;

    @Autowired
    private GameService gameService;

    public void syncBoxScoreFromExternalDB() {

        try {

            Map<Integer, Integer> map = gameService.getGameIdAndCompetitionMap();
            List<BoxScore> boxScores = new ArrayList<>();

            TypeReference<List<BoxScore>> boxScoreTypeRef = new TypeReference<>() {
            };

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

                Integer gameId = entry.getKey();
                Integer competition = entry.getValue();

                System.out.println("Competition: " + competition + "," + "gameId: " + gameId);

                String boxScoreURlEndPoint = ExternalSoccerApiEndpoints.BOX_SCORE_RESOURCE_URL
                        + competition
                        + "/"
                        + gameId;

                boxScores = ExternalApiDataFetcherUtil.fetchListDataFromExternalApi(boxScoreURlEndPoint,
                        boxScoreTypeRef);

                BoxScore currentBoxScore = boxScores.get(0);
                BoxScore existingBoxScore = boxScoreRepository.findByGameId(gameId);

                BoxScoreUtils.saveOrUpdateBoxScore(currentBoxScore, existingBoxScore, boxScoreRepository, competition);
            }

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
        }
    }

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

}
