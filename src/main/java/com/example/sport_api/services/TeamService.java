package com.example.sport_api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Team;
import com.example.sport_api.repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team retrieveTeamById(int teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        if (optionalTeam.isEmpty()) {
            return null;
        }

        return optionalTeam.get();
    }

    public List<Team> retrieveAllTeams() {
        return teamRepository.findAll();
    }

}
