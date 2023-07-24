package com.example.sport_api.models.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@JsonPropertyOrder({ "standingId", "TeamId", "name", "shortName", "scope", "teamsOrder", "games", "wins", "losses",
        "draws", "goalsScored", "goalsAgainst", "goalsDifferential", "points", "groupName", "globalTeamID" })
@Entity
public class Standing {

    @Id
    private Integer StandingId;
    private Integer TeamId;
    private String Name;
    private String ShortName;
    private String Scope;
    private Integer TeamsOrder;
    private Integer Games;
    private Integer Wins;
    private Integer Losses;
    private Integer Draws;
    private Integer GoalsScored;
    private Integer GoalsAgainst;
    private Integer GoalsDifferential;
    private Integer Points;
    private String GroupName;
    private int GlobalTeamID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id", insertable = false, updatable = false)
    private Round round;

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

    @JsonProperty("Order")
    public Integer getTeamsOrder() {
        return TeamsOrder;
    }

    @JsonProperty("Order")
    public void setTeamsOrder(Integer TeamsOrder) {
        this.TeamsOrder = TeamsOrder;
    }

    public Integer getStandingId() {
        return StandingId;
    }

    public void setStandingId(Integer standingId) {
        StandingId = standingId;
    }

    public Integer getTeamId() {
        return TeamId;
    }

    public void setTeamId(Integer teamId) {
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

    public Integer getGames() {
        return Games;
    }

    public void setGames(Integer games) {
        Games = games;
    }

    public Integer getWins() {
        return Wins;
    }

    public void setWins(Integer wins) {
        Wins = wins;
    }

    public Integer getLosses() {
        return Losses;
    }

    public void setLosses(Integer losses) {
        Losses = losses;
    }

    public Integer getDraws() {
        return Draws;
    }

    public void setDraws(Integer draws) {
        Draws = draws;
    }

    public Integer getGoalsScored() {
        return GoalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        GoalsScored = goalsScored;
    }

    public Integer getGoalsAgainst() {
        return GoalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        GoalsAgainst = goalsAgainst;
    }

    public Integer getGoalsDifferential() {
        return GoalsDifferential;
    }

    public void setGoalsDifferential(Integer goalsDifferential) {
        GoalsDifferential = goalsDifferential;
    }

    public Integer getPoints() {
        return Points;
    }

    public void setPoints(Integer points) {
        Points = points;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public int getGlobalTeamID() {
        return GlobalTeamID;
    }

    public void setGlobalTeamID(int globalTeamID) {
        GlobalTeamID = globalTeamID;
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
