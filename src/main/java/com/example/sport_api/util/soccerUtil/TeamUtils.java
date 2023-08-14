package com.example.sport_api.util.soccerUtil;

import java.util.List;
import com.example.sport_api.models.sport.Team;
import com.example.sport_api.repositories.soccer.TeamRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;

public class TeamUtils {

    public static void saveTeamsToDatabase(List<Team> teams, TeamRepository teamRepository) {
        teamRepository.saveAll(teams);
    }

    public static void handleExternalApiException(Exception e) {
        ExternalApiDataFetcherUtil.handleException(e);
        throw new RuntimeException("Failed to synchronize teams from external API", e);
    }
}
