package com.example.sport_api;

import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.models.sport.BoxScore;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.models.sport.Membership;
import com.example.sport_api.models.sport.Player;
import com.example.sport_api.models.sport.PlayerGame;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.models.sport.Team;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.repositories.soccer.BoxScoreRepository;
import com.example.sport_api.repositories.soccer.CompetitionRepository;
import com.example.sport_api.repositories.soccer.GameRepository;
import com.example.sport_api.repositories.soccer.MembershipRepository;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.repositories.soccer.RoundRepository;
import com.example.sport_api.repositories.soccer.TeamDetailRepository;
import com.example.sport_api.repositories.soccer.TeamRepository;
import com.example.sport_api.repositories.soccer.VenueRepository;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.GameService;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.TimeMeasurementUtil;
import com.example.sport_api.util.soccerUtil.CompetitionProcessingAsyncUtil;
import com.example.sport_api.util.soccerUtil.CompetitionUtils;
import com.example.sport_api.util.soccerUtil.PlayerGameUtils;
import com.example.sport_api.util.soccerUtil.RoundUtils;
import com.example.sport_api.util.soccerUtil.TeamDetailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SoccerDataSyncService {

    static final int[] seasonsArr = { 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024 };

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

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

    @Autowired
    private CompetitionService competitionService;

    public void fetchTeamsAndUpdate() throws JsonMappingException,
            JsonProcessingException {

        try {
            String teamsJson = ExternalApiDataFetcherUtil.fetchData(ExternalSoccerApiEndpoints.TEAMS_RESOURCE_URL);

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();

            List<Team> teams = new ArrayList<>();

            TypeReference<List<Team>> teamTypeRef = new TypeReference<>() {
            };

            teams = objectMapper.readValue(teamsJson, teamTypeRef);
            teamRepository.saveAll(teams);
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void fetchAreasAndUpdate() throws JsonProcessingException {
        try {
            String areasJson = ExternalApiDataFetcherUtil.fetchData(ExternalSoccerApiEndpoints.AREAS_RESOURCE_URL);

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();

            List<Area> areas = new ArrayList<>();

            TypeReference<List<Area>> areaTypeRef = new TypeReference<>() {
            };

            areas = objectMapper.readValue(areasJson, areaTypeRef);

            areaRepository.saveAll(areas);

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }
    }

    public void fetchCompetitionsAndUpdate() throws JsonProcessingException {

        try {
            TimeMeasurementUtil.startTimer();
            String areasJson = ExternalApiDataFetcherUtil
                    .fetchData(ExternalSoccerApiEndpoints.COMPETITIONS_RESOURCE_URL);

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();

            List<Competition> competitions;

            TypeReference<List<Competition>> competitionTypeRef = new TypeReference<>() {
            };

            competitions = objectMapper.readValue(areasJson, competitionTypeRef);

            CompetitionProcessingAsyncUtil.setCompetitionsIdToRoundsParallel(competitions);

            competitionRepository.saveAll(competitions);
            TimeMeasurementUtil.timeTaken();

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void fetchCompetitionFixturesAndUpdate() throws JsonProcessingException, IOException {

        try {
            TimeMeasurementUtil.startTimer();
            int count = 0;
            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            List<Integer> competitionIntegers = competitionService.getSortedCompetitionIds();

            // Partial loop for sanity checks - delete count
            for (Integer competitionId : competitionIntegers) {
                if (count == 3) {
                    break;
                }

                String competitionFixturesJson = ExternalApiDataFetcherUtil
                        .fetchData(ExternalSoccerApiEndpoints.COMPETITION_FIXTURES_RESOURCE_URL +
                                competitionId);

                Competition competition = objectMapper.readValue(competitionFixturesJson,
                        Competition.class);

                List<Game> gameWithCompetitionId = CompetitionUtils.setCompetitionIdToGames(competition);

                List<TeamDetail> teamsWithCompetitionIdAndPlayers = CompetitionUtils
                        .setCompetitionIdToTeamsDetail(competition);
                TeamDetailUtils.setPlayersToTeams(teamsWithCompetitionIdAndPlayers, playerRepository);

                CompetitionUtils.setCompetitionIdToRounds(competition);

                competitionRepository.save(competition);

                if (!gameWithCompetitionId.isEmpty()) {
                    gameRepository.saveAll(gameWithCompetitionId);
                }
                if (!teamsWithCompetitionIdAndPlayers.isEmpty()) {
                    teamDetailRepository.saveAll(teamsWithCompetitionIdAndPlayers);
                }
                count++;
            }
            TimeMeasurementUtil.timeTaken();
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }
    }

    public void fetchCompetitionFixturesAndUpdateAsyncParallel() throws JsonProcessingException, IOException {
        try {
            TimeMeasurementUtil.startTimer();
            List<Integer> competitionIds = competitionService.getSortedCompetitionIds();
            List<String> competitionFixtureJsons = new ArrayList<>();
            List<Competition> competitionFixtures = new ArrayList<>();

            competitionFixtureJsons = CompetitionProcessingAsyncUtil.getCompetitionFixtureJsonsParallel(competitionIds);

            competitionFixtures = CompetitionProcessingAsyncUtil
                    .deserializeCompetitionFixturesParallel(competitionFixtureJsons);

            List<CompletableFuture<Void>> competitionProcessingFutures = competitionFixtures.parallelStream()
                    .map(competition -> {
                        return CompetitionProcessingAsyncUtil.processCompetitionFixtureAndSaveToDBAsync(
                                competition, playerRepository, competitionRepository, teamDetailRepository,
                                gameRepository);
                    }).collect(Collectors.toList());

            CompletableFuture<Void> allCompetitionsProcessingFutures = CompletableFuture.allOf(
                    competitionProcessingFutures.toArray(new CompletableFuture[0]));

            allCompetitionsProcessingFutures.join();

            TimeMeasurementUtil.timeTaken();
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }
    }

    public void fetchActiveMembershipAndUpdate() throws JsonProcessingException {

        try {

            int count = 0;
            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            List<Integer> competitioIntegers = competitionRepository.findAllCompetitionsNumbers();
            TypeReference<List<Membership>> membershipTypeRef = new TypeReference<>() {
            };

            competitioIntegers.sort(null);
            // Partial loop for sanity checks - delete count
            for (Integer competitionId : competitioIntegers) {

                if (count == 3) {
                    break;
                }

                String activeMembershipJson = ExternalApiDataFetcherUtil
                        .fetchData(
                                ExternalSoccerApiEndpoints.ACTIVE_MEMBERSHIPS_RESOURCE_URL + competitionId);

                List<Membership> activeMemberships = new ArrayList<>();

                activeMemberships = objectMapper.readValue(activeMembershipJson, membershipTypeRef);

                activeMemberships.forEach(membership -> membership.setCompetitionId(competitionId));

                membershipRepository.saveAll(activeMemberships);

                count++;
            }

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void fetchRecentlyChangedMembershipAndUpdate() throws JsonProcessingException {

        try {
            int count = 0;
            List<Membership> recentlyChangedMemberships = new ArrayList<>();
            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            List<Integer> competitioIntegers = competitionRepository.findAllCompetitionsNumbers();
            int numOfDays = 30;
            TypeReference<List<Membership>> MembershipTypeRef = new TypeReference<>() {
            };

            competitioIntegers.sort(null);

            // Partial loop for sanity checks - delete count

            for (Integer competitionId : competitioIntegers) {
                if (count == 3) {
                    break;
                }

                String recentlyChangedMembershipsJson = ExternalApiDataFetcherUtil.fetchData(
                        ExternalSoccerApiEndpoints.RECENTLY_CHANGED_MEMBERSHIPS_RESOURCE_URL + competitionId + "/"
                                + numOfDays);

                recentlyChangedMemberships = objectMapper.readValue(recentlyChangedMembershipsJson, MembershipTypeRef);

                recentlyChangedMemberships.forEach(membership -> membership.setCompetitionId(competitionId));

                membershipRepository.saveAll(recentlyChangedMemberships);

                count++;
            }
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }
    }

    public void fetchPlayersbyTeamsAndUpdate() throws JsonProcessingException {

        try {
            int count = 0;

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();

            List<TeamDetail> teams = teamDetailRepository.findAll();

            List<Player> players = new ArrayList<>();

            TypeReference<List<Player>> playerTypeRef = new TypeReference<>() {
            };

            // Partial loop for sanity checks - delete count
            for (int i = 0; i < teams.size(); i++) {
                TeamDetail team = teams.get(i);
                Integer competitionId = team.getCompetition().get(0).getCompetitionId();
                int teamId = team.getTeamId();

                if (count == 3) {
                    break;
                }

                String playersbyTeamJson = ExternalApiDataFetcherUtil.fetchData(
                        ExternalSoccerApiEndpoints.PLAYERS_BY_TEAM_RESOURCE_URL +
                                competitionId + "/" + teamId);

                players = objectMapper.readValue(playersbyTeamJson, playerTypeRef);

                int teamNum = i;
                players.forEach(player -> player.setTeamDetail(teams.get(teamNum)));

                playerRepository.saveAll(players);

                count++;

            }
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void fetchAndUpdateScheduleAndStandingsAndTeamSeason() throws JsonProcessingException {

        try {
            int count = 0;
            List<Round> rounds = new ArrayList<>();
            List<Round> standingsRounds = new ArrayList<>();
            List<Round> teamSeasonRounds = new ArrayList<>();

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            List<Integer> competitioIntegers = competitionRepository.findAllCompetitionsNumbers();
            TypeReference<List<Round>> roundTypeRef = new TypeReference<>() {
            };

            competitioIntegers.sort(null);

            // Partial loop for sanity checks - delete count
            for (Integer competitonId : competitioIntegers) {

                if (count > 1) {
                    break;
                }

                for (int i = 0; i < seasonsArr.length; i++) {
                    int seasonYear = seasonsArr[i];
                    count++;
                    String scheduleJson = ExternalApiDataFetcherUtil.fetchData(
                            ExternalSoccerApiEndpoints.SCHEDULE_RESOURCE_URL + competitonId + "/"
                                    + seasonYear);

                    String standingsJson = ExternalApiDataFetcherUtil.fetchData(
                            ExternalSoccerApiEndpoints.STANDINGS_RESOURCE_URL + competitonId + "/"
                                    + seasonYear);

                    String teamSeasonJson = ExternalApiDataFetcherUtil.fetchData(
                            ExternalSoccerApiEndpoints.TEAM_SEASON_STATS_RESOURCE_URL + competitonId + '/'
                                    + seasonYear);

                    rounds = objectMapper.readValue(scheduleJson, roundTypeRef);
                    standingsRounds = objectMapper.readValue(standingsJson, roundTypeRef);
                    teamSeasonRounds = objectMapper.readValue(teamSeasonJson, roundTypeRef);

                    RoundUtils.setCompetitionToRounds(rounds, competitonId);
                    RoundUtils.setStandingsAndTeamSeasonsToRounds(rounds, standingsRounds, teamSeasonRounds);

                    roundRepository.saveAll(rounds);
                }
                count++;

            }

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void fetchVenuesAndUpdate() throws JsonProcessingException {
        try {

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();

            String venuesJson = ExternalApiDataFetcherUtil.fetchData(ExternalSoccerApiEndpoints.VENUES_RESOURCE_URL);

            List<Venue> venues = new ArrayList<>();

            TypeReference<List<Venue>> venueTypeRef = new TypeReference<>() {
            };

            venues = objectMapper.readValue(venuesJson, venueTypeRef);

            venueRepository.saveAll(venues);

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
        }
    }

    public void fetchAndUpdateBoxScore() throws JsonProcessingException {

        try {
            Map<Integer, Integer> map = gameService.getGameIdAndCompetitionMap();
            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            List<BoxScore> boxScores = new ArrayList<>();
            int count = 0;

            TypeReference<List<BoxScore>> boxScoreTypeRef = new TypeReference<>() {
            };

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (count == 2) {
                    break;
                }
                Integer competition = entry.getValue();
                Integer gameId = entry.getKey();
                System.out.println(competition + "      " + gameId);

                String boxScoreFixturesJson = ExternalApiDataFetcherUtil
                        .fetchData(ExternalSoccerApiEndpoints.BOX_SCORE_RESOURCE_URL + competition + "/" + gameId);

                boxScores = objectMapper.readValue(boxScoreFixturesJson, boxScoreTypeRef);

                BoxScore currentBoxScore = boxScores.get(0);
                BoxScore existingBoxScore = boxScoreRepository.findByGameId(gameId);

                currentBoxScore.setCompetition(competition);
                currentBoxScore.setDateTime(currentBoxScore.getGame().getDateTime());
                List<PlayerGame> playersGames = currentBoxScore.getPlayerGames();
                PlayerGameUtils.setCompetitionToPlayerGame(playersGames, competition);

                if (existingBoxScore == null) {
                    currentBoxScore.setBoxScoreId(BoxScoreIdGenerator.generateId());
                    boxScoreRepository.saveAll(boxScores);
                } else {
                    currentBoxScore.setBoxScoreId(existingBoxScore.getBoxScoreId());
                    boxScoreRepository.saveAll(boxScores);

                }
                count++;
            }
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
        }
    }

}
