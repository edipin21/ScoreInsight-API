package com.example.sport_api.util;

import java.util.List;

import com.example.sport_api.models.sport.PlayerGame;

public class PlayerGameUtils {

    public static void setCompetitionToPlayerGame(List<PlayerGame> players, Integer competition) {
        players.forEach(player -> player.setCompetition(competition));
    }

}
