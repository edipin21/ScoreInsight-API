package com.example.sport_api.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sport_api.models.Game;
import com.example.sport_api.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGamesByDateTimeAndCompetitionId(Integer id, Date date) {

        List<Game> games = gameRepository.findByDateTimeAndCompetitionId(date, id);

        return games;
    }

}
