package com.example.sport_api.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sport_api.models.Game;
import com.example.sport_api.repositories.CompetitionRepository;
import com.example.sport_api.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGamesByDateTimeAndCompetitionId(Integer id, String date) {

        Date theDate = Date.valueOf(date);
        List<Game> games = gameRepository.findByDateTimeAndCompetitionId(theDate, id);
        return games;

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