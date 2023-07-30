package com.example.sport_api.controllers.soccer;

import java.util.List;
import java.time.format.DateTimeParseException;

import com.example.sport_api.util.ResponseUtil;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

@RestController
public class GameController {

    private static final Logger logger = LogManager.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/scores/gamesByDate/{competition}/{date}")
    public ResponseEntity<List<Game>> retriveGamesByCompetitionIdAndDate(
            @PathVariable String competition, @PathVariable String date) {

        try {

            Integer theCompetition = Integer.parseInt(competition);

            if (date == null || !gameService.isValidDate(date)) {
                throw new IllegalArgumentException("Invalid Argument: The date parameter is invalid ");
            }

            if (competition == null ||
                    !competitionService.isCompetitionValid(theCompetition)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }
            List<Game> games = gameService.getGamesByDateTimeAndCompetitionId(theCompetition, date);

            return ResponseUtil.createOkResponse(games);

        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition parameter should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition parameter should be an integer");
        } catch (DateTimeParseException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
