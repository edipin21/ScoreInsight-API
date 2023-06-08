package com.example.sport_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.exceptions.NotFoundException;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
import com.example.sport_api.repositories.CompetitionRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

@Service
public class CompetitionService {

    private static final Logger logger = LogManager.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
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

    public Competition getCompetitionById(int competitionId) {
        try {
            Optional<Competition> competition = competitionRepository.findById(competitionId);

            if (competition.isPresent()) {
                return competition.get();
            } else {
                throw new NotFoundException("Competition not found");
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARN, "Competition not found for ID: " + competitionId, e);
            throw e;
        }

    }
}
