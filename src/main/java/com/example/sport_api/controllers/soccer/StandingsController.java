package com.example.sport_api.controllers.soccer;

import java.util.Arrays;
import java.util.List;
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

import com.example.sport_api.config.OpenApiParameters;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.RoundService;
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
@Tag(name = "Standings", description = "Endpoints for retrieving Standings information")
@RequestMapping("/soccer/scores")
public class StandingsController {

    private static final Logger logger = LogManager.getLogger(StandingsController.class);

    @Autowired
    private RoundService roundService;

    @Autowired
    private CompetitionService competitionService;

    @Operation(summary = "Get Standings by competition and year . ", description = "Retrieves Standings by competition and year.  \n"
            + "Recommended Call Interval: 5 Minutes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Round.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })

    @GetMapping("/Standings/{competition}/{year}")
    public ResponseEntity<?> retriveStandingsByCompetitionAndYear(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.YEAR_DESCRITION) @PathVariable String year,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        List<Integer> years = Arrays.asList(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024);

        try {
            Integer competitionId = Integer.parseInt(competition);
            Integer theYear = Integer.parseInt(year);

            if (competitionId == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (theYear == null || !years.contains(theYear)) {
                throw new IllegalArgumentException("Invalid Argument: The year parameter is invalid ");
            }

            List<Round> Standings = roundService.getStandingsByCompetitionAndYear(competitionId, theYear);

            return ResponseUtil.createOkResponse(Standings);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and year parameters should be an integers", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and year parameters should be an integers");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
