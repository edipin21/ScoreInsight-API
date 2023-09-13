package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import com.example.sport_api.models.sport.Membership;
import com.example.sport_api.repositories.soccer.MembershipRepository;
import com.example.sport_api.services.soccer.MembershipService;

public class MembershipServiceTests {
    @InjectMocks
    private MembershipService membershipService;

    @Mock
    private MembershipRepository membershipRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMembershipsByCompetitionSuccess() {

        Integer competitionId = 1;

        List<Membership> mockMemberships = new ArrayList<>();
        Membership membership = new Membership();
        membership.setCompetitionId(competitionId);
        membership.setTeamName("test");
        mockMemberships.add(membership);

        when(membershipRepository.findByCompetitionId(competitionId)).thenReturn(mockMemberships);

        List<Membership> result = membershipService.getMembershipsByCompetition(competitionId);

        assertEquals(mockMemberships.size(), result.size());

    }

    @Test
    public void testGetMembershipsByCompetitionDataAccessException() {

        Integer competitionId = 1;

        when(membershipRepository.findByCompetitionId(competitionId))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            membershipService.getMembershipsByCompetition(competitionId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetRecentlyChangedMembershipsSuccess() {

        Integer competitionId = 1;
        Integer days = 30;

        List<Membership> mockMemberships = new ArrayList<>();
        mockMemberships.add(new Membership());
        mockMemberships.get(0).setCompetitionId(competitionId);

        when(membershipRepository.findRecentMembershipsByCompetitionId(competitionId, days))
                .thenReturn(mockMemberships);

        List<Membership> result = membershipService.getRecentlyChangedMemberships(competitionId, days);

        assertEquals(mockMemberships.size(), result.size());

    }

    @Test
    public void testGetRecentlyChangedMembershipsDataAccessException() {

        Integer competitionId = 1;
        Integer days = 30;

        when(membershipRepository.findRecentMembershipsByCompetitionId(competitionId, days))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            membershipService.getRecentlyChangedMemberships(competitionId, days);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testGetMembershipsByCompetitionAndTeamSuccess() {

        Integer competitionId = 1;
        Integer teamId = 2;

        List<Membership> mockMemberships = new ArrayList<>();
        mockMemberships.add(new Membership());
        mockMemberships.get(0).setCompetitionId(competitionId);
        mockMemberships.get(0).setTeamId(teamId);

        when(membershipRepository.findByCompetitionIdAndTeamId(competitionId, teamId)).thenReturn(mockMemberships);

        List<Membership> result = membershipService.getMembershipsByCompetitionAndTeam(competitionId, teamId);

        assertEquals(mockMemberships.size(), result.size());
        assertEquals(mockMemberships.get(0).getCompetitionId(), result.get(0).getCompetitionId());
    }

    @Test
    public void testGetMembershipsByCompetitionAndTeamDataAccessException() {

        Integer competitionId = 1;
        Integer teamId = 2;

        when(membershipRepository.findByCompetitionIdAndTeamId(competitionId, teamId))
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            membershipService.getMembershipsByCompetitionAndTeam(competitionId, teamId);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }
}
