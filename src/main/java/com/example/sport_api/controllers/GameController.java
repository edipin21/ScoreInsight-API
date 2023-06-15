package com.example.sport_api.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.Game;
import com.example.sport_api.services.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public List<Game> retrivegamesByCompetitionIdAndDate(Date date, Integer competitonId) {

        Integer i = 3;
        String dateeee = "2022-06-21";
        Date date1 = Date.valueOf(dateeee);
        return gameService.getGamesByDateTimeAndCompetitionId(3, date1);
    }
}
