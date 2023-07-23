package com.example.sport_api.controllers;

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

import com.example.sport_api.models.Membership;
import com.example.sport_api.services.CompetitionService;
import com.example.sport_api.services.MembershipService;
import com.example.sport_api.services.TeamService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class MembershipController {

    private static final Logger logger = LogManager.getLogger(MembershipController.class);

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MembershipService membershipService;

    @GetMapping("scores/ActiveMemberships/{competition}")
    public ResponseEntity<List<Membership>> retriveMembershipByCompetition(@PathVariable String competition) {

        try {

            Integer competitionId = Integer.parseInt(competition);

            if (competition == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            List<Membership> memberships = membershipService.getMembershipsByCompetition(competitionId);

            return ResponseUtil.createOkResponse(memberships);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition parameter should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition parameter should be an integer");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping(value = "scores/recentlychangedMemberships/{competition}/{days}")
    public ResponseEntity<List<Membership>> retriveMembershipByCompetitionAndUpdateDate(
            @PathVariable String competition,
            @PathVariable String days) {

        try {
            Integer competitionId = Integer.parseInt(competition);
            Integer theDays = Integer.parseInt(days);

            if (competitionId == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (theDays < 1 || theDays > 30 || theDays == null) {
                throw new IllegalArgumentException("Invalid Argument: The days parameter is invalid ");
            }

            List<Membership> memberships = membershipService.getRecentlyChangedMemberships(competitionId, theDays);

            return ResponseUtil.createOkResponse(memberships);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and days parameteres should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and days parameteres should be an integer");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping(value = "scores/HistoricalMembershipsByTeam/{competition}/{teamId}")
    public ResponseEntity<List<Membership>> retriveMembershipByCompetitionAndTeamId(
            @PathVariable String competition,
            @PathVariable String teamId) {

        try {
            Integer competitionId = Integer.parseInt(competition);
            Integer team = Integer.parseInt(teamId);

            if (competitionId == null || !competitionService.isCompetitionValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }

            if (team == null || !teamService.isTeamIdValid(team)) {
                throw new IllegalArgumentException("Invalid Argument: The teamId parameter is invalid ");
            }

            List<Membership> memberships = membershipService.getMembershipsByCompetitionAndTeam(competitionId, team);

            return ResponseUtil.createOkResponse(memberships);
        } catch (NumberFormatException e) {
            logger.error("Invalid Argument: The competition and days parameteres should be an integer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Invalid Argument: The competition and days parameteres should be an integer");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument: " + e.getMessage());
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
