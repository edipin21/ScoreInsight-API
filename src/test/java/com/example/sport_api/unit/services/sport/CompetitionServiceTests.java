package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.exceptions.NotFoundException;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.repositories.soccer.CompetitionRepository;
import com.example.sport_api.services.soccer.CompetitionService;

public class CompetitionServiceTests {

    @InjectMocks
    private CompetitionService competitionService;

    @Mock
    private CompetitionRepository competitionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCompetitionByIdSuccess() {

        int competitionId = 1;
        Competition mockCompetition = new Competition();
        mockCompetition.setCompetitionId(competitionId);

        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(mockCompetition));

        Competition result = competitionService.getCompetitionById(competitionId);

        assertEquals(mockCompetition, result);
    }

    @Test
    public void testGetCompetitionByIdNotFound() {
        // Arrange
        int competitionId = 2; // Replace with an ID that should not be found

        when(competitionRepository.findById(competitionId)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            competitionService.getCompetitionById(competitionId);
        });

        assertEquals("Competition not found", exception.getMessage());
    }

    @Test
    public void testGetCompetitionByIdDataAccessException() {

        int competitionId = 3;

        when(competitionRepository.findById(competitionId)).thenThrow(new DataAccessException("Mock Exception") {
        });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            competitionService.getCompetitionById(competitionId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testIsCompetitionValidValidCompetition() {

        Integer competitionId = 1;
        List<Integer> allCompetitionIds = Arrays.asList(1, 2, 3);

        when(competitionRepository.findAllCompetitionsNumbers()).thenReturn(allCompetitionIds);

        boolean isValid = competitionService.isCompetitionValid(competitionId);

        assertTrue(isValid);
    }

    @Test
    public void testIsCompetitionValidInvalidCompetition() {
        // Arrange
        Integer competitionId = 4;
        List<Integer> allCompetitionIds = Arrays.asList(1, 2, 3);

        when(competitionRepository.findAllCompetitionsNumbers()).thenReturn(allCompetitionIds);

        // Act
        boolean isValid = competitionService.isCompetitionValid(competitionId);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testIsCompetitionValidDataAccessException() {
        // Arrange
        Integer competitionId = 1; // Replace with an existing competition ID

        when(competitionRepository.findAllCompetitionsNumbers()).thenThrow(new DataAccessException("Mock Exception") {
        });

        // Act & Assert
        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            competitionService.isCompetitionValid(competitionId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetSortedCompetitionIdsSuccess() {

        List<Integer> unsortedCompetitionIds = Arrays.asList(3, 1, 2);
        List<Integer> sortedCompetitionIds = Arrays.asList(1, 2, 3);

        when(competitionRepository.findAllCompetitionsNumbers()).thenReturn(unsortedCompetitionIds);

        List<Integer> result = competitionService.getSortedCompetitionIds();

        verify(competitionRepository).findAllCompetitionsNumbers();
        assertEquals(sortedCompetitionIds, result);
    }

    @Test
    public void testGetSortedCompetitionIdsDataAccessException() {

        when(competitionRepository.findAllCompetitionsNumbers()).thenThrow(new DataAccessException("Mock Exception") {});

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            competitionService.getSortedCompetitionIds();
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

}
