package com.example.sport_api.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Player;
import com.example.sport_api.repositories.PlayerRepository;

@Service
public class PlayerService {

    private static final Logger logger = LogManager.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> retrievePlayersByTeam(Integer teamID) {

        try {
            List<Player> thePlayers = playerRepository.findplayersByTeam(teamID);
            return thePlayers;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while validating competition ID: " + e.getMessage());
            throw e;
        }

    }

}
