package com.example.sport_api.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.sport_api.services.betting.BettingEventService;
import com.example.sport_api.services.betting.BettingMarketService;
import com.example.sport_api.services.soccer.AreaService;
import com.example.sport_api.services.soccer.BoxScoreService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.services.soccer.MembershipService;
import com.example.sport_api.services.soccer.PlayerService;
import com.example.sport_api.services.soccer.RoundService;
import com.example.sport_api.services.soccer.TeamService;
import com.example.sport_api.services.soccer.VenueService;

@Service
public class DatabaseUpdaterService {

    private final ReentrantLock updateLock = new ReentrantLock();
    private final Object competitionLock = new Object();

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BoxScoreService boxScoreService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private BettingEventService bettingEventService;

    @Autowired
    private BettingMarketService bettingMarketService;

    // @Async
    // @Scheduled(fixedDelay = 3600000)
    // public void updateSoccerDBEveryHour() {
    // try {
    // if (updateLock.tryLock(1, TimeUnit.MINUTES)) {
    // try {
    // CompletableFuture.runAsync(() -> {
    // synchronized (competitionLock) {
    // competitionService.syncCompetitionsFromExternalApi();
    // }
    // });

    // CompletableFuture.runAsync(() -> {
    // synchronized (competitionLock) {
    // competitionService.syncCompetitionsFixturesFromExternalApi();
    // }
    // });

    // CompletableFuture.runAsync(() -> {
    // membershipService.syncRecentlyChangedMembershipsFromExternalApi();
    // });

    // CompletableFuture.runAsync(() -> {
    // playerService.syncPlayersByTeamsFromExternalApi();
    // });

    // } finally {
    // updateLock.unlock();
    // }
    // } else {
    // System.out.println("Lock not acquired, update postponed.");
    // }
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // }
    // }

    // @Async
    // @Scheduled(fixedDelay = 60000)
    // public void updateSoccerDBEvery1Min() {
    // try {
    // if (updateLock.tryLock(30, TimeUnit.SECONDS)) {
    // try {
    // CompletableFuture.runAsync(() -> {
    // boxScoreService.syncBoxScoreFromExternalDB();
    // });
    // } finally {
    // updateLock.unlock();
    // }
    // }
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // }
    // }

    // @Async
    // @Scheduled(fixedDelay = 300000)
    // public void updateSoccerDBEvery5Min() {
    // CompletableFuture.runAsync(() -> {
    // roundService.syncScheduleAndStandingsAndTeamSeasonFromExternalApi();
    // });

    // }

    // @Async
    // @Scheduled(fixedDelay = 14400000)
    // public void updateSoccerDBEvery4Hours() {

    // try {
    // CompletableFuture.runAsync(() -> {
    // venueService.syncVenuesFromExternalApi();
    // });

    // try {
    // if (updateLock.tryLock(5, TimeUnit.MINUTES)) {
    // CompletableFuture.runAsync(() -> {
    // areaService.syncAreasFromExternalApi();
    // });
    // CompletableFuture.runAsync(() -> {
    // teamService.syncTeamsFromExternalApi();
    // });

    // }
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // }

    // } finally {
    // updateLock.unlock();
    // }
    // }

    // @Async
    // @Scheduled(cron = "0 0 6 * * ?")
    // public void updateSoccerDBEveryday() {
    // try {
    // if (updateLock.tryLock(10, TimeUnit.MINUTES)) {
    // try {
    // CompletableFuture.runAsync(() -> {
    // membershipService.syncActiveMembershipsFromExternalApi();
    // });
    // } finally {
    // updateLock.unlock();
    // }

    // }
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // }

    // }

    // @Async
    // @Scheduled(fixedDelay = 5000)
    // public void updateBettingDBEvery10Min() {
    // // bettingEventService.syncBettingEventesFromExternalApi();
    // bettingMarketService.syncBettingMarketsFromExternalApi();
    // }
}
