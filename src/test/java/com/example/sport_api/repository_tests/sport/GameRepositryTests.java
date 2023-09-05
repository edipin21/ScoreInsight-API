package com.example.sport_api.repository_tests.sport;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.models.sport.PlayoffAggregateScore;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.repositories.soccer.GameRepository;

// need to fix!!!!!
@DataJpaTest
public class GameRepositryTests {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testGameMapping() {

        Competition competition = new Competition();
        competition.setCompetitionId(1);

        Round round = new Round();
        round.setRoundId(1);

        PlayoffAggregateScore playoffAggregateScore = new PlayoffAggregateScore();
        playoffAggregateScore.setTeamA_Id(1);

        Game game = new Game();
        game.setSeason(1);
        game.setGameId(1);
        game.setRoundId(1);
        game.setGroupName("test");
        game.setPlayoffAggregateScore(playoffAggregateScore);
        game.setRound(round);
        game.setCompetition(competition);

        gameRepository.save(game);

        Optional<Game> savedGame = gameRepository.findById(1);

        assertTrue(savedGame.isPresent());

    }

}
