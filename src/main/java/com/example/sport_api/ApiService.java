package com.example.sport_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.sport_api.models.Area;
import com.example.sport_api.models.Competition;
// import com.example.sport_api.models.Competition;
// import com.example.sport_api.models.CompetitionDetail;
import com.example.sport_api.models.Team;
import com.example.sport_api.repositories.AreaRepository;
// import com.example.sport_api.repositories.CompetitionDetailRepository;
import com.example.sport_api.repositories.CompetitionRepository;
// import com.example.sport_api.repositories.CompetitionRepository;
import com.example.sport_api.repositories.TeamRepository;
import com.example.sport_api.services.CompetitionService;
// import com.example.sport_api.services.CompetitionDetailService;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {

    private final String teamsResourceUrl = "https://api.sportsdata.io/v3/soccer/scores/json/Teams?key=";
    private final String areasResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Areas?key=";
    private final String competitionsResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Competitions?key=";
    private final String competitionFixturesUrl = "https://api.sportsdata.io/v4/soccer/scores/json/CompetitionDetails/ucl?key=";

    Dotenv dotenv = Dotenv.load();

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    // @Autowired
    // private CompetitionDetailRepository competitionDetailRepository;
    // @Autowired
    // private CompetitionDetailService competitionDetailService;

    @Autowired
    CompetitionService competitionService;

    // Make the update more efficient
    public void fetchTeamsAndUpdate() throws JsonMappingException, JsonProcessingException {

        String teamsJson = fetchData(teamsResourceUrl);

        ObjectMapper objectMapper = initializeObjectMapper();

        List<Team> teams = new ArrayList<>();

        teams = objectMapper.readValue(teamsJson, new TypeReference<List<Team>>() {
        });

        teamRepository.saveAll(teams);
    }

    public void fetchAreasAndUpdate() throws JsonMappingException, JsonProcessingException {

        String areasJson = fetchData(areasResourceUrl);

        ObjectMapper objectMapper = initializeObjectMapper();

        List<Area> areas = new ArrayList<>();
        // Area[] areas;
        areas = objectMapper.readValue(areasJson, new TypeReference<List<Area>>() {
        });

        try {
            // areaRepository.saveAll(Arrays.asList(areas));
            areaRepository.saveAll(areas);

        } catch (Exception e) {
            System.out.println("the error is : -------------------------------");
            System.out.println(e.getMessage());
        }
    }

    public void fetchCompetitionsAndUpdate() throws JsonMappingException, JsonProcessingException {

        String areasJson = fetchData(competitionsResourceUrl);

        ObjectMapper objectMapper = initializeObjectMapper();

        List<Competition> competitions;

        competitions = objectMapper.readValue(areasJson, new TypeReference<List<Competition>>() {
        });

        competitionRepository.saveAll(competitions);

    }

    public String fetchData(String resourceUrl) {

        String apiKey = dotenv.get("SPORT_DATA_IO_API_KEY");

        System.out.println(resourceUrl + apiKey);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + apiKey,
                String.class);
        String responsesData = response.getBody();

        return responsesData;
    }

    public ObjectMapper initializeObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        return objectMapper;
    }

    public void fetchCompetitionFixturesAndUpdate() throws JsonMappingException,
            JsonProcessingException {

        String competitionFixturesJson = fetchData(competitionFixturesUrl);

        ObjectMapper objectMapper = initializeObjectMapper();

        Competition competition;

        competition = objectMapper.readValue(competitionFixturesJson,
                Competition.class);

        // CompetitionService.addCompetitionDetail1(competition);
        // competitionDetailRepository.save(competitionDetails);

        competitionRepository.save(competition);
    }
}
