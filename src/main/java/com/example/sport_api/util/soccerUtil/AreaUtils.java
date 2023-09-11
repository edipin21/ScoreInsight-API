package com.example.sport_api.util.soccerUtil;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.mappers.CompetitionMapper;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.models.sport.AreaDto;
import com.example.sport_api.models.sport.CompetitionDto;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;

public class AreaUtils {

    public static void saveAreasToDatabase(List<Area> areas, AreaRepository areaRepository) throws DataAccessException {
        areaRepository.saveAll(areas);
    }

    public static void handleExternalApiException(Exception e) {
        ExternalApiDataFetcherUtil.handleException(e);
        throw new RuntimeException("Failed to synchronize areas from external API", e);
    }

    public static AreaDto createAreaDtoFromArea(Area area) {
        if (area == null)
            return null;

        AreaDto areaDto = new AreaDto();
        areaDto.setAreaId(area.getAreaId());
        areaDto.setCountryCode(area.getCountryCode());
        areaDto.setName(area.getName());

        List<CompetitionDto> competitionDtos = area.getCompetitions().stream()
                .map(CompetitionMapper::toDto)
                .collect(Collectors.toList());

        areaDto.setCompetitions(competitionDtos);

        return areaDto;
    }
}
