package com.example.sport_api.util;

import java.util.List;

import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.PlayerRepository;

public class TeamDetailUtils {

    public static void setPlayersToTeams(List<TeamDetail> teams, PlayerRepository playerRepository) {

        teams.forEach(team -> team.setPlayers(playerRepository.findPlayersByTeam(team.getTeamId())));

    }
}
