package com.example.sport_api.unit.services.sport;

import java.util.Map;
import java.sql.Date;
import java.util.List;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Arrays;

import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import com.example.sport_api.models.sport.Game;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.services.soccer.GameService;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.sport_api.repositories.soccer.GameRepository;

@SpringBootTest
public class GameServiceTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGamesByDateTimeAndCompetitionIdSuccess() {

        Integer competitionId = 1;
        String date = "2023-09-15";

        Date theDate = Date.valueOf(date);

        List<Game> mockGames = new ArrayList<>();
        mockGames.add(new Game());
        mockGames.get(0).setGameId(1);
        mockGames.get(0).setDateTime(theDate);
        mockGames.get(0).setGroupName("test");

        when(gameRepository.findByDateAndCompetitionId(theDate, competitionId)).thenReturn(mockGames);

        List<Game> result = gameService.getGamesByDateTimeAndCompetitionId(competitionId, date);

        assertEquals(mockGames.size(), result.size()); // Check if the number of games matches the expected number
        // You can add more specific assertions based on your actual data and business
        // logic
    }

    @Test
    public void testGetGamesByDateTimeAndCompetitionIdDataAccessException() {

        Integer competitionId = 1;
        String date = "2023-09-15";

        Date theDate = Date.valueOf(date);

        when(gameRepository.findByDateAndCompetitionId(theDate, competitionId))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            gameService.getGamesByDateTimeAndCompetitionId(competitionId, date);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetGameIdAndCompetitionMapSuccess() {

        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[] { 1, 101 });
        mockResults.add(new Object[] { 2, 102 });

        when(gameRepository.findGameIdAndCompetition()).thenReturn(mockResults);

        Map<Integer, Integer> result = gameService.getGameIdAndCompetitionMap();

        assertEquals(mockResults.size(), result.size());

    }

    @Test
    public void testGetGameIdAndCompetitionMapDataAccessException() {
        
        when(gameRepository.findGameIdAndCompetition()).thenThrow(new DataAccessException("Mock Exception") {});

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            gameService.getGameIdAndCompetitionMap();
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testIsValidGameIdValidGame() {

        Integer gameId = 1;
        List<Integer> allGameIds = Arrays.asList(1, 2, 3);

        when(gameRepository.findAllGameIds()).thenReturn(allGameIds);

        boolean isValid = gameService.isValidGameId(gameId);

        assertTrue(isValid);
    }

    @Test
    public void testIsValidGameIdInvalidGame() {

        Integer gameId = 4;
        List<Integer> allGameIds = Arrays.asList(1, 2, 3);

        when(gameRepository.findAllGameIds()).thenReturn(allGameIds);

        boolean isValid = gameService.isValidGameId(gameId);

        assertFalse(isValid);
    }

    @Test
    public void testIsValidGameIdDataAccessException() {

        Integer gameId = 1;

        when(gameRepository.findAllGameIds()).thenThrow(new DataAccessException("Mock Exception") {
        });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            gameService.isValidGameId(gameId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }
}
