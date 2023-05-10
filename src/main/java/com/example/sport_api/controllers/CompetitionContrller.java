package com.example.sport_api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionProjection;
import com.example.sport_api.repositories.CompetitionRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CompetitionContrller {

    private CompetitionRepository competitionRepository;

    public CompetitionContrller(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @RequestMapping("/com")
    public List<CompetitionProjection> retrieveAllTeams() {

        // return competitionRepository.findAllCompetitions();

        // return competitionRepository.findById(1).get();
        return competitionRepository.findAllCompetitions();
        // return List<CompetitionProjection> competitionProjections =
        // competitionRepository.findByCompetitionId(competitionId);
    }

    @RequestMapping("/c/{competitionId}")
    public Competition retriveCompetitionId(@PathVariable int competitionId) {
        Optional<Competition> competition = competitionRepository.findById(competitionId);

        if (competition.isPresent()) {
            return competition.get();
        } else {
            return null;
        }
    }

}
