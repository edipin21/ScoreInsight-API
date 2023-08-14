package com.example.sport_api.services.soccer;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.models.sport.AreaDto;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.soccerUtil.AreaUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class AreaService {

    private static final Logger logger = LogManager.getLogger(AreaService.class);

    @Autowired
    private AreaRepository areaRepository;

    public void syncAreasFromExternalApi() throws JsonProcessingException {
        try {
            TypeReference<List<Area>> areaTypeRef = new TypeReference<>() {
            };

            List<Area> areas = ExternalApiDataFetcherUtil
                    .fetchListDataFromExternalApi(ExternalSoccerApiEndpoints.AREAS_RESOURCE_URL, areaTypeRef);

            AreaUtils.saveAreasToDatabase(areas, areaRepository);

        } catch (Exception e) {
            AreaUtils.handleExternalApiException(e);
            throw e;
        }
    }

    public List<Area> retrieveAllAreas() {
        try {
            return areaRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Error occurred while accessing data while retrieving areas: " + e.getMessage(), e);
            throw e;
        }

    }

    public List<AreaDto> getAllAreasWithCompetitions() {
        try {
            List<Area> areas = areaRepository.findAll();
            List<AreaDto> areaDtos = new ArrayList<>();

            for (Area area : areas) {
                AreaDto areaDto = AreaUtils.createAreaDtoFromArea(area);
                areaDtos.add(areaDto);
            }

            return areaDtos;

        } catch (DataAccessException e) {
            logger.error("Error occurred while accessing data while retrieving areas: " + e.getMessage(), e);
            throw e;
        }
    }
}
