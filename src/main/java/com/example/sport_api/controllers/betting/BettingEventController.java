package com.example.sport_api.controllers.betting;

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
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.services.betting.BettingEventService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class BettingEventController {

    private static final Logger logger = LogManager.getLogger(BettingEventController.class);

    @Autowired
    private BettingEventService bettingEventService;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/odds/BettingEventsBySeason/{competition}/{season}")
    public ResponseEntity<List<BettingEvent>> retriveBettingEventsByCompetitionAndSeason(
            @PathVariable String competition, @PathVariable String season) {

        try {
            Integer theCompetition = Integer.parseInt(competition);
            Integer theSeason = Integer.parseInt(season);

            if (competition == null ||
                    !competitionService.isCompetitionValid(theCompetition)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (season == null || !bettingEventService.isValidSeason(theSeason)) {
                throw new IllegalArgumentException("Invalid Argument: The season parameter is invalid ");
            }

            List<BettingEvent> bettingEvents = bettingEventService.getBettingEventsByCompetitionAndSeason(
                    theCompetition,
                    theSeason);

            return ResponseUtil.createOkResponse(bettingEvents);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and season parameters should be an integers", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and season parameters should be an integers");
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
