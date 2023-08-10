package com.example.sport_api.controllers.betting;

import java.util.List;
import java.time.format.DateTimeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.constants.OpenApiParameters;
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.services.betting.BettingEventService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.util.DateUtils;
import com.example.sport_api.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Betting Event", description = "Endpoints for retrieving Betting Event information")
@RequestMapping("/soccer/odds")
public class BettingEventController {

    private static final Logger logger = LogManager.getLogger(BettingEventController.class);

    @Autowired
    private BettingEventService bettingEventService;

    @Autowired
    private CompetitionService competitionService;

    @Operation(summary = "Get Betting Event by competition and season . ", description = "Retrieves  Betting Event by competition and season.  \n"
            + "Recommended Call Interval: 10 Minutes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = BettingEvent.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })

    @GetMapping("/BettingEventsBySeason/{competition}/{season}")
    public ResponseEntity<?> retriveBettingEventsByCompetitionAndSeason(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.YEAR_DESCRITION) @PathVariable String season,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

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

    @Operation(summary = "Get Betting Event by competition and date . ", description = "Retrieves  Betting Event by competition and date.  \n"
            + "Recommended Call Interval: 10 Minutes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = BettingEvent.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/BettingEventsByDate/{competition}/{date}")
    public ResponseEntity<?> retriveBettingEventsByCompetitionAndDate(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.DATE_FORMAT_DESCRIPTION) @PathVariable String date,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        try {
            Integer theCompetition = Integer.parseInt(competition);

            if (competition == null ||
                    !competitionService.isCompetitionValid(theCompetition)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (date == null || !DateUtils.isValidDate(date)) {
                throw new IllegalArgumentException("Invalid Argument: The date parameter is invalid ");
            }

            List<BettingEvent> bettingEvents = bettingEventService.getBettingEventsByCompetitionAndDate(theCompetition,
                    date);

            return ResponseUtil.createOkResponse(bettingEvents);
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
