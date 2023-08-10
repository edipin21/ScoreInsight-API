package com.example.sport_api.controllers.soccer;

import java.util.List;
import java.time.format.DateTimeParseException;
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

import com.example.sport_api.constants.OpenApiParameters;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

@Tag(name = "Game", description = "Endpoints for retrieving games information")
@RestController
@RequestMapping("/soccer/scores")
public class GameController {

    private static final Logger logger = LogManager.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private CompetitionService competitionService;

    @Operation(summary = " Get games by competition and date. ", description = "Retrieves games by competitioId and date.  \n"
            + "Recommended Call Interval: 5 Seconds")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Game.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })

    @GetMapping("/gamesByDate/{competition}/{date}")
    public ResponseEntity<?> retriveGamesByCompetitionIdAndDate(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.DATE_FORMAT_DESCRIPTION) @PathVariable String date,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        try {

            Integer theCompetition = Integer.parseInt(competition);

            if (date == null || !DateUtils.isValidDate(date)) {
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
