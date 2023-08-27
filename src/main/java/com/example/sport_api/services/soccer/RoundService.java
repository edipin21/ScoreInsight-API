package com.example.sport_api.services.soccer;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.repositories.soccer.RoundRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.soccerUtil.RoundUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class RoundService {

    static final int[] seasonsArr = { 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024 };

    private static final Logger logger = LogManager.getLogger(RoundService.class);

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private CompetitionService competitionService;

    public void syncScheduleAndStandingsAndTeamSeasonFromExternalApi() throws JsonProcessingException {

        try {

            List<Integer> competitioIntegers = competitionService.getSortedCompetitionIds();
            TypeReference<List<Round>> roundTypeRef = new TypeReference<>() {
            };

            competitioIntegers.forEach(competitionId -> {
                List<Round> rounds = new ArrayList<>();
                List<Round> standingsRounds = new ArrayList<>();
                List<Round> teamSeasonRounds = new ArrayList<>();

                for (Integer year : seasonsArr) {

                    String scheduleApiEndPoint = ExternalSoccerApiEndpoints.SCHEDULE_RESOURCE_URL
                            + competitionId
                            + "/"
                            + year;

                    String standingsApiEndPoint = ExternalSoccerApiEndpoints.STANDINGS_RESOURCE_URL
                            + competitionId
                            + "/"
                            + year;

                    String teamSeasonApiEndPoint = ExternalSoccerApiEndpoints.TEAM_SEASON_STATS_RESOURCE_URL
                            + competitionId
                            + '/'
                            + year;

                    rounds = ExternalApiDataFetcherUtil.fetchListDataFromExternalApi(scheduleApiEndPoint, roundTypeRef);

                    standingsRounds = ExternalApiDataFetcherUtil.fetchListDataFromExternalApi(standingsApiEndPoint,
                            roundTypeRef);

                    teamSeasonRounds = ExternalApiDataFetcherUtil.fetchListDataFromExternalApi(teamSeasonApiEndPoint,
                            roundTypeRef);

                    RoundUtils.setCompetitionToRounds(rounds, competitionId);
                    RoundUtils.setStandingsAndTeamSeasonsToRounds(rounds, standingsRounds, teamSeasonRounds);

                    RoundUtils.saveRoundsToDB(roundRepository, rounds);
                }

            });

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public List<Round> getScheduleByCompetitionAndYear(Integer Competition, Integer year) {

        try {
            List<Round> schedule = roundRepository.findRoundsByCompetitionAndYear(Competition, year);

            schedule.forEach(round -> round.setStandings(new ArrayList<>()));
            schedule.forEach(round -> round.setTeamSeasons(new ArrayList<>()));

            return schedule;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }
    }

    public List<Round> getStandingsByCompetitionAndYear(Integer Competition, Integer year) {

        try {
            List<Round> standings = roundRepository.findRoundsByCompetitionAndYear(Competition, year);

            standings.forEach(round -> round.setGames(new ArrayList<>()));
            standings.forEach(round -> round.setTeamSeasons(new ArrayList<>()));

            return standings;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }
    }

    public List<Round> getTeamSeasonStatsByCompetitionAndYear(Integer Competition, Integer year) {

        try {
            List<Round> teamSeasonStats = roundRepository.findRoundsByCompetitionAndYear(Competition, year);

            teamSeasonStats.forEach(round -> round.setStandings(new ArrayList<>()));
            teamSeasonStats.forEach(round -> round.setGames(new ArrayList<>()));

            return teamSeasonStats;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }
    }

}
