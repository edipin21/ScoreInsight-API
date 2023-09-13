package com.example.sport_api.unit.services.betting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.models.betting.BettingEvent;
import com.example.sport_api.repositories.betting.BettingEventRepository;
import com.example.sport_api.services.betting.BettingEventService;

public class BettingEventServiceTests {

    @InjectMocks
    private BettingEventService bettingEventService;

    @Mock
    private BettingEventRepository bettingEventRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBettingEventsByCompetitionAndSeasonSuccess() {

        Integer competitionId = 1;
        Integer season = 2023;

        List<BettingEvent> mockBettingEvents = new ArrayList<>();
        mockBettingEvents.add(new BettingEvent());
        mockBettingEvents.add(new BettingEvent());

        when(bettingEventRepository.findByCompetitionAndSeason(competitionId, season)).thenReturn(mockBettingEvents);

        List<BettingEvent> result = bettingEventService.getBettingEventsByCompetitionAndSeason(competitionId, season);

        assertEquals(mockBettingEvents.size(), result.size());

    }

    @Test
    public void testGetBettingEventsByCompetitionAndSeasonDataAccessException() {
        // Arrange
        Integer competitionId = 1;
        Integer season = 2023;

        when(bettingEventRepository.findByCompetitionAndSeason(competitionId, season))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            bettingEventService.getBettingEventsByCompetitionAndSeason(competitionId, season);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetBettingEventsByCompetitionAndDateSuccess() {

        Integer competitionId = 1;
        String date = "2023-09-15";

        Date theDate = Date.valueOf(date);

        List<BettingEvent> mockBettingEvents = new ArrayList<>();
        mockBettingEvents.add(new BettingEvent());
        mockBettingEvents.add(new BettingEvent());

        when(bettingEventRepository.findBettingEventByCompetitionAndDate(competitionId, theDate))
                .thenReturn(mockBettingEvents);

        List<BettingEvent> result = bettingEventService.getBettingEventsByCompetitionAndDate(competitionId, date);

        assertEquals(mockBettingEvents.size(), result.size());

    }

    @Test
    public void testGetBettingEventsByCompetitionAndDateDataAccessException() {

        Integer competitionId = 1;
        String date = "2023-09-15";

        Date theDate = Date.valueOf(date);

        when(bettingEventRepository.findBettingEventByCompetitionAndDate(competitionId, theDate))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            bettingEventService.getBettingEventsByCompetitionAndDate(competitionId, date);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetEventIdAndCompetitionMapSuccess() {
        // Arrange
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[] { 1, 101 });
        mockResults.add(new Object[] { 2, 102 });

        when(bettingEventRepository.findBettingEventIdAndCompetition()).thenReturn(mockResults);

        Map<Integer, Integer> result = bettingEventService.getEventIdAndCompetitionMap();

        assertEquals(mockResults.size(), result.size());

        assertEquals(101, result.get(1));
        assertEquals(102, result.get(2));
    }

    @Test
    public void testGetEventIdAndCompetitionMapEmptyResults() {

        List<Object[]> mockResults = new ArrayList<>();

        when(bettingEventRepository.findBettingEventIdAndCompetition()).thenReturn(mockResults);

        Map<Integer, Integer> result = bettingEventService.getEventIdAndCompetitionMap();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testIsValidSeasonValidSeason() {

        assertTrue(bettingEventService.isValidSeason(2022));
        assertTrue(bettingEventService.isValidSeason(2023));
        assertTrue(bettingEventService.isValidSeason(2024));
    }

    @Test
    public void testIsValidSeasonInvalidSeason() {

        assertFalse(bettingEventService.isValidSeason(2021));
        assertFalse(bettingEventService.isValidSeason(2025));
        assertFalse(bettingEventService.isValidSeason(0));
    }
}
