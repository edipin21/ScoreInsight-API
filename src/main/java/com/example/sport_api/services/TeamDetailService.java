package com.example.sport_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Competition;
import com.example.sport_api.models.TeamDetail;
import com.example.sport_api.repositories.TeamDetailRepository;

@Service
public class TeamDetailService {

    @Autowired
    private TeamDetailRepository teamDetailRepository;

    // public List<Competition> getTeamDetailByComptitionId() {

    // List<TeamDetail> teams = teamDetailRepository.findAll();

    // List<Competition> competitions = teams.get(0).getCompetition();

    // return competitions;
    // }

}
