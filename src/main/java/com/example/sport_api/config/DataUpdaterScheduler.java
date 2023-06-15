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

    // @Autowired
    // private DataSyncService apiService;

    // (fixedRate = 4 * 60 * 60 * 1000)

    // @Scheduled(cron = "0 48 13 * * *", zone = "Asia/Jerusalem")
    // public void updateTeamsDB() throws JsonMappingException,
    // JsonProcessingException {

    // // System.out.println(apiService.fetchData(teamsResourceUrl));
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
}
