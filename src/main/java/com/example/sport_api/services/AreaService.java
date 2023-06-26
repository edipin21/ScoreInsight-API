package com.example.sport_api.services;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sport_api.models.Area;
import com.example.sport_api.models.AreaDto;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
import com.example.sport_api.mappers.CompetitionMapper;
import com.example.sport_api.repositories.AreaRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class AreaService {

    private static final Logger logger = LogManager.getLogger(AreaService.class);

    @Autowired
    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> retrieveAllAreas() {
        return areaRepository.findAll();
    }

    public List<AreaDto> getAllAreasWithCompetitions() {
        try {
            List<Area> areas = areaRepository.findAll();
            List<AreaDto> areaDtos = new ArrayList<>();

            for (Area area : areas) {
                AreaDto areaDto = new AreaDto();
                areaDto.setAreaId(area.getAreaId());
                areaDto.setCountryCode(area.getCountryCode());
                areaDto.setName(area.getName());

                List<Competition> competitions = area.getCompetitions();
                List<CompetitionDto> competitionDtos = competitions.stream()
                        .map(CompetitionMapper::toDto)
                        .collect(Collectors.toList());

                areaDto.setCompetitions(competitionDtos);

                areaDtos.add(areaDto);
            }
            return areaDtos;

        } catch (DataAccessException e) {
            logger.error("Error occurred while accessing data while retrieving areas: " + e.getMessage(), e);
            throw e;
        }
    }
}
