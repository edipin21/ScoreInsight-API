package com.example.sport_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.sport_api.team.Team;
import com.example.sport_api.team.TeamRepository;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    private TeamRepository teamRepository;

    // Make the update more efficient
    public void fetchTeamsAndUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.sportsdata.io/v3/soccer/scores/json/Teams?key=2db931a014ad4b1e90d3f614e7927f11";

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl,
                String.class);
        String s = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        List<Team> teams = new ArrayList<>();

        try {

            teams = objectMapper.readValue(s, new TypeReference<List<Team>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        teamRepository.saveAll(teams);
    }
}
