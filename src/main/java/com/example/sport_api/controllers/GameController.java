package com.example.sport_api.controllers;

import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.Game;
import com.example.sport_api.services.CompetitionService;
import com.example.sport_api.services.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CompetitionService competitionService;

    // need to fix the catch blocks and add the rest of the exception
    @GetMapping("/scores/gamesByDate/{competition}/{date}")
    public ResponseEntity<List<Game>> retrivegamesByCompetitionIdAndDate(@PathVariable String date,
            @PathVariable Integer competitionId) {

        try {
            if (date == null || !gameService.isValidDate(date)) {
                throw new IllegalArgumentException("Invalid date");
            }

            if (competitionId == null || !competitionService.isCompetitionIdValid(competitionId)) {
                throw new IllegalArgumentException("Invalid competitionId");
            }
            List<Game> games = gameService.getGamesByDateTimeAndCompetitionId(competitionId, date);
            return ResponseEntity.ok(games);

        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .cacheControl(CacheControl.noCache())
                    .body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .cacheControl(CacheControl.noCache())
                    .body(null);
        }

    }
}
