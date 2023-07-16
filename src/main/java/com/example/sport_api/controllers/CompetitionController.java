package com.example.sport_api.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;
import com.example.sport_api.services.CompetitionService;
import com.example.sport_api.util.ResponseUtil;
import java.util.List;

@RestController
public class CompetitionController {

    private static final Logger logger = LogManager.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/scores/competitions")
    public ResponseEntity<List<CompetitionDto>> retrieveAllCompetitions() {

        try {
            List<CompetitionDto> competitions = competitionService.getAllCompetitions();
            return ResponseUtil.createOkResponse(competitions);
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while retrieving competitions: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/scores/{competition}")
    public ResponseEntity<Competition> retrieveCompetitionById(@PathVariable String competition) {
        try {

            Integer competitionId = Integer.parseInt(competition);

            if (competitionId == null ||
                    !competitionService.isCompetitionIdValid(competitionId)) {
                throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
            }
            Competition theCompetition = competitionService.getCompetitionById(competitionId);

            return ResponseUtil.createOkResponse(theCompetition);
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

}
