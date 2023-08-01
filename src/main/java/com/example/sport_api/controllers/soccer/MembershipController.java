package com.example.sport_api.controllers.soccer;

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
import com.example.sport_api.config.OpenApiParameters;
import com.example.sport_api.models.sport.Membership;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.MembershipService;
import com.example.sport_api.services.soccer.TeamService;
import com.example.sport_api.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Membership", description = "Endpoints for retrieving membership information")
public class MembershipController {

    private static final Logger logger = LogManager.getLogger(MembershipController.class);

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MembershipService membershipService;

    @Operation(summary = " Get active memberships by competition and date. ", description = "Retrieves active memberships by competitioId .  \n"
            + "Recommended Call Interval:  1 Day")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Membership.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("scores/ActiveMemberships/{competition}")
    public ResponseEntity<?> retriveMembershipByCompetition(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition) {

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

    @Operation(summary = " Get recently changed Memberships  by competition and days. ", description = "Retrieves recently changed Memberships  by competition and days.  \n"
            + "Recommended Call Interval: 1 Hour")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Membership.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })

    @GetMapping(value = "scores/recentlychangedMemberships/{competition}/{days}")
    public ResponseEntity<?> retriveMembershipByCompetitionAndUpdateDate(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.DAYS_DESCRIPTION) @PathVariable String days) {

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

    @Operation(summary = " Get Historical memberships by teamId. ", description = "Retrieves Historical memberships by competition and teamId.  \n"
            + "Recommended Call Interval: 1 Day")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Membership.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })

    @GetMapping(value = "scores/HistoricalMembershipsByTeam/{competition}/{teamId}")
    public ResponseEntity<?> retriveMembershipByCompetitionAndTeamId(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.GAME_ID_DESCRIPTION) @PathVariable String teamId) {

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
