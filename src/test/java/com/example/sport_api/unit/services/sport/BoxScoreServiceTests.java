package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import com.example.sport_api.models.sport.BoxScore;
import com.example.sport_api.repositories.soccer.BoxScoreRepository;
import com.example.sport_api.services.soccer.BoxScoreService;

@SpringBootTest
public class BoxScoreServiceTests {

    @InjectMocks
    private BoxScoreService boxScoreService;

    @Mock
    private BoxScoreRepository boxScoreRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBoxScoreByCompetitionAndGameIdSuccess() {

        Integer competition = 3; // Replace with appropriate competition value
        Integer gameId = 1; // Replace with appropriate game ID value
        BoxScore mockBoxScore = new BoxScore(1, 3);

        when(boxScoreRepository.findByCompetitionAndGameId(competition, gameId)).thenReturn(mockBoxScore);

        BoxScore result = boxScoreService.getBoxScoreByCompetitionAndGameId(competition, gameId);

        assertEquals(mockBoxScore, result);
    }

    @Test
    public void testGetBoxScoreByCompetitionAndGameIdDataAccessException() {

        Integer competition = 1;
        Integer gameId = 2;

        when(boxScoreRepository.findByCompetitionAndGameId(competition, gameId))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            boxScoreService.getBoxScoreByCompetitionAndGameId(competition, gameId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetBoxScoresByCompetitionAndDateSuccess() {
        // Arrange
        Integer competition = 1;
        String dateTime = "2023-09-15";

        Date theDate = Date.valueOf(dateTime);

        List<BoxScore> mockBoxScores = new ArrayList<>();
        mockBoxScores.add(new BoxScore(1, competition, theDate));

        when(boxScoreRepository.findByCompetitionAndDate(competition, theDate)).thenReturn(mockBoxScores);

        List<BoxScore> result = boxScoreService.getBoxScoresByCompetitionAndDate(competition, dateTime);

        assertEquals(mockBoxScores.size(), result.size());
    }

    @Test
    public void testGetBoxScoresByCompetitionAndDateDataAccessException() {

        Integer competition = 1;
        String dateTime = "2023-09-15";

        Date theDate = Date.valueOf(dateTime);

        when(boxScoreRepository.findByCompetitionAndDate(competition, theDate))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        // Act & Assert
        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            boxScoreService.getBoxScoresByCompetitionAndDate(competition, dateTime);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }
}
