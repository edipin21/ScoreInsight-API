package com.example.sport_api.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.models.Round;
import com.example.sport_api.services.CompetitionService;
import com.example.sport_api.services.RoundService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class RoundController {

    private static final Logger logger = LogManager.getLogger(RoundController.class);

    @Autowired
    private RoundService roundService;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/scores/Schedule/{competition}/{year}")
    public ResponseEntity<List<Round>> retriveScheduleByCompetitionAndYear(@PathVariable String competition,
            @PathVariable String year) {

        List<Integer> years = Arrays.asList(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024);

        try {
            Integer competitionId = Integer.parseInt(competition);
            Integer theYear = Integer.parseInt(year);

            if (competitionId == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (theYear == null || !years.contains(theYear)) {
                throw new IllegalArgumentException("Invalid Argument: The year parameter is invalid ");
            }

            List<Round> theSchedule = roundService.getScheduleByCompetitionAndYear(competitionId, theYear);

            return ResponseUtil.createOkResponse(theSchedule);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and year parameters should be an integers", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and year parameters should be an integers");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/scores/Standings/{competition}/{year}")
    public ResponseEntity<List<Round>> retriveStandingsByCompetitionAndYear(@PathVariable String competition,
            @PathVariable String year) {

        List<Integer> years = Arrays.asList(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024);

        try {
            Integer competitionId = Integer.parseInt(competition);
            Integer theYear = Integer.parseInt(year);

            if (competitionId == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (theYear == null || !years.contains(theYear)) {
                throw new IllegalArgumentException("Invalid Argument: The year parameter is invalid ");
            }

            List<Round> Standings = roundService.getStandingsByCompetitionAndYear(competitionId, theYear);

            return ResponseUtil.createOkResponse(Standings);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and year parameters should be an integers", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and year parameters should be an integers");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/scores/teamSeasonStats/{competition}/{year}")
    public ResponseEntity<List<Round>> retriveTeamSeasonStatsByCompetitionAndYear(@PathVariable String competition,
            @PathVariable String year) {

        List<Integer> years = Arrays.asList(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024);

        try {
            Integer competitionId = Integer.parseInt(competition);
            Integer theYear = Integer.parseInt(year);

            if (competitionId == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (theYear == null || !years.contains(theYear)) {
                throw new IllegalArgumentException("Invalid Argument: The year parameter is invalid ");
            }

            List<Round> teamSeasonStats = roundService.getTeamSeasonStatsByCompetitionAndYear(competitionId, theYear);

            return ResponseUtil.createOkResponse(teamSeasonStats);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and year parameters should be an integers", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and year parameters should be an integers");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
