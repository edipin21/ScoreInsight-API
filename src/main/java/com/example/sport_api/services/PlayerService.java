package com.example.sport_api.services;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.sport.Player;
import com.example.sport_api.repositories.soccer.PlayerRepository;

@Service
public class PlayerService {

    private static final Logger logger = LogManager.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> retrievePlayersByTeam(Integer teamID) {

        try {
            List<Player> thePlayers = playerRepository.findPlayersByTeam(teamID);
            return thePlayers;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }

    }

}
