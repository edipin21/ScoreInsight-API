package com.example.sport_api.controllerTests;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.example.sport_api.controllers.CompetitionController;
import com.example.sport_api.exceptions.NotFoundException;
import com.example.sport_api.models.Competition;
import com.example.sport_api.services.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataAccessResourceFailureException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompetitionControllerTest {

    @Mock
    private CompetitionService competitionService;

    @InjectMocks
    private CompetitionController competitionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveCompetitionById_ValidId_ReturnsCompetition() {
        // Arrange
        int competitionId = 1;
        Competition expectedCompetition = new Competition();
        when(competitionService.getCompetitionById(competitionId)).thenReturn(expectedCompetition);

        // Act
        ResponseEntity<Competition> response = competitionController.retrieveCompetitionById(competitionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(expectedCompetition, response.getBody());
    }

    @Test
    public void testRetrieveCompetitionById_NotFound() {
        // Configure the competitionService mock to throw a NotFoundException
        doThrow(new NotFoundException("Competition not found"))
                .when(competitionService)
                .getCompetitionById(anyInt());

        // Call the retrieveCompetitionById method
        ResponseEntity<Competition> response = competitionController.retrieveCompetitionById(1);

        // Assert the response status and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(null, response.getBody());
    }

    @Test
    void testRetrieveCompetitionById_InvalidId_ReturnsBadRequest() {
        // Arrange
        int competitionId = -1;
        when(competitionService.getCompetitionById(competitionId)).thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<Competition> response = competitionController.retrieveCompetitionById(competitionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(null, response.getBody());
    }

    @Test
    void testRetrieveCompetitionById_DataAccessException_ReturnsInternalServerError() {
        // Arrange
        int competitionId = 1;
        when(competitionService.getCompetitionById(competitionId))
                .thenThrow(new DataAccessResourceFailureException("Database connection error"));

        // Act
        ResponseEntity<Competition> response = competitionController.retrieveCompetitionById(competitionId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(null, response.getBody());
    }
}
