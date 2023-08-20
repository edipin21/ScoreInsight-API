package com.example.sport_api.util.soccerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.models.sport.Season;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.CompetitionRepository;
import com.example.sport_api.repositories.soccer.GameRepository;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.repositories.soccer.TeamDetailRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CompetitionUtils {

    private static final Logger logger = LogManager.getLogger(CompetitionUtils.class);

    public static void saveCompetitionsToDB(List<Competition> competitions,
            CompetitionRepository competitionRepository) throws DataAccessException {

        competitionRepository.saveAll(competitions);
    }

    public static void handleExternalApiException(Exception e) {
        ExternalApiDataFetcherUtil.handleException(e);
        throw new RuntimeException("Failed to synchronize competitions from external API", e);
    }

    // public static void setCompetitionsIdToRounds(List<Competition> competitions)
    // {

    // competitions.forEach(competition -> {
    // setCompetitionIdToRounds(competition);
    // });

    // }

    public static void setCompetitionIdToRounds(Competition competition) {

        List<Season> seasons = competition.getSeasons();
        seasons.forEach(season -> {
            List<Round> rounds = season.getRounds();
            rounds.forEach(round -> round.setCompetitionId(competition.getCompetitionId()));
        });

    }

    public static List<Game> setCompetitionIdToGames(Competition competition) {

        List<Game> theGames = competition.getGames();
        if (!theGames.isEmpty())
            theGames.forEach(game -> game.setCompetition(competition));

        return theGames;

    }

    public static List<TeamDetail> setCompetitionIdToTeamsDetail(Competition competition) {

        List<TeamDetail> theTeams = competition.getTeams();

        if (!theTeams.isEmpty()) {
            List<Competition> competitions = new ArrayList<>();
            competitions.add(competition);
            theTeams.forEach(team -> team.setCompetition(competitions));
        }
        return theTeams;

    }

    public static Competition deserializeCompetition(String json) {
        try {

            Competition competition = ExternalApiDataFetcherUtil.fetchDataFromExternalApi(json, Competition.class);

            return competition;

        } catch (JsonProcessingException e) {
            logger.error("Error occurred while deserializeing competition");
            return null;
        }
    }

    public static void setCompetitionIdToRoundsParallel(Competition competition) {

        List<Season> seasons = competition.getSeasons();
        seasons.parallelStream().forEach(season -> {
            List<Round> rounds = season.getRounds();
            rounds.parallelStream().forEach(round -> round.setCompetitionId(competition.getCompetitionId()));
        });

    }

    public static void setCompetitionsIdToRoundsParallel(List<Competition> competitions) {
        competitions.parallelStream().forEach(competition -> {
            setCompetitionIdToRoundsParallel(competition);
        });
    }

    public static List<Game> setCompetitionIdToGamesParallel(Competition competition) {

        List<Game> theGames = competition.getGames();
        if (!theGames.isEmpty())
            theGames.parallelStream().forEach(game -> game.setCompetition(competition));

        return theGames;

    }

    public static List<TeamDetail> setCompetitionIdToTeamsDetailParallel(Competition competition) {

        List<TeamDetail> theTeams = competition.getTeams();

        if (!theTeams.isEmpty()) {
            List<Competition> competitions = new ArrayList<>();
            competitions.add(competition);
            theTeams.parallelStream().forEach(team -> team.setCompetition(competitions));
        }
        return theTeams;

    }

    public static List<String> getCompetitionFixtureJsonsParallel(List<Integer> competitionIds) {

        List<String> competitionFixtureJsons = new ArrayList<>();
        competitionIds.parallelStream().forEach(competitionId -> {
            String competitionFixtureJson = ExternalApiDataFetcherUtil
                    .fetchData(ExternalSoccerApiEndpoints.COMPETITION_FIXTURES_RESOURCE_URL + competitionId);
            competitionFixtureJsons.add(competitionFixtureJson);
        });
        return competitionFixtureJsons;
    }

    public static List<Competition> deserializeCompetitionFixturesParallel(List<String> competitionFixtureJsons) {
        List<Competition> competitionFixtures = new ArrayList<>();

        competitionFixtureJsons.parallelStream().forEach(competitionFixtureJson -> {
            competitionFixtures.add(deserializeCompetition(competitionFixtureJson));

        });

        return competitionFixtures;
    }

    public static void saveOrUpdateCompetitionDataToDB(Competition competition, PlayerRepository playerRepository,
            CompetitionRepository competitionRepository, GameRepository gameRepository,
            TeamDetailRepository teamDetailRepository) {

        List<Game> games = setCompetitionIdToGames(competition);
        List<TeamDetail> teams = setCompetitionIdToTeamsDetail(competition);
        TeamDetailUtils.setPlayersToTeams(teams, playerRepository);
        setCompetitionIdToRounds(competition);

        competitionRepository.save(competition);

        if (!games.isEmpty()) {
            gameRepository.saveAll(games);
        }

        if (!teams.isEmpty()) {
            teamDetailRepository.saveAll(teams);
        }
    }

    public static CompletableFuture<Void> processCompetitionFixtureAndSaveToDBAsync(Competition competition,
            PlayerRepository playerRepository, CompetitionRepository competitionRepository,
            TeamDetailRepository teamDetailRepository, GameRepository gameRepository) {

        CompletableFuture<List<Game>> gameFuture = CompletableFuture
                .supplyAsync(() -> CompetitionUtils.setCompetitionIdToGames(competition));

        CompletableFuture<List<TeamDetail>> teamsFuture = CompletableFuture
                .supplyAsync(() -> CompetitionUtils.setCompetitionIdToTeamsDetail(competition));

        CompletableFuture<Void> playersFuture = teamsFuture.thenAcceptAsync(
                teams -> TeamDetailUtils.setPlayersToTeams(teams, playerRepository));

        CompletableFuture<Void> roundsFuture = CompletableFuture.runAsync(
                () -> CompetitionUtils.setCompetitionIdToRounds(competition));

        CompletableFuture<Void> allOfCompetitionFuture = CompletableFuture.allOf(gameFuture, playersFuture,
                roundsFuture);

        return allOfCompetitionFuture
                .thenRun(() -> saveCompetitionData(competition, gameFuture.join(), teamsFuture.join(),
                        competitionRepository, gameRepository,
                        teamDetailRepository));
    }

    private static void saveCompetitionData(Competition competition, List<Game> games, List<TeamDetail> teams,
            CompetitionRepository competitionRepository, GameRepository gameRepository,
            TeamDetailRepository teamDetailRepository) {
        competitionRepository.save(competition);
        if (!games.isEmpty())
            gameRepository.saveAll(games);
        if (!teams.isEmpty())
            teamDetailRepository.saveAll(teams);
    }
}
