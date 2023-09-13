package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.repositories.soccer.RoundRepository;
import com.example.sport_api.services.soccer.RoundService;

public class RoundServiceTests {

    @InjectMocks
    private RoundService roundService;

    @Mock
    private RoundRepository roundRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetScheduleByCompetitionAndYearSuccess() {
        // Arrange
        Integer competitionId = 1;
        Integer year = 2023;

        List<Round> mockRounds = new ArrayList<>();
        mockRounds.add(new Round());
        mockRounds.get(0).setCompetitionId(competitionId);

        when(roundRepository.findRoundsByCompetitionAndYear(competitionId, year)).thenReturn(mockRounds);

        List<Round> result = roundService.getScheduleByCompetitionAndYear(competitionId, year);

        assertEquals(mockRounds.size(), result.size());

        result.forEach(round -> {
            assertTrue(round.getStandings().isEmpty());
            assertTrue(round.getTeamSeasons().isEmpty());
        });

    }

    @Test
    public void testGetScheduleByCompetitionAndYearDataAccessException() {

        Integer competitionId = 1;
        Integer year = 2023;

        when(roundRepository.findRoundsByCompetitionAndYear(competitionId, year))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            roundService.getScheduleByCompetitionAndYear(competitionId, year);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }
}
