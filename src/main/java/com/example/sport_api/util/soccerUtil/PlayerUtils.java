package com.example.sport_api.util.soccerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.dao.DataAccessException;

import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Player;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.services.soccer.TeamDetailService;

public class PlayerUtils {

    public static List<String> getPlayerApiUrls(TeamDetailService teamDetailService) {
        List<TeamDetail> teams = teamDetailService.retriveAllTeamsDetails();

        List<String> playersByTeamUrls = new ArrayList<>();

        teams.forEach(team -> {
            Integer competitionId = team.getCompetition().get(0).getCompetitionId();
            int teamId = team.getTeamId();
            String apiUrl = ExternalSoccerApiEndpoints.PLAYERS_BY_TEAM_RESOURCE_URL +
                    competitionId + "/" + teamId;
            playersByTeamUrls.add(apiUrl);
        });

        return playersByTeamUrls.subList(0, 3);

    }

    public static List<String> getPlayerApiUrlsParallel(TeamDetailService teamDetailService) {
        List<TeamDetail> teams = teamDetailService.retriveAllTeamsDetails();

        List<String> playersByTeamUrls = new ArrayList<>();

        teams.parallelStream().forEach(team -> {
            Integer competitionId = team.getCompetition().get(0).getCompetitionId();
            int teamId = team.getTeamId();
            String apiUrl = ExternalSoccerApiEndpoints.PLAYERS_BY_TEAM_RESOURCE_URL +
                    competitionId + "/" + teamId;
            playersByTeamUrls.add(apiUrl);
        });

        return playersByTeamUrls;

    }

    public static void setTeamDetailToPlayers(List<Player> teamsPlayers, List<TeamDetail> teams) {

        for (int i = 0; i < teamsPlayers.size(); i++) {
            teamsPlayers.get(i).setTeamDetail(teams.get(i));
        }

    }

    public static void setTeamDetailToPlayersParallel(List<Player> teamsPlayers, List<TeamDetail> teams) {
        AtomicInteger index = new AtomicInteger(0);

        teamsPlayers.parallelStream().forEach(player -> {
            int currentIndex = index.getAndIncrement();
            if (currentIndex < teams.size()) {
                player.setTeamDetail(teams.get(currentIndex));
            }
        });
    }

    public static void savePlayersToDB(List<Player> teamPlayers, PlayerRepository playerRepository)
            throws DataAccessException {

        playerRepository.saveAll(teamPlayers);
    }

}
