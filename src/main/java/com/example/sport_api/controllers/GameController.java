package com.example.sport_api.controllers;

import java.util.List;
import java.time.format.DateTimeParseException;
import com.example.sport_api.models.Game;
import com.example.sport_api.services.GameService;
import com.example.sport_api.util.ResponseUtil;
import com.example.sport_api.services.CompetitionService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/scores/gamesByDate/{competition}/{date}")
    public ResponseEntity<List<Game>> retriveGamesByCompetitionIdAndDate(
            @PathVariable Integer competition, @PathVariable String date) {

        try {
            System.out.println("start");
            if (date == null || !gameService.isValidDate(date)) {
                throw new IllegalArgumentException("Invalid Argument: The date parameter is invalid ");
            }

            if (competition == null ||
                    !competitionService.isCompetitionIdValid(competition)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }
            List<Game> games = gameService.getGamesByDateTimeAndCompetitionId(competition, date);

            return ResponseUtil.createOkResponse(games);

        } catch (DateTimeParseException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
