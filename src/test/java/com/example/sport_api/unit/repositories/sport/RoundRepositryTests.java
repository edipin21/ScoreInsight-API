package com.example.sport_api.unit.repositories.sport;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.sport_api.models.sport.Game;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.models.sport.Season;
import com.example.sport_api.models.sport.TeamSeason;
import com.example.sport_api.repositories.soccer.GameRepository;
import com.example.sport_api.repositories.soccer.RoundRepository;
import com.example.sport_api.repositories.soccer.SeasonRepository;

@DataJpaTest
public class RoundRepositryTests {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private GameRepository gameRepository;

    // need to fix!!!!!
    @Test
    public void testRoundMapping() {

        Season season = new Season();
        season.setSeasonId(1);
        seasonRepository.save(season);

        Game game = new Game();
        game.setGameId(1);
        gameRepository.save(game);

        TeamSeason teamSeason = new TeamSeason();
        teamSeason.setStatId(1);

        Round round = new Round();
        round.setGames(new ArrayList<>());
        round.setSeason(null);
        round.setStandings(new ArrayList<>());
        round.setPlayerSeasons(new ArrayList<>());
        round.setTeamSeasons(new ArrayList<>());

        round.setRoundId(1);
        round.setName("test");

        roundRepository.save(round);

    }

}
