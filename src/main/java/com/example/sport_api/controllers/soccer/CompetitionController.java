package com.example.sport_api.controllers.soccer;

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
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.CompetitionDto;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Compettion", description = "Endpoints for retrieving competitons information")
@RestController
@RequestMapping("/soccer/scores")
public class CompetitionController {

    private static final Logger logger = LogManager.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionService competitionService;

    @Operation(summary = "Competitions (Leagues)", description = "Retrieves a list of all competitions available.  \n"
            + "Recommended Call Interval: 1 Hour ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CompetitionDto.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/competitions")
    public ResponseEntity<?> retrieveAllCompetitions(
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        try {
            List<CompetitionDto> competitions = competitionService.getAllCompetitions();
            return ResponseUtil.createOkResponse(competitions);
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Operation(summary = "Competition Fixtures (League Details)", description = "Retrieves competition by id.  \n"
            + "Recommended Call Interval: 1 Hour")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(schema = @Schema(implementation = Competition.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/CompetitionDetails/{competition}")
    public ResponseEntity<?> retrieveCompetitionById(
            @PathVariable @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) String competition,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {
        try {

            Integer competitionId = Integer.parseInt(competition);

            if (competitionId == null ||
                    !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }
            Competition theCompetition = competitionService.getCompetitionById(competitionId);

            return ResponseUtil.createOkResponse(theCompetition);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition parameter should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition parameter should be an integer");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
