package com.example.sport_api.area;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class areaService {

    @Autowired
    private areaRepository areaRepository;

    public areaService(areaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> retrieveAllAreas() {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Areas?key=2db931a014ad4b1e90d3f614e7927f11";

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl,
                String.class);
        String s = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        List<Area> areas = new ArrayList<>();

        try {

            areas = objectMapper.readValue(s, new TypeReference<List<Area>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        areaRepository.saveAll(areas);

        return areas;
    }

}
