package com.example.sport_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.exceptions.NotFoundException;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.CompetitionDto;
import com.example.sport_api.repositories.soccer.CompetitionRepository;

import org.apache.logging.log4j.Logger;
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

        try {
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
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while retrieving competitions: " + e.getMessage());
            throw e;
        }

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
            logger.error("Competition not found for ID: " + competitionId, e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }

    }

    public boolean isCompetitionValid(Integer competitionId) {
        try {
            List<Integer> allCompetitionIds = competitionRepository.findAllCompetitionsNumbers();
            return allCompetitionIds.contains(competitionId);
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while validating competition ID: " + e.getMessage());
            throw e;
        }

    }
}
// catch (NotFoundException e) {
// logger.error("Competition not found: " + e.getMessage());
// return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND,
// e.getMessage());
// }