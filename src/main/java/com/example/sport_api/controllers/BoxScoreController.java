package com.example.sport_api.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.BoxScore;
import com.example.sport_api.services.BoxScoreService;
import com.example.sport_api.services.CompetitionService;
import com.example.sport_api.services.GameService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class BoxScoreController {

    private static final Logger logger = LogManager.getLogger(BoxScoreController.class);

    @Autowired
    private BoxScoreService boxScoreService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private GameService gameService;

    @GetMapping("stats/boxScore/{competition}/{gameId}")
    public ResponseEntity<BoxScore> retriveBoxScoreByCompetitionAndGameId(@PathVariable String competition,
            @PathVariable String gameId) {

        try {

            Integer theCompetition = Integer.parseInt(competition);
            Integer theGameId = Integer.parseInt(gameId);

            if (competition == null ||
                    !competitionService.isCompetitionIdValid(theCompetition)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (gameId == null ||
                    !gameService.isValidGameId(theGameId)) {
                throw new IllegalArgumentException("Invalid Argument: The gameId parameter is invalid ");
            }

            BoxScore boxScore = boxScoreService.getBoxScoreByCompetitionAndGameId(theCompetition, theGameId);

            return ResponseUtil.createOkResponse(boxScore);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and gameId parameteres should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and gameId parameteres should be an integer");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
