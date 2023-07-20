package com.example.sport_api;

import com.example.sport_api.models.Area;
import com.example.sport_api.models.BoxScore;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.Game;
import com.example.sport_api.models.Membership;
import com.example.sport_api.models.Player;
import com.example.sport_api.models.Round;
import com.example.sport_api.models.Season;
import com.example.sport_api.models.Team;
import com.example.sport_api.models.TeamDetail;
import com.example.sport_api.models.Venue;
import com.example.sport_api.repositories.AreaRepository;
import com.example.sport_api.repositories.BoxScoreRepository;
import com.example.sport_api.repositories.CompetitionRepository;
import com.example.sport_api.repositories.GameRepository;
import com.example.sport_api.repositories.MembershipRepository;
import com.example.sport_api.repositories.PlayerRepository;
import com.example.sport_api.repositories.RoundRepository;
import com.example.sport_api.repositories.TeamDetailRepository;
import com.example.sport_api.repositories.TeamRepository;
import com.example.sport_api.repositories.VenueRepository;
import com.example.sport_api.services.GameService;
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
import java.util.Map;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

//need to add the HistoricalMembershipsByTeam function 
@Service
public class DataSyncService {

    private static final Logger logger = LogManager.getLogger(DataSyncService.class);

    private final String teamsResourceUrl = "https://api.sportsdata.io/v3/soccer/scores/json/Teams?key=";
    private final String areasResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Areas?key=";
    private final String competitionsResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Competitions?key=";
    private final String competitionFixturesResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/CompetitionDetails/";
    private final String activeMembershipResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/ActiveMemberships/";
    private final String recentlyChangedMembershipResourceUrl = "https://api.sportsdata.io/v4/soccer/scores/json/RecentlyChangedMemberships/";
    private final String plyersByTeamResourcUrl = "https://api.sportsdata.io/v4/soccer/scores/json/PlayersByTeam/";
    private final String scheduleResourcUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Schedule/";
    private final String standingsResourcUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Standings/";
    private final String teamSeasonStateResourcUrl = "https://api.sportsdata.io/v4/soccer/scores/json/TeamSeasonStats/";
    private final String venuesResourcUrl = "https://api.sportsdata.io/v4/soccer/scores/json/Venues?key=";
    private final String boxScoreResourcUrl = "https://api.sportsdata.io/v4/soccer/stats/json/BoxScore/";

    Dotenv dotenv = Dotenv.load();

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    // @Autowired
    // private CompetitionService competitionService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamDetailRepository teamDetailRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private BoxScoreRepository boxScoreRepository;

    @Autowired
    private GameService gameService;

    public void fetchTeamsAndUpdate() throws JsonMappingException,
            JsonProcessingException {

        try {
            String teamsJson = fetchData(teamsResourceUrl);

            ObjectMapper objectMapper = initializeObjectMapper();

            List<Team> teams = new ArrayList<>();

            teams = objectMapper.readValue(teamsJson, new TypeReference<List<Team>>() {
            });

            System.out.println(teams.get(0));
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
            for (Competition competition : competitions) {
                List<Season> seasons = competition.getSeasons();
                for (Season season : seasons) {
                    List<Round> sRounds = season.getRounds();
                    sRounds.forEach(round -> round.setCompetitionId(competition.getCompetitionId()));
                }
            }

            competitionRepository.saveAll(competitions);
        } catch (Exception e) {
            handleException(e);
            throw e;
        }

    }

