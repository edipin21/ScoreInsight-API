package com.example.sport_api.services.soccer;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.models.sport.Player;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.TimeMeasurementUtil;
import com.example.sport_api.util.soccerUtil.PlayerUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class PlayerService {

    private static final Logger logger = LogManager.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamDetailService teamDetailService;

    public void syncPlayersByTeamsFromExternalApi() {

        try {
            TimeMeasurementUtil.startTimer();
            List<TeamDetail> teams = teamDetailService.retriveAllTeamsDetails();
            List<Player> teamsPlayers = new ArrayList<>();
            TypeReference<List<Player>> playerTypeRef = new TypeReference<>() {
            };
            List<String> playersByTeamUrls = PlayerUtils.getPlayerApiUrls(teamDetailService);

            for (String playerUrl : playersByTeamUrls) {
                teamsPlayers = ExternalApiDataFetcherUtil
                        .fetchListDataFromExternalApi(playerUrl, playerTypeRef);

                PlayerUtils.setTeamDetailToPlayers(teamsPlayers, teams);

                PlayerUtils.savePlayersToDB(teamsPlayers, playerRepository);

            }
            TimeMeasurementUtil.timeTaken();
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
            throw e;
        }

    }

    public void syncPlayersByTeamsFromExternalApiParallel() {

        try {
            TimeMeasurementUtil.startTimer();
            List<TeamDetail> teams = teamDetailService.retriveAllTeamsDetails();
            List<Player> teamsPlayers = new ArrayList<>();
            TypeReference<List<Player>> playerTypeRef = new TypeReference<>() {
            };
            List<String> playersByTeamUrls = PlayerUtils.getPlayerApiUrlsParallel(teamDetailService);

            for (String playerUrl : playersByTeamUrls) {
                teamsPlayers = ExternalApiDataFetcherUtil
                        .fetchListDataFromExternalApi(playerUrl, playerTypeRef);

                PlayerUtils.setTeamDetailToPlayersParallel(teamsPlayers, teams);
                PlayerUtils.savePlayersToDB(teamsPlayers, playerRepository);
            }
            TimeMeasurementUtil.timeTaken();
        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
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
