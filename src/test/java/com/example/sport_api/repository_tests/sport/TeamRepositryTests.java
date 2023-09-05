package com.example.sport_api.repository_tests.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.sport_api.models.sport.Team;
import com.example.sport_api.repositories.soccer.TeamRepository;

@DataJpaTest
public class TeamRepositryTests {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testTeamMapping() {

        // Create a new Team object with sample data
        Team team = new Team();
        team.setTeamId(1);
        team.setName("test");

        // Save the Team object to the repository
        teamRepository.save(team);

        // Retrieve the Team object from the repository using its ID
        // and assign it to the optionalTeam variable
        Optional<Team> optionalTeam = teamRepository.findById(1);

        assertTrue(optionalTeam.isPresent());

        Team savedTeam = optionalTeam.get();

        // Perform assertions to verify that the retrieved data matches the expected
        // values
        assertEquals(1, savedTeam.getTeamId());
        assertEquals("test", savedTeam.getName());

    }

}
