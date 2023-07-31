package com.example.sport_api.controllers.soccer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.sport.Team;
import com.example.sport_api.services.soccer.TeamService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class TeamController {

    private static final Logger logger = LogManager.getLogger(TeamController.class);

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> retrieveTeamById(@PathVariable String teamId) {
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

    @GetMapping("/teams")
    public List<Team> retrieveAllTeams() {
        return teamService.retrieveAllTeams();
    }

}
