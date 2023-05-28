package com.example.sport_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.mappers.CompetitionMapper;
import com.example.sport_api.models.Area;
import com.example.sport_api.models.AreaDto;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
import com.example.sport_api.repositories.AreaRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> retrieveAllAreas() {
        return areaRepository.findAll();
    }

    public List<AreaDto> getAllAreasWithCompetitions() {

        // the error is here!!!!!!!!!!!!!!
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
    }

}
