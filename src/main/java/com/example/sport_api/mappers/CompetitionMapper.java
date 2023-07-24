package com.example.sport_api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.CompetitionDto;

public class CompetitionMapper {

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        configureMappings();
    }

    private static void configureMappings() {
        modelMapper.addMappings(new PropertyMap<Competition, CompetitionDto>() {
            @Override
            protected void configure() {
                map().setAreaName(source.getArea().getName());
            }
        });
    }

    public static CompetitionDto toDto(Competition competition) {
        return modelMapper.map(competition, CompetitionDto.class);
    }

    public static List<CompetitionDto> toDtoList(List<Competition> competitions) {
        return competitions.stream()
                .map(CompetitionMapper::toDto)
                .collect(Collectors.toList());
    }
}