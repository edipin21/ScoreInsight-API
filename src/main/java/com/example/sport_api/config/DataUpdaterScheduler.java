package com.example.sport_api.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.sport_api.BettingDataSyncService;
import com.example.sport_api.SoccerDataSyncService;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.services.soccer.AreaService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.MembershipService;
import com.example.sport_api.services.soccer.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class DataUpdaterScheduler {

    @Autowired
    private SoccerDataSyncService soccerApiService;

    @Autowired
    private BettingDataSyncService bettingApiService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private MembershipService membershipService;

    // (fixedRate=4*60*60*1000)

    // @Scheduled(initialDelay = 0, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateTeamsDB() throws JsonMappingException,
    // JsonProcessingException {

    // teamService.syncTeamsFromExternalApi();
    // }

    // @Scheduled(initialDelay = 0, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateAreasDB() throws JsonMappingException,
    // JsonProcessingException {
    // areaService.syncAreasFromExternalApi();
    // }

    // @Scheduled(initialDelay = 1, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateCompetitionDB() throws JsonMappingException,
    // JsonProcessingException {
    // soccerApiService.fetchCompetitionsAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateCompetitionfeDB() throws IOException {
    // competitionService.syncCompetitionsFixturesFromExternalApi();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateActiveMembershipDB() throws IOException {
    // membershipService.syncActiveMembershipsFromExternalApiParallel();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateRecentlyChangedMembershipDB() throws IOException {
    // membershipService.syncRecentlyChangedMembershipsFromExternalApiParallel();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updatePlayersByTeamDB() throws IOException {
    // apiService.fetchPlayersbyTeamsAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateScheduleAndStandingsAndTeamSeasonByTeamDB() throws
    // IOException {
    // soccerApiService.fetchAndUpdateScheduleAndStandingsAndTeamSeason();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateVenueDB() throws IOException {
    // apiService.fetchVenuesAndUpdate();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateBoxScoreDB() throws IOException {
    // apiService.fetchAndUpdateBoxScore();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateBettingEvenntsDB() throws IOException {
    // bettingApiService.fetchBettingEventesAndUpdateDB();
    // }

    // @Scheduled(initialDelay = 2, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateBettingMarketDB() throws IOException {
    // bettingApiService.fetchBettingMarketByEventAndUpdateDB();
    // }
}
