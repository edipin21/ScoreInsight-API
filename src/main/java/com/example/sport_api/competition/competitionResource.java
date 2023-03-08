package com.example.sport_api.competition;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
public class competitionResource {

    private competitionRepository competitionRepository;

    public competitionResource(com.example.sport_api.competition.competitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @RequestMapping("/com")
    public List<Competition> retrieveAllTeams() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Competitions?key=2db931a014ad4b1e90d3f614e7927f11";

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl,
                String.class);
        String s = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        List<Competition> competitions = new ArrayList<>();

        try {

            competitions = objectMapper.readValue(s, new TypeReference<List<Competition>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        competitionRepository.saveAll(competitions);

        return competitions;
    }

}
