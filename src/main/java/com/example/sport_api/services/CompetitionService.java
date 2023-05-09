package com.example.sport_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Competition;
import com.example.sport_api.repositories.CompetitionRepository;

import jakarta.transaction.Transactional;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Transactional
    public void addCompetitionDetail1(Competition competition) {

        Optional<Competition> com = competitionRepository.findById(competition.getCompetitionId());

        if (com.isPresent()) {
            Competition exsistedCompetition = com.get();

            exsistedCompetition.setCurrentSeason(competition.getCurrentSeason());
            competitionRepository.save(exsistedCompetition);
        }

    }

    // @Transactional
    // public void addCompetitionDetail(CompetitionDetail competitionDetail) {
    // // check if the parent entity exists in the database
    // Competition existingCompetition =
    // if (existingCompetition != null) {
    // // check if the child entity already exists in the database
    // CompetitionDetail existingCompetitionDetail =
    // entityManager.find(CompetitionDetail.class,
    // competitionDetail.getCompetitionId());
    // if (existingCompetitionDetail == null) {
    // entityManager.merge(competitionDetail);
    // } else {
    // // update the existing entity with the new data

    // existingCompetitionDetail.setCurrentSeason(competitionDetail.getCurrentSeason());
    // // existingCompetitionDetail.setTeams(competitionDetail.getTeams());
    // // existingCompetitionDetail.setGames(competitionDetail.getGames());
    // // entityManager.merge(existingCompetitionDetail);
    // // }
    // // }
    // // }
}
