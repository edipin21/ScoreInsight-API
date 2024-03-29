package com.example.sport_api.controllers.soccer;

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

import com.example.sport_api.constants.OpenApiParameters;
import com.example.sport_api.models.sport.Player;
import com.example.sport_api.services.soccer.PlayerService;
import com.example.sport_api.services.soccer.TeamService;
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
@Tag(name = "Players", description = "Endpoints for retrieving players information")
@RequestMapping("/soccer/scores")
public class playerController {

    private static final Logger logger = LogManager.getLogger(MembershipController.class);

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Operation(summary = " Get players by teamId. ", description = "Retrieves players by teamId.  \n"
            + "Recommended Call Interval: 1 Hour")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })

    @GetMapping("/PlayersByTeam/{teamId}")
    public ResponseEntity<?> retrivePlayersByTeamId(
            @Parameter(description = OpenApiParameters.TEAM_ID_DESCRIPTION) @PathVariable String teamId,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        try {
            Integer team = Integer.parseInt(teamId);

            if (teamId == null || !teamService.isTeamIdValid(team)) {
                throw new IllegalArgumentException("Invalid Argument: The teamId parameter is invalid ");
            }

            List<Player> players = playerService.retrievePlayersByTeam(team);

            return ResponseUtil.createOkResponse(players);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The teamId parameter should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The teamId parameter should be an integer");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
