package com.example.sport_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
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

    public List<CompetitionDto> getAllCompetitions() {

        List<Competition> competitions = competitionRepository.findAll();

        List<CompetitionDto> competitionDtos = new ArrayList<>();

        for (Competition competition : competitions) {

            CompetitionDto competitionDto = new CompetitionDto();

            competitionDto.setAreaId(competition.getAreaId());
            competitionDto.setAreaName(competition.getAreaName());
            competitionDto.setCompetitionId(competition.getCompetitionId());
            competitionDto.setFormat(competition.getFormat());
            competitionDto.setGender(competition.getGender());
            competitionDto.setName(competition.getName());
            competitionDto.setSeasons(competition.getSeasons());
            competitionDto.setStringKey(competition.getStringKey());
            competitionDto.setType(competition.getType());

            competitionDtos.add(competitionDto);

        }

        return competitionDtos;
    }

}
