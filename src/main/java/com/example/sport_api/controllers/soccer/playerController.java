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
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.sport.Player;
import com.example.sport_api.services.soccer.PlayerService;
import com.example.sport_api.services.soccer.TeamService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class playerController {

    private static final Logger logger = LogManager.getLogger(MembershipController.class);

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/scores/PlayersByTeam/{teamId}")
    public ResponseEntity<List<Player>> retrivePlayersByTeamId(@PathVariable String teamId) {

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
