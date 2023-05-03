package com.example.sport_api.config;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.sport_api.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.annotation.PostConstruct;

@Component
public class DataUpdaterScheduler {

    @Autowired
    private ApiService apiService;

    @PostConstruct
    public void init() {
        ZoneId zone = ZoneId.systemDefault();
        String timeZone = zone.toString();
        System.out.println("Server timezone!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: " + timeZone);
    }

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

    // @Scheduled(initialDelay = 0, fixedRate = 4 * 60 * 60 * 1000)
    // public void updateCompetitionDB() throws JsonMappingException,
    // JsonProcessingException {
    // apiService.fetchCompetitionsAndUpdate();
    // }

    @Scheduled(initialDelay = 1, fixedRate = 4 * 60 * 60 * 1000)
    public void updateCompetitionfeDB() throws JsonMappingException,
            JsonProcessingException {
        apiService.fetchCompetitionFixturesAndUpdate();
    }
}
