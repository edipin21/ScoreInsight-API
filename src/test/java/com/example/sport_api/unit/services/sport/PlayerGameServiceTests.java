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
import org.springframework.dao.DataAccessException;
import com.example.sport_api.models.sport.PlayerGame;
import com.example.sport_api.repositories.soccer.PlayerGameRepository;
import com.example.sport_api.services.soccer.playerGameService;

public class PlayerGameServiceTests {

    @InjectMocks
    private playerGameService playerGameService;

    @Mock
    private PlayerGameRepository playerGameRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPlayerGameStatByCompetitionAndDateSuccess() {
        // Arrange
        Integer competitionId = 1;
        String date = "2023-09-15";

        Date theDate = Date.valueOf(date);

        List<PlayerGame> mockPlayerGames = new ArrayList<>();
        mockPlayerGames.add(new PlayerGame());
        mockPlayerGames.get(0).setDateTime(theDate);

        when(playerGameRepository.findByCompetitionAndDate(competitionId, theDate)).thenReturn(mockPlayerGames);

        List<PlayerGame> result = playerGameService.getPlayerGameStatByCompetitionAndDate(competitionId, date);

        assertEquals(mockPlayerGames.size(), result.size());
        assertEquals(mockPlayerGames.get(0).getDateTime(), result.get(0).getDateTime());

    }

    @Test
    public void testGetPlayerGameStatByCompetitionAndDateDataAccessException() {

        Integer competitionId = 1;
        String date = "2023-09-15";

        Date theDate = Date.valueOf(date);

        when(playerGameRepository.findByCompetitionAndDate(competitionId, theDate))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            playerGameService.getPlayerGameStatByCompetitionAndDate(competitionId, date);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

}
