package com.example.sport_api.unit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.sport_api.controllers.soccer.BoxScoreController;
import com.example.sport_api.models.sport.BoxScore;
import com.example.sport_api.services.soccer.BoxScoreService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.GameService;
import com.example.sport_api.util.ResponseUtil;

public class BoxScoreControllerTests {

    @InjectMocks
    private BoxScoreController boxScoreController;

    @Mock
    private CompetitionService competitionService;

    @Mock
    private GameService gameService;

    @Mock
    private BoxScoreService boxScoreService;

    @BeforeAll
    public static void staticSetUp() {
        Mockito.mockStatic(ResponseUtil.class);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveBoxScoreByCompetitionAndGameId_Success() {

        String competition = "1";
        String gameId = "2";
        String apiKey = "key";

        Integer theCompetition = Integer.parseInt(competition);
        Integer theGameId = Integer.parseInt(gameId);

        when(competitionService.isCompetitionValid(theCompetition)).thenReturn(true);
        when(gameService.isValidGameId(theGameId)).thenReturn(true);

        BoxScore mockBoxScore = new BoxScore();
        mockBoxScore.setCompetition(theCompetition);

        when(boxScoreService.getBoxScoreByCompetitionAndGameId(theCompetition, theGameId))
                .thenReturn(mockBoxScore);

        ResponseEntity<?> expectedResponse = ResponseEntity.ok(mockBoxScore);
        when(ResponseUtil.createOkResponse(mockBoxScore)).thenReturn((ResponseEntity<BoxScore>) expectedResponse);

        ResponseEntity<?> result = boxScoreController.retriveBoxScoreByCompetitionAndGameId(
                competition, gameId, apiKey);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(expectedResponse, result);
    }

    @Test
    public void testRetrieveBoxScoreByCompetitionAndGameIdInvalidCompetition() {
        // Arrange
        String competition = "1"; // Replace with an invalid competition ID
        String gameId = "2"; // Replace with a valid game ID
        String apiKey = "your_api_key";

        Integer theCompetition = Integer.parseInt(competition);
        Integer theGameId = Integer.parseInt(gameId);

        when(competitionService.isCompetitionValid(theCompetition)).thenReturn(false);

        // Mock the createErrorResponse method to return a BAD_REQUEST response
        ResponseEntity<?> expectedErrorResponse = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid Argument: The competition parameter is invalid");

        when(ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                "Invalid Argument: The competition parameter is invalid"))
                .thenReturn((ResponseEntity<Map<String, Object>>) expectedErrorResponse);

        // Act
        ResponseEntity<?> result = boxScoreController.retriveBoxScoreByCompetitionAndGameId(
                competition, gameId, apiKey);

        // Assert
        // assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode()); // Check if the
        // status code is BAD_REQUEST
        assertSame(expectedErrorResponse, result); // Check if the response is the expected error response
    }

    @Test
    public void testRetrieveBoxScoreByCompetitionAndGameId_NumberFormatException() {
        // Arrange
        String competition = "invalid"; // Invalid competition ID that causes NumberFormatException
        String gameId = "2"; // Replace with a valid game ID
        String apiKey = "your_api_key";

        // Act
        ResponseEntity<?> result = boxScoreController.retriveBoxScoreByCompetitionAndGameId(
                competition, gameId, apiKey);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode()); // Check if the status code is BAD_REQUEST
    }
}
