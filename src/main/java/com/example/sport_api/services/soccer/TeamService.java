package com.example.sport_api.services.soccer;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.sport.Team;
import com.example.sport_api.repositories.soccer.TeamRepository;

@Service
public class TeamService {

    private static final Logger logger = LogManager.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team retrieveTeamById(int teamId) {

        try {
            Optional<Team> optionalTeam = teamRepository.findById(teamId);

            return optionalTeam.get();
        } catch (DataAccessException e) {
            logger.error("A data access error occurred will retrivien team " + e.getMessage());

            throw e;
        }

    }

    public List<Team> retrieveAllTeams() {
        try {
            List<Team> teams = teamRepository.findAll();
            return teams;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while retrieving teams  " + e.getMessage());
            throw e;
        }

    }

    public boolean isTeamIdValid(Integer teamId) {
        try {
            List<Integer> allTeamsIds = teamRepository.findAllTeamsIds();
            return allTeamsIds.contains(teamId);
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }
    }

}
