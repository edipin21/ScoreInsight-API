package com.example.sport_api.util.soccerUtil;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.sport_api.BoxScoreIdGenerator;
import com.example.sport_api.models.sport.BoxScore;
import com.example.sport_api.models.sport.PlayerGame;
import com.example.sport_api.repositories.soccer.BoxScoreRepository;

public class BoxScoreUtils {

    private static void saveBoxScoreToDB(BoxScoreRepository boxScoreRepository, BoxScore boxScore)
            throws DataAccessException {

        boxScoreRepository.save(boxScore);
    }

    public static void saveOrUpdateBoxScore(BoxScore currentBoxScore, BoxScore existingBoxScore,
            BoxScoreRepository boxScoreRepository, Integer competition) {

        currentBoxScore.setCompetition(competition);
        currentBoxScore.setDateTime(currentBoxScore.getGame().getDateTime());
        List<PlayerGame> playersGames = currentBoxScore.getPlayerGames();
        PlayerGameUtils.setCompetitionToPlayerGame(playersGames, competition);

        if (existingBoxScore == null) {
            currentBoxScore.setBoxScoreId(BoxScoreIdGenerator.generateId());

        } else {
            currentBoxScore.setBoxScoreId(existingBoxScore.getBoxScoreId());
            saveBoxScoreToDB(boxScoreRepository, currentBoxScore);
        }
    }
}
