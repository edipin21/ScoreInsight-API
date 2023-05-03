package com.example.sport_api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.CompetitionDetail;
import com.example.sport_api.services.CompetitionDetailService;

@RestController
public class CompetitionDetailController {

    private CompetitionDetailService competitionDetailService;

    public CompetitionDetailController(CompetitionDetailService competitionDetailService) {
        this.competitionDetailService = competitionDetailService;
    }

    @RequestMapping("/c/{competitionId}")
    public CompetitionDetail retriveCompetitionId(@PathVariable int competitionId) {
        return competitionDetailService.retrieveCompetitionDetailById(competitionId);
    }

}
