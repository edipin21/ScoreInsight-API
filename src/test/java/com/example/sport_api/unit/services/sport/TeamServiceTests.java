package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.models.sport.Team;
import com.example.sport_api.repositories.soccer.TeamRepository;
import com.example.sport_api.services.soccer.TeamService;

public class TeamServiceTests {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveTeamByIdSuccess() {

        int teamId = 1;

        Team mockTeam = new Team();
        mockTeam.setTeamId(teamId);
        mockTeam.setName("test");

        when(teamRepository.findById(teamId)).thenReturn(Optional.of(mockTeam));

        // Act
        Team result = teamService.retrieveTeamById(teamId);

        // Assert
        assertNotNull(result);
        assertEquals(mockTeam, result);

    }

    @Test
    public void testRetrieveTeamByIdNotFound() {
        // Arrange
        int teamId = 1; // Replace with appropriate team ID

        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            teamService.retrieveTeamById(teamId);
        });
    }

    @Test
    public void testRetrieveTeamByIdDataAccessException() {

        int teamId = 1;

        when(teamRepository.findById(teamId)).thenThrow(new DataAccessException("Mock Exception") {
        });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            teamService.retrieveTeamById(teamId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testIsTeamIdValid_ValidTeamId() {

        Integer teamId = 1;

        List<Integer> mockTeamIds = new ArrayList<>();
        mockTeamIds.add(1);

        when(teamRepository.findAllTeamsIds()).thenReturn(mockTeamIds);

        boolean result = teamService.isTeamIdValid(teamId);

        assertTrue(result);
    }

    @Test
    public void testIsTeamIdValid_InvalidTeamId() {

        Integer teamId = 1;

        List<Integer> mockTeamIds = new ArrayList<>();

        when(teamRepository.findAllTeamsIds()).thenReturn(mockTeamIds);

        boolean result = teamService.isTeamIdValid(teamId);

        assertFalse(result);
    }

    @Test
    public void testIsTeamIdValidDataAccessException() {

        Integer teamId = 1;

        when(teamRepository.findAllTeamsIds()).thenThrow(new DataAccessException("Mock Exception") {
        });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            teamService.isTeamIdValid(teamId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }
}
