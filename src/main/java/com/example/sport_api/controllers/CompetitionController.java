package com.example.sport_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.exceptions.NotFoundException;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
import com.example.sport_api.services.CompetitionService;
import com.example.sport_api.util.ResponseUtil;

import java.util.List;

@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/com")
    public List<CompetitionDto> retrieveAllTeams() {
        return competitionService.getAllCompetitions();
    }

    // need to fix IllegalArgument exception that will chack if the input is Intger!
    @GetMapping("/c/{competitionId}")
    public ResponseEntity<Competition> retrieveCompetitionById(@PathVariable Integer competitionId) {
        try {

            if (competitionId == null ||
                    !competitionService.isCompetitionIdValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }
            Competition competition = competitionService.getCompetitionById(competitionId);

            return ResponseUtil.createOkResponse(competition);
        } catch (NotFoundException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    e.getMessage());
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
