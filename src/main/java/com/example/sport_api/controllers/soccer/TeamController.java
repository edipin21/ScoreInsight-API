package com.example.sport_api.controllers.soccer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.config.OpenApiParameters;
import com.example.sport_api.models.sport.Team;
import com.example.sport_api.services.soccer.TeamService;
import com.example.sport_api.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Team", description = "Endpoints for retrieving teams information")
@RestController
@RequestMapping("/soccer/scores")
public class TeamController {

    private static final Logger logger = LogManager.getLogger(TeamController.class);

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Operation(summary = "Get team by id", description = "Retrieves team by id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(schema = @Schema(implementation = Team.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> retrieveTeamById(
            @PathVariable @Parameter(description = OpenApiParameters.TEAM_ID_DESCRIPTION) String teamId,
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {
        try {
            Integer id = Integer.parseInt(teamId);

            if (teamId == null || !teamService.isTeamIdValid(id)) {
                throw new IllegalArgumentException("Invalid Argument: The teamId parameter is invalid ");
            }

            Team team = teamService.retrieveTeamById(id);

            return ResponseUtil.createOkResponse(team);
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

    @Operation(summary = "Get all teams", description = "Retrieves a list of all teams available.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(schema = @Schema(implementation = Team.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/teams")
    public ResponseEntity<?> retrieveAllTeams(
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {
        try {
            List<Team> teams = teamService.retrieveAllTeams();

            return ResponseUtil.createOkResponse(teams);

        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }

    }

}
