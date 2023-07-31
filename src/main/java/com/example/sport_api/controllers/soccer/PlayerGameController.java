package com.example.sport_api.controllers.soccer;

import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.sport.PlayerGame;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.playerGameService;
import com.example.sport_api.util.DateUtils;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class PlayerGameController {

    private static final Logger logger = LogManager.getLogger(PlayerGameController.class);

    @Autowired
    private playerGameService playerGameService;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("stats/PlayerGameStatsByDate/{competition}/{date}")
    public ResponseEntity<?> retrivePlayerGameStatByCompetitionAndDate(@PathVariable String competition,
            @PathVariable String date) {

        try {

            Integer theCompetition = Integer.parseInt(competition);

            if (date == null || !DateUtils.isValidDate(date)) {
                throw new IllegalArgumentException("Invalid Argument: The date parameter is invalid ");
            }

            if (competition == null ||
                    !competitionService.isCompetitionValid(theCompetition)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            List<PlayerGame> playerGames = playerGameService.getPlayerGameStatByCompetitionAndDate(theCompetition,
                    date);

            return ResponseUtil.createOkResponse(playerGames);
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
