package com.example.sport_api.unit.services.betting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.repositories.betting.BettingMarketRepository;
import com.example.sport_api.services.betting.BettingMarketService;

public class BettingMarketServiceTests {

    @InjectMocks
    private BettingMarketService bettingMarketService;

    @Mock
    private BettingMarketRepository bettingMarketRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBettingMarketsByCompetitionAndEventIdSuccess() {

        Integer competitionId = 1;
        Integer eventId = 101;

        List<BettingMarket> mockBettingMarkets = new ArrayList<>();
        mockBettingMarkets.add(new BettingMarket());
        mockBettingMarkets.add(new BettingMarket());

        when(bettingMarketRepository.findByCompetitionAndEventId(competitionId, eventId))
                .thenReturn(mockBettingMarkets);

        List<BettingMarket> result = bettingMarketService.getBettingMarketsByCompetitionAndEventId(competitionId,
                eventId);

        assertEquals(mockBettingMarkets.size(), result.size());

    }

    @Test
    public void testGetBettingMarketsByCompetitionAndEventIdDataAccessException() {

        Integer competitionId = 1;
        Integer eventId = 101;

        when(bettingMarketRepository.findByCompetitionAndEventId(competitionId, eventId))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            bettingMarketService.getBettingMarketsByCompetitionAndEventId(competitionId, eventId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testIsEventIdValid_ValidEventId() {

        Integer eventId = 1;

        List<Integer> mockEventIds = new ArrayList<>();
        mockEventIds.add(1);

        when(bettingMarketRepository.findAllEventsIds()).thenReturn(mockEventIds);

        boolean result = bettingMarketService.isEventIdValid(eventId);

        assertTrue(result);
    }

    @Test
    public void testIsEventIdValid_InvalidEventId() {

        Integer eventId = 5;

        List<Integer> mockEventIds = new ArrayList<>(Arrays.asList(1, 2, 3));

        when(bettingMarketRepository.findAllEventsIds()).thenReturn(mockEventIds);

        boolean result = bettingMarketService.isEventIdValid(eventId);

        assertFalse(result);
    }

}
