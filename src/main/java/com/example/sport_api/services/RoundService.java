package com.example.sport_api.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.models.Round;
import com.example.sport_api.repositories.RoundRepository;

@Service
public class RoundService {

    private static final Logger logger = LogManager.getLogger(RoundService.class);

    @Autowired
    private RoundRepository roundRepository;

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
