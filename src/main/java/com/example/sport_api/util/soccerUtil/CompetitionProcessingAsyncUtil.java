package com.example.sport_api.util.soccerUtil;

import java.util.ArrayList;
import java.util.List;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.models.sport.Season;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;

public class CompetitionProcessingAsyncUtil {

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
            competitionFixtures.add(CompetitionUtils.deserializeCompetition(competitionFixtureJson));

        });

        return competitionFixtures;
    }

}
