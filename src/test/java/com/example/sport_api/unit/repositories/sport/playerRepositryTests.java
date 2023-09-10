package com.example.sport_api.unit.repositories.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.sport_api.models.sport.Player;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.repositories.soccer.TeamDetailRepository;

@DataJpaTest
public class playerRepositryTests {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamDetailRepository teamDetailRepository;

    @Test
    public void testPlayerMapping() {
        // Create a new Player object with sample data

        Player player = new Player();
        player.setPlayerId(1);
        player.setFirstName("test");

        // Save the Player object to the repository
        playerRepository.save(player);

        // Retrieve the Player object from the repository using its ID
        // and assign it to the savedArea variable
        Optional<Player> optionalPlayer = playerRepository.findById(1);

        assertTrue(optionalPlayer.isPresent());
        // Perform assertions to verify that the retrieved data matches the expected
        // values
        Player savedPlayer = optionalPlayer.get();

        assertEquals(1, savedPlayer.getPlayerId());
        assertEquals("test", savedPlayer.getFirstName());

    }

    @Test
    public void testPlayerRelationships() {
        // Create related entity
        TeamDetail teamDetail = new TeamDetail();
        teamDetail.setTeamId(1);
        teamDetail.setName("test");
        teamDetailRepository.save(teamDetail);

        // Create a Player entity and associate it with related entity
        Player player = new Player();
        player.setPlayerId(1);
        player.setTeamDetail(teamDetail);
        playerRepository.save(player);

        // Retrieve the saved player entity
        Optional<Player> optionalPlayer = playerRepository.findById(1);

        assertTrue(optionalPlayer.isPresent());

        Player savedPlayer = optionalPlayer.get();

        // Verify that the relationship are correctly established
        assertEquals(1, savedPlayer.getTeamDetail().getTeamId());
        assertEquals("test", savedPlayer.getTeamDetail().getName());

    }

}
