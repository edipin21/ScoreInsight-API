package com.example.sport_api.standing;

import com.example.sport_api.round.Round;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Standing {

    @Id
    private int StandingId;

    // private int RoundId;
    private int TeamId;
    private String Name;
    private String ShortName;
    private String Scope;
    @JsonProperty("order")
    private int TeamsOrder;
    private int Games;
    private int Wins;
    private int Losses;
    private int Draws;
    private int GoalsScored;
    private int GoalsAgainst;
    private int GoalsDifferential;
    private int Points;
    // fix !
    @JsonProperty("Group")
    private String GroupName;
    private int GlobalTeamID;

    public Standing() {
        super();
    }

    @ManyToOne
    @JoinColumn(name = "RoundId")
    private Round round;

    // public Standing(int standingId, int roundId, int teamId, String name, String
    // shortName, String scope, int order,
    // int games, int wins, int losses, int draws, int goalsScored, int
    // goalsAgainst, int goalsDifferential,
    // int points, String group, int globalTeamID) {
    // super();
    // StandingId = standingId;
    // RoundId = roundId;
    // TeamId = teamId;
    // Name = name;
    // ShortName = shortName;
    // Scope = scope;
    // Order = order;
    // Games = games;
    // Wins = wins;
    // Losses = losses;
    // Draws = draws;
    // GoalsScored = goalsScored;
    // GoalsAgainst = goalsAgainst;
    // GoalsDifferential = goalsDifferential;
    // Points = points;
    // Group = group;
    // GlobalTeamID = globalTeamID;
    // }

    public int getStandingId() {
        return StandingId;
    }

    public void setStandingId(int standingId) {
        StandingId = standingId;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }

    public int getTeamsOrder() {
        return TeamsOrder;
    }

    public void setTeamsOrder(int TeamsOrder) {
        this.TeamsOrder = TeamsOrder;
    }

    public int getGames() {
        return Games;
    }

    public void setGames(int games) {
        Games = games;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public int getLosses() {
        return Losses;
    }

    public void setLosses(int losses) {
        Losses = losses;
    }

    public int getDraws() {
        return Draws;
    }

    public void setDraws(int draws) {
        Draws = draws;
    }

    public int getGoalsScored() {
        return GoalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        GoalsScored = goalsScored;
    }

    public int getGoalsAgainst() {
        return GoalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        GoalsAgainst = goalsAgainst;
    }

    public int getGoalsDifferential() {
        return GoalsDifferential;
    }

    public void setGoalsDifferential(int goalsDifferential) {
        GoalsDifferential = goalsDifferential;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getGlobalTeamID() {
        return GlobalTeamID;
    }

    public void setGlobalTeamID(int globalTeamID) {
        GlobalTeamID = globalTeamID;
    }

}
