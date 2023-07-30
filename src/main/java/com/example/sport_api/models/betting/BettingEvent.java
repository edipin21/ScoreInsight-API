package com.example.sport_api.models.betting;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({ "bettingEventID", "name", "season", "bettingEventTypeID", "bettingEventType", "startDate",
        "created", "updated", "gameID", "globalGameID", "gameStatus", "quarter", "awayTeam", "homeTeam", "awayTeamID",
        "homeTeamID", "globalAwayTeamID", "globalHomeTeamID", "awayTeamScore", "homeTeamScore", "totalScore",
        "awayRotationNumber", "homeRotationNumber", "competitionId" })
public class BettingEvent {

    @Id
    private int BettingEventID;
    private String Name;
    private int Season;
    private int BettingEventTypeID;
    private String BettingEventType;
    private Date StartDate;
    private Date Created;
    private Date Updated;
    private int GameID;
    private int GlobalGameID;
    private String GameStatus;
    private String Quarter;
    private String AwayTeam;
    private String HomeTeam;
    private int AwayTeamID;
    private int HomeTeamID;
    private int GlobalAwayTeamID;
    private int GlobalHomeTeamID;
    private int AwayTeamScore;
    private int HomeTeamScore;
    private int TotalScore;
    private int AwayRotationNumber;
    private int HomeRotationNumber;
    private int CompetitionId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BettingEventID")
    private List<BettingMarket> BettingMarkets;

    public BettingEvent() {
    }

    public BettingEvent(int bettingEventID, String name, int season, int bettingEventTypeID, String bettingEventType,
            Date startDate, Date created, Date updated, int gameID, int globalGameID, String gameStatus, String quarter,
            String awayTeam, String homeTeam, int awayTeamID, int homeTeamID, int globalAwayTeamID,
            int globalHomeTeamID, int awayTeamScore, int homeTeamScore, int totalScore, int awayRotationNumber,
            int homeRotationNumber, int competitionId, List<BettingMarket> bettingMarkets) {
        BettingEventID = bettingEventID;
        Name = name;
        Season = season;
        BettingEventTypeID = bettingEventTypeID;
        BettingEventType = bettingEventType;
        StartDate = startDate;
        Created = created;
        Updated = updated;
        GameID = gameID;
        GlobalGameID = globalGameID;
        GameStatus = gameStatus;
        Quarter = quarter;
        AwayTeam = awayTeam;
        HomeTeam = homeTeam;
        AwayTeamID = awayTeamID;
        HomeTeamID = homeTeamID;
        GlobalAwayTeamID = globalAwayTeamID;
        GlobalHomeTeamID = globalHomeTeamID;
        AwayTeamScore = awayTeamScore;
        HomeTeamScore = homeTeamScore;
        TotalScore = totalScore;
        AwayRotationNumber = awayRotationNumber;
        HomeRotationNumber = homeRotationNumber;
        CompetitionId = competitionId;
        BettingMarkets = bettingMarkets;
    }

    public int getBettingEventID() {
        return BettingEventID;
    }

    public void setBettingEventID(int bettingEventID) {
        BettingEventID = bettingEventID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSeason() {
        return Season;
    }

    public void setSeason(int season) {
        Season = season;
    }

    public int getBettingEventTypeID() {
        return BettingEventTypeID;
    }

    public void setBettingEventTypeID(int bettingEventTypeID) {
        BettingEventTypeID = bettingEventTypeID;
    }

    public String getBettingEventType() {
        return BettingEventType;
    }

    public void setBettingEventType(String bettingEventType) {
        BettingEventType = bettingEventType;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public Date getUpdated() {
        return Updated;
    }

    public void setUpdated(Date updated) {
        Updated = updated;
    }

    public int getGameID() {
        return GameID;
    }

    public void setGameID(int gameID) {
        GameID = gameID;
    }

    public int getGlobalGameID() {
        return GlobalGameID;
    }

    public void setGlobalGameID(int globalGameID) {
        GlobalGameID = globalGameID;
    }

    public String getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(String gameStatus) {
        GameStatus = gameStatus;
    }

    public String getQuarter() {
        return Quarter;
    }

    public void setQuarter(String quarter) {
        Quarter = quarter;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        AwayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }

    public int getAwayTeamID() {
        return AwayTeamID;
    }

    public void setAwayTeamID(int awayTeamID) {
        AwayTeamID = awayTeamID;
    }

    public int getHomeTeamID() {
        return HomeTeamID;
    }

    public void setHomeTeamID(int homeTeamID) {
        HomeTeamID = homeTeamID;
    }

    public int getGlobalAwayTeamID() {
        return GlobalAwayTeamID;
    }

    public void setGlobalAwayTeamID(int globalAwayTeamID) {
        GlobalAwayTeamID = globalAwayTeamID;
    }

    public int getGlobalHomeTeamID() {
        return GlobalHomeTeamID;
    }

    public void setGlobalHomeTeamID(int globalHomeTeamID) {
        GlobalHomeTeamID = globalHomeTeamID;
    }

    public int getAwayTeamScore() {
        return AwayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        AwayTeamScore = awayTeamScore;
    }

    public int getHomeTeamScore() {
        return HomeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        HomeTeamScore = homeTeamScore;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }

    public int getAwayRotationNumber() {
        return AwayRotationNumber;
    }

    public void setAwayRotationNumber(int awayRotationNumber) {
        AwayRotationNumber = awayRotationNumber;
    }

    public int getHomeRotationNumber() {
        return HomeRotationNumber;
    }

    public void setHomeRotationNumber(int homeRotationNumber) {
        HomeRotationNumber = homeRotationNumber;
    }

    public int getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(int competitionId) {
        CompetitionId = competitionId;
    }

    public List<BettingMarket> getBettingMarkets() {
        return BettingMarkets;
    }

    public void setBettingMarkets(List<BettingMarket> bettingMarkets) {
        BettingMarkets = bettingMarkets;
    }

    @Override
    public String toString() {
        return "BettingEvent [BettingEventID=" + BettingEventID + ", Name=" + Name + ", Season=" + Season
                + ", BettingEventTypeID=" + BettingEventTypeID + ", BettingEventType=" + BettingEventType
                + ", StartDate=" + StartDate + ", Created=" + Created + ", Updated=" + Updated + ", GameID=" + GameID
                + ", GlobalGameID=" + GlobalGameID + ", GameStatus=" + GameStatus + ", Quarter=" + Quarter
                + ", AwayTeam=" + AwayTeam + ", HomeTeam=" + HomeTeam + ", AwayTeamID=" + AwayTeamID + ", HomeTeamID="
                + HomeTeamID + ", GlobalAwayTeamID=" + GlobalAwayTeamID + ", GlobalHomeTeamID=" + GlobalHomeTeamID
                + ", AwayTeamScore=" + AwayTeamScore + ", HomeTeamScore=" + HomeTeamScore + ", TotalScore=" + TotalScore
                + ", AwayRotationNumber=" + AwayRotationNumber + ", HomeRotationNumber=" + HomeRotationNumber
                + ", CompetitionId=" + CompetitionId + ", BettingMarkets=" + BettingMarkets + "]";
    }

}
