package com.example.sport_api.unit.repositories.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.sport_api.models.sport.PlayerGame;
import com.example.sport_api.repositories.soccer.PlayerGameRepository;

@DataJpaTest
public class playerGameRepositoryTests {

    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Test
    public void testPlayerGameMapping() {

        PlayerGame playerGame = new PlayerGame();
        playerGame.setPlayerId(1);
        playerGame.setName("test");

        playerGameRepository.save(playerGame);

        Optional<PlayerGame> optionalPlayerGame = playerGameRepository.findById(1);

        assertTrue(optionalPlayerGame.isPresent());

        PlayerGame savedPlayerGame = optionalPlayerGame.get();

        assertEquals(1, savedPlayerGame.getPlayerId());
        assertEquals("test", savedPlayerGame.getName());

    }

}
