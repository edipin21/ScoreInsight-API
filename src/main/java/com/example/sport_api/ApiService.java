package com.example.sport_api;

import com.example.sport_api.models.Area;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.Game;
import com.example.sport_api.models.Team;
import com.example.sport_api.models.TeamDetail;
import com.example.sport_api.repositories.AreaRepository;
import com.example.sport_api.repositories.CompetitionRepository;
import com.example.sport_api.repositories.GameRepository;
import com.example.sport_api.repositories.TeamDetailRepository;
import com.example.sport_api.repositories.TeamRepository;
import com.example.sport_api.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    private final String teamsResourceUrl = "https://api.sportsdata.io/v3/soccer/scores/json/Teams?key=";
    private final String areasResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Areas?key=";
    private final String competitionsResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Competitions?key=";
    private final String competitionFixturesUrl = "https://api.sportsdata.io/v4/soccer/scores/json/CompetitionDetails/1?key=";

    Dotenv dotenv = Dotenv.load();

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    CompetitionService competitionService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamDetailRepository teamDetailRepository;

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

        areas = objectMapper.readValue(areasJson, new TypeReference<List<Area>>() {
        });

        try {
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

    public void fetchCompetitionFixturesAndUpdate() throws JsonProcessingException, IOException {

        try {
            String competitionFixturesJson = fetchData(competitionFixturesUrl);

            ObjectMapper objectMapper = initializeObjectMapper();

            Competition competition = objectMapper.readValue(competitionFixturesJson,
                    Competition.class);

            Optional<List<Game>> games = Optional.of(competition.getGames());
            List<Game> theGames = new ArrayList<>();
            if (games.isPresent()) {
                theGames = games.get();
                for (Game game : theGames) {
                    game.setCompetition(competition);
                }

            }
            Optional<List<TeamDetail>> teams = Optional.of(competition.getTeams());
            List<TeamDetail> theTeams = new ArrayList<>();
            if (teams.isPresent()) {
                System.out.println(teams.get().get(0));
                theTeams = teams.get();
                for (TeamDetail team : theTeams) {

                    team.setCompetition(competition);
                }

            }
            competitionRepository.save(competition);

            if (!theGames.isEmpty()) {
                gameRepository.saveAll(theGames);
            }
            if (!theTeams.isEmpty()) {
                teamDetailRepository.saveAll(theTeams);
            }
        } catch (IOException e) {
            if (e instanceof JsonProcessingException) {
                System.out.println("Error occurred during JSON processing: " + e.getMessage());
                throw e;
            } else {
                System.out.println("Error occurred during I/O operation: " + e.getMessage());
                throw e;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument provided: " + e.getMessage());
            throw e;

        } catch (DataAccessException e) {
            System.out.println("Error occurred during data access: " + e.getMessage());
            throw e;
        }
    }

}
