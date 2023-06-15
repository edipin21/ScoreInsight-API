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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class DataSyncService {

    private static final Logger logger = LogManager.getLogger(DataSyncService.class);

    private final String teamsResourceUrl = "https://api.sportsdata.io/v3/soccer/scores/json/Teams?key=";
    private final String areasResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Areas?key=";
    private final String competitionsResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Competitions?key=";
    private final String competitionFixturesUrl = "https://api.sportsdata.io/v4/soccer/scores/json/CompetitionDetails/3?key=";

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

    public void fetchTeamsAndUpdate() throws JsonMappingException,
            JsonProcessingException {

        try {
            String teamsJson = fetchData(teamsResourceUrl);

            ObjectMapper objectMapper = initializeObjectMapper();

            List<Team> teams = new ArrayList<>();

            teams = objectMapper.readValue(teamsJson, new TypeReference<List<Team>>() {
            });

            teamRepository.saveAll(teams);
        } catch (Exception e) {
            handleException(e);
            throw e;
        }

    }

    public void fetchAreasAndUpdate() throws JsonProcessingException {
        try {
            String areasJson = fetchData(areasResourceUrl);

            ObjectMapper objectMapper = initializeObjectMapper();

            List<Area> areas = new ArrayList<>();

            areas = objectMapper.readValue(areasJson, new TypeReference<List<Area>>() {
            });

            areaRepository.saveAll(areas);

        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public void fetchCompetitionsAndUpdate() throws JsonProcessingException {

        try {
            String areasJson = fetchData(competitionsResourceUrl);

            ObjectMapper objectMapper = initializeObjectMapper();

            List<Competition> competitions;

            competitions = objectMapper.readValue(areasJson, new TypeReference<List<Competition>>() {
            });

            competitionRepository.saveAll(competitions);
        } catch (Exception e) {
            handleException(e);
            throw e;
        }

    }

    public String fetchData(String resourceUrl) {

        String apiKey = dotenv.get("SPORT_DATA_IO_API_KEY");

        System.out.println(resourceUrl + apiKey);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl +
                apiKey,
                String.class);
        String responsesData = response.getBody();

        return responsesData;
    }

    public ObjectMapper initializeObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
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

            System.out.println(competition.getGames().get(0));
            Optional<List<Game>> games = Optional.of(competition.getGames());
            List<Game> theGames = new ArrayList<>();
            if (games.isPresent()) {
                System.out.println("game is present");
                theGames = games.get();
                for (Game game : theGames) {
                    System.out.println(1);
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
                System.out.println("work1");
                gameRepository.saveAll(theGames);
            }
            if (!theTeams.isEmpty()) {
                System.out.println("work2");
                teamDetailRepository.saveAll(theTeams);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument provided: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public void handleException(Exception e) {
        if (e instanceof JsonProcessingException) {
            logger.error("Error occurred during JSON processing: {}", e.getMessage(), e);
        } else if (e instanceof IOException) {
            logger.error("Error occurred during I/O operation: {}", e.getMessage(), e);
        } else if (e instanceof DataAccessException) {
            logger.error("Error occurred during data access: {}", e.getMessage(), e);
        }

    }

}
