package com.example.sport_api.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.sport_api.DataSyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class DataUpdaterScheduler {

    @Autowired
    private DataSyncService apiService;

    // (fixedRate = 4 * 60 * 60 * 1000)
    // @Scheduled(initialDelay = 0, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateTeamsDB() throws JsonMappingException,
    // JsonProcessingException {

    // apiService.fetchTeamsAndUpdate();
    // }

    // @Scheduled(initialDelay = 0, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateAreasDB() throws JsonMappingException,
    // JsonProcessingException {
    // apiService.fetchAreasAndUpdate();
    // }

    // @Scheduled(initialDelay = 1, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateCompetitionDB() throws JsonMappingException,
    // JsonProcessingException {
    // apiService.fetchCompetitionsAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateCompetitionfeDB() throws IOException {
    // apiService.fetchCompetitionFixturesAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateActiveMembershipDB() throws IOException {
    // apiService.fetchActiveMembershipAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateRecentlyChangedMembershipDB() throws IOException {
    // apiService.fetchRecentlyChangedMembershipAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updatePlayersByTeamDB() throws IOException {
    // apiService.fetchPlayersbyTeamsAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateScheduleAndStandingsAndTeamSeasonByTeamDB() throws
    // IOException {
    // apiService.fetchAndUpdateScheduleAndStandingsAndTeamSeason();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateVenueDB() throws IOException {
    // apiService.fetchVenuesAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateBoxScoreDB() throws IOException {
    // apiService.fetchAndUpdateBoxScore();
    // }

}
