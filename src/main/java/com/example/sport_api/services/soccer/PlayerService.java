package com.example.sport_api.services.soccer;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Player;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.repositories.soccer.TeamDetailRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class PlayerService {

    private static final Logger logger = LogManager.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamDetailRepository teamDetailRepository;

    // need to add parllel implemtation !!!!!!
    public void syncPlayersByTeamsFromExternalApi() throws JsonProcessingException {

        try {
            int count = 0;
            List<TeamDetail> teams = teamDetailRepository.findAll();
            List<Player> players = new ArrayList<>();
            TypeReference<List<Player>> playerTypeRef = new TypeReference<>() {
            };

            // Partial loop for sanity checks - delete count
            for (int i = 0; i < teams.size(); i++) {
                TeamDetail team = teams.get(i);
                Integer competitionId = team.getCompetition().get(0).getCompetitionId();
                int teamId = team.getTeamId();
                String apiUrl = ExternalSoccerApiEndpoints.PLAYERS_BY_TEAM_RESOURCE_URL +
                        competitionId + "/" + teamId;

                if (count == 3) {
                    break;
                }

                players = ExternalApiDataFetcherUtil
                        .fetchListDataFromExternalApi(apiUrl, playerTypeRef);

                int teamNum = i;
                players.forEach(player -> player.setTeamDetail(teams.get(teamNum)));

                playerRepository.saveAll(players);

                count++;

            }
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

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
