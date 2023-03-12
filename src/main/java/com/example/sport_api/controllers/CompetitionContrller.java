package com.example.sport_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.models.Competition;
import com.example.sport_api.repositories.CompetitionRepository;

import java.util.List;

@RestController
public class CompetitionContrller {

    private CompetitionRepository competitionRepository;

    public CompetitionContrller(com.example.sport_api.repositories.CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @RequestMapping("/com")
    public List<Competition> retrieveAllTeams() {
        return competitionRepository.findAll();
    }

}
