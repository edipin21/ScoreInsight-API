package com.example.sport_api;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    // @Scheduled(cron = "0 22 11 * * *", zone = "Asia/Jerusalem")
    // public void myScheduledMethod() {
    // System.out.println("The scheduler is running...");
    // }

    @Scheduled(cron = "0 38 11 * * *", zone = "Asia/Jerusalem")
    public void updateTeams() {
        apiService.fetchTeamsAndUpdate();
    }
}