    public void fetchCompetitionFixturesAndUpdate() throws JsonProcessingException, IOException {

        try {
            int count = 0;

            List<Integer> competitionIntegers = competitionRepository.findAllCompetitionIds();
            competitionIntegers.sort(null);

            // Partial loop for sanity checks - delete count
            for (Integer competitionId : competitionIntegers) {
                if (count == 3) {
                    break;
                }

                String competitionFixturesJson = fetchData(competitionFixturesResourceUrl +
                        competitionId + "?key=");

                ObjectMapper objectMapper = initializeObjectMapper();

                Competition competition = objectMapper.readValue(competitionFixturesJson,
                        Competition.class);

                List<Competition> competitions = new ArrayList<>();
                competitions.add(competition);

                Optional<List<Game>> games = Optional.of(competition.getGames());
                List<Game> theGames = new ArrayList<>();
                if (games.isPresent()) {
                    theGames = games.get();
                    theGames.forEach(game -> game.setCompetition(competition));
                }

                Optional<List<TeamDetail>> teams = Optional.of(competition.getTeams());
                List<TeamDetail> theTeams = new ArrayList<>();
                if (teams.isPresent()) {
                    theTeams = teams.get();
                    theTeams.forEach(team -> team.setCompetition(competitions));
                    for (TeamDetail team : theTeams) {
                        team.setPlayers(new ArrayList<>());
                    }

                }
                for (Competition theCompetition : competitions) {
                    List<Season> seasons = theCompetition.getSeasons();
                    for (Season season : seasons) {
                        List<Round> rounds = season.getRounds();
                        rounds.forEach(round -> round.setCompetitionId(competition.getCompetitionId()));
                    }
                }

                competitionRepository.save(competition);

                if (!theGames.isEmpty()) {
                    gameRepository.saveAll(theGames);
                }
                if (!theTeams.isEmpty()) {
                    teamDetailRepository.saveAll(theTeams);
                }
                count++;
            }

        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument provided: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public void fetchActiveMembershipAndUpdate() throws JsonProcessingException {

        try {

            int count = 0;

            ObjectMapper objectMapper = initializeObjectMapper();
            List<Integer> competitioIntegers = competitionRepository.findAllCompetitionIds();
            competitioIntegers.sort(null);
            // Partial loop for sanity checks - delete count
            for (Integer competitionId : competitioIntegers) {

                if (count == 3) {
                    break;
                }

                String activeMembershipJson = fetchData(activeMembershipResourceUrl + competitionId + "?key=");

                List<Membership> activeMemberships = new ArrayList<>();

                activeMemberships = objectMapper.readValue(activeMembershipJson, new TypeReference<List<Membership>>() {
                });

                activeMemberships.forEach(membership -> membership.setCompetitionId(3));

                membershipRepository.saveAll(activeMemberships);

                count++;
            }

        } catch (Exception e) {
            handleException(e);
            throw e;
        }

    }

    public void fetchRecentlyChangedMembershipAndUpdate() throws JsonProcessingException {

        try {
            int count = 0;

            ObjectMapper objectMapper = initializeObjectMapper();
            List<Integer> competitioIntegers = competitionRepository.findAllCompetitionIds();
            int numOfDays = 30;
            competitioIntegers.sort(null);

            // Partial loop for sanity checks - delete count

            for (Integer competitionId : competitioIntegers) {
                if (count == 3) {
                    break;
                }

                String recentlyChangedMembershipsJson = fetchData(
                        recentlyChangedMembershipResourceUrl + competitionId + "/" + numOfDays + "?key=");

                List<Membership> recentlyChangedMemberships = new ArrayList<>();

                recentlyChangedMemberships = objectMapper.readValue(recentlyChangedMembershipsJson,
                        new TypeReference<List<Membership>>() {
                        });

                recentlyChangedMemberships.forEach(membership -> membership.setCompetitionId(3));

                membershipRepository.saveAll(recentlyChangedMemberships);

                count++;
            }
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public void fetchPlayersbyTeamsAndUpdate() throws JsonProcessingException {

        try {
            int count = 0;

            ObjectMapper objectMapper = initializeObjectMapper();

            List<TeamDetail> teams = teamDetailRepository.findAll();

            // Partial loop for sanity checks - delete count
            for (int i = 0; i < teams.size(); i++) {

                if (count == 3) {
                    break;
                }
                List<Player> players = new ArrayList<>();

                String playersbyTeamJson = fetchData(
                        plyersByTeamResourcUrl +
                                teams.get(i).getCompetition().get(0).getCompetitionId() + "/"
                                + teams.get(i).getTeamId()
                                + "?key=");

                players = objectMapper.readValue(playersbyTeamJson,
                        new TypeReference<List<Player>>() {
                        });
                int teamNum = i;
                players.forEach(player -> player.setTeamDetail(teams.get(teamNum)));

                playerRepository.saveAll(players);

                count++;

            }
        } catch (Exception e) {
            handleException(e);
            throw e;
        }

    }

    public void fetchAndUpdateScheduleAndStandingsAndTeamSeason() throws JsonProcessingException {

        try {
            int count = 0;

            int[] seasonsArr = { 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024 };
            ObjectMapper objectMapper = initializeObjectMapper();

            List<Integer> competitioIntegers = competitionRepository.findAllCompetitionIds();
            competitioIntegers.sort(null);

            // Partial loop for sanity checks - delete count
            for (Integer competitonId : competitioIntegers) {
                if (count == 10) {
                    break;
                }

                for (int i = 0; i < seasonsArr.length; i++) {
                    count++;
                    String scheduleJson = fetchData(
                            scheduleResourcUrl + competitonId + "/"
                                    + seasonsArr[i]
                                    + "?key=");

                    List<Round> rounds = new ArrayList<>();

                    List<Round> standingsRounds = new ArrayList<>();

                    List<Round> teamSeasonRounds = new ArrayList<>();

                    rounds = objectMapper.readValue(scheduleJson, new TypeReference<List<Round>>() {
                    });

                    rounds.forEach(round -> round.setCompetitionId(competitioIntegers.get(0)));

                    String standingsJson = fetchData(
                            standingsResourcUrl + 1 + "/"
                                    + seasonsArr[i]
                                    + "?key=");
                    standingsRounds = objectMapper.readValue(standingsJson, new TypeReference<List<Round>>() {
                    });

                    standingsRounds.forEach(round -> round.setCompetitionId(competitioIntegers.get(0)));

                    String teamSeasonJson = fetchData(teamSeasonStateResourcUrl + 1 + '/' + seasonsArr[i] + "?key=");

                    teamSeasonRounds = objectMapper.readValue(teamSeasonJson, new TypeReference<List<Round>>() {
                    });
                    teamSeasonRounds.forEach(round -> round.setCompetitionId(competitioIntegers.get(0)));

                    for (int j = 0; j < rounds.size(); j++) {
                        rounds.get(i).setStandings(standingsRounds.get(i).getStandings());
                        rounds.get(i).setTeamSeasons(teamSeasonRounds.get(i).getTeamSeasons());
                    }

                    roundRepository.saveAll(rounds);
                }
                count++;

            }

        } catch (Exception e) {
            handleException(e);
            throw e;
        }

    }

    public void fetchVenuesAndUpdate() throws JsonProcessingException {
        try {
            String venuesJson = fetchData(venuesResourcUrl);

            ObjectMapper objectMapper = initializeObjectMapper();

            List<Venue> venues = new ArrayList<>();

            venues = objectMapper.readValue(venuesJson, new TypeReference<List<Venue>>() {
            });

            venueRepository.saveAll(venues);

        } catch (Exception e) {
            handleException(e);
        }
    }

    public void fetchAndUpdateBoxScore() throws JsonProcessingException {

        List<Object[]> gameIdToCompetitionMap = gameRepository.findGameIdAndCompetitionId();

        Map<Integer, Integer> map = gameService.getGameIdAndCompetitionIdMap();

        // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        // System.out.println("Key: " + entry.getKey() + ", Value: " +
        // entry.getValue());
        // }

        ObjectMapper objectMapper = initializeObjectMapper();
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (count == 2) {
                break;
            }
            Integer competitionId = entry.getValue();
            Integer gameId = entry.getKey();
            System.out.println(competitionId + "      " + gameId);
            String boxScoreFixturesJson = fetchData(boxScoreResourcUrl + competitionId + "/" + gameId
                    + "?key=");

            List<BoxScore> boxScores = objectMapper.readValue(boxScoreFixturesJson,
                    new TypeReference<List<BoxScore>>() {
                    });
            boxScores.get(0).setBoxScoreId(BoxScoreIdGenerator.generateId());

            boxScoreRepository.saveAll(boxScores);

            System.out.println(boxScoreRepository.findAll().get(0));

            count++;

        }

        // List<BoxScore> boxScores = objectMapper.readValue(boxScoreFixturesJson, new
        // TypeReference<List<BoxScore>>() {
        // });

        // Integer id = 2;
        // boxScores.get(0).setBoxScoreId(id);

        // boxScoreRepository.saveAll(boxScores);

        // List<BoxScore> b = boxScoreRepository.findAll();

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

}
