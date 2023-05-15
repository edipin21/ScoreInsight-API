package com.example.sport_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
import com.example.sport_api.repositories.CompetitionRepository;
import com.example.sport_api.services.CompetitionService;

import java.util.List;
import java.util.Optional;

@RestController
public class CompetitionContrller {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionService competitionService;

    public CompetitionContrller(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @RequestMapping("/com")
    public List<CompetitionDto> retrieveAllTeams() {

        // return competitionRepository.findAllCompetitions();

        // return competitionRepository.findById(1).get();
        // return competitionRepository.findAll();
        // return competitionRepository.findAllCompetitions();
        return competitionService.getAllCompetitions();
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
