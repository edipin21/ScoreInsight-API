package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Standing {

    @Id
    private int StandingId;
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

    @ManyToOne
    @JoinColumn(name = "RoundId")
    @JsonBackReference("round-standings")
    private Round round;

    @JsonProperty("Group")
    private String GroupName;
    private int GlobalTeamID;

    public Standing() {
        super();
    }

    public Standing(int standingId, int teamId, String name, String shortName, String scope, int teamsOrder, int games,
            int wins, int losses, int draws, int goalsScored, int goalsAgainst, int goalsDifferential, int points,
            Round round, String groupName, int globalTeamID) {
        super();
        StandingId = standingId;
        TeamId = teamId;
        Name = name;
        ShortName = shortName;
        Scope = scope;
        TeamsOrder = teamsOrder;
        Games = games;
        Wins = wins;
        Losses = losses;
        Draws = draws;
        GoalsScored = goalsScored;
        GoalsAgainst = goalsAgainst;
        GoalsDifferential = goalsDifferential;
        Points = points;
        this.round = round;
        GroupName = groupName;
        GlobalTeamID = globalTeamID;
    }

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

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "Standing [StandingId=" + StandingId + ", TeamId=" + TeamId + ", Name=" + Name + ", ShortName="
                + ShortName + ", Scope=" + Scope + ", TeamsOrder=" + TeamsOrder + ", Games=" + Games + ", Wins=" + Wins
                + ", Losses=" + Losses + ", Draws=" + Draws + ", GoalsScored=" + GoalsScored + ", GoalsAgainst="
                + GoalsAgainst + ", GoalsDifferential=" + GoalsDifferential + ", Points=" + Points + ", round=" + round
                + ", GroupName=" + GroupName + ", GlobalTeamID=" + GlobalTeamID + "]";
    }

}
