package com.example.sport_api.controllers;

// import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.sport_api.models.Team;
import com.example.sport_api.services.TeamService;

@RestController
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping("/team/{teamId}")
    public Team retrieveTeamById(@PathVariable int teamId) {
        Team team = teamService.retrieveTeamById(teamId);
        if (team == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return team;
    }

    @RequestMapping("/teams")
    public List<Team> retrieveAllTeams() {
        return teamService.retrieveAllTeams();
    }

}

// @RequestMapping(value = "/team/add/{teamId}/{teamName}", method =
// RequestMethod.POST)
// public ResponseEntity<Object> addTeam(@PathVariable int teamId, @PathVariable
// String teamName) {
// teamService.addTeam(teamId, teamName);

// return ResponseEntity.noContent().build();
// }
