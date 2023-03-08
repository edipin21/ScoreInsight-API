package com.example.sport_api.game;

import java.sql.Date;
import com.example.sport_api.playoffAggregateScore.PlayoffAggregateScore;
import com.example.sport_api.round.Round;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Game {
    @Id
    private int GameId;
    private int Season;
    private int SeasonType;
    @JsonProperty("Group")
    private String GroupName;
    private int AwayTeamId;
    private int HomeTeamId;
    private int VenueId;
    private Date Day;
    private Date DateTime;
    private String Status;
    private int Week;
    private String Period;
    private int Clock;
    private String Winner;
    private String VenueType;
    private String AwayTeamKey;
    private String AwayTeamName;
    private String AwayTeamCountryCode;
    private int AwayTeamScore;
    private int AwayTeamScorePeriod1;
    private int AwayTeamScorePeriod2;
    private int AwayTeamScoreExtraTime;
    private int AwayTeamScorePenalty;
    private String HomeTeamKey;
    private String HomeTeamName;
    private String HomeTeamCountryCode;
    private int HomeTeamScore;
    private int HomeTeamScorePeriod1;
    private int HomeTeamScorePeriod2;
    private int HomeTeamScoreExtraTime;
    private int HomeTeamScorePenalty;
    private int HomeTeamMoneyLine;
    private int AwayTeamMoneyLine;
    private int DrawMoneyLine;
    private float PointSpread;
    private int HomeTeamPointSpreadPayout;
    private int AwayTeamPointSpreadPayout;
    private float OverUnder;
    private int OverPayout;
    private int UnderPayout;
    private int Attendance;
    private Date Updated;
    private Date UpdatedUtc;
    private int GlobalGameId;
    private int GlobalAwayTeamId;
    private int GlobalHomeTeamId;
    private int ClockExtra;
    private String ClockDisplay;
    private boolean IsClosed;
    private String HomeTeamFormation;
    private String AwayTeamFormation;

    @OneToOne
    private PlayoffAggregateScore playoffAggregateScore;

    @ManyToOne
    @JoinColumn(name = "RoundId")
    private Round round;

    public Game() {
        super();
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public int getSeason() {
        return Season;
    }

    public void setSeason(int season) {
        Season = season;
    }

    public int getSeasonType() {
        return SeasonType;
    }

    public void setSeasonType(int seasonType) {
        SeasonType = seasonType;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public int getAwayTeamId() {
        return AwayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        AwayTeamId = awayTeamId;
    }

    public int getHomeTeamId() {
        return HomeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        HomeTeamId = homeTeamId;
    }

    public int getVenueId() {
        return VenueId;
    }

    public void setVenueId(int venueId) {
        VenueId = venueId;
    }

    public Date getDay() {
        return Day;
    }

    public void setDay(Date day) {
        Day = day;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getWeek() {
        return Week;
    }

    public void setWeek(int week) {
        Week = week;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public int getClock() {
        return Clock;
    }

    public void setClock(int clock) {
        Clock = clock;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getVenueType() {
        return VenueType;
    }

    public void setVenueType(String venueType) {
        VenueType = venueType;
    }

    public String getAwayTeamKey() {
        return AwayTeamKey;
    }

    public void setAwayTeamKey(String awayTeamKey) {
        AwayTeamKey = awayTeamKey;
    }

    public String getAwayTeamName() {
        return AwayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        AwayTeamName = awayTeamName;
    }

    public String getAwayTeamCountryCode() {
        return AwayTeamCountryCode;
    }

    public void setAwayTeamCountryCode(String awayTeamCountryCode) {
        AwayTeamCountryCode = awayTeamCountryCode;
    }

    public int getAwayTeamScore() {
        return AwayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        AwayTeamScore = awayTeamScore;
    }

    public int getAwayTeamScorePeriod1() {
        return AwayTeamScorePeriod1;
    }

    public void setAwayTeamScorePeriod1(int awayTeamScorePeriod1) {
        AwayTeamScorePeriod1 = awayTeamScorePeriod1;
    }

    public int getAwayTeamScorePeriod2() {
        return AwayTeamScorePeriod2;
    }

    public void setAwayTeamScorePeriod2(int awayTeamScorePeriod2) {
        AwayTeamScorePeriod2 = awayTeamScorePeriod2;
    }

    public int getAwayTeamScoreExtraTime() {
        return AwayTeamScoreExtraTime;
    }

    public void setAwayTeamScoreExtraTime(int awayTeamScoreExtraTime) {
        AwayTeamScoreExtraTime = awayTeamScoreExtraTime;
    }

    public int getAwayTeamScorePenalty() {
        return AwayTeamScorePenalty;
    }

    public void setAwayTeamScorePenalty(int awayTeamScorePenalty) {
        AwayTeamScorePenalty = awayTeamScorePenalty;
    }

    public String getHomeTeamKey() {
        return HomeTeamKey;
    }

    public void setHomeTeamKey(String homeTeamKey) {
        HomeTeamKey = homeTeamKey;
    }

    public String getHomeTeamName() {
        return HomeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        HomeTeamName = homeTeamName;
    }

    public String getHomeTeamCountryCode() {
        return HomeTeamCountryCode;
    }

    public void setHomeTeamCountryCode(String homeTeamCountryCode) {
        HomeTeamCountryCode = homeTeamCountryCode;
    }

    public int getHomeTeamScore() {
        return HomeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        HomeTeamScore = homeTeamScore;
    }

    public int getHomeTeamScorePeriod1() {
        return HomeTeamScorePeriod1;
    }

    public void setHomeTeamScorePeriod1(int homeTeamScorePeriod1) {
        HomeTeamScorePeriod1 = homeTeamScorePeriod1;
    }

    public int getHomeTeamScorePeriod2() {
        return HomeTeamScorePeriod2;
    }

    public void setHomeTeamScorePeriod2(int homeTeamScorePeriod2) {
        HomeTeamScorePeriod2 = homeTeamScorePeriod2;
    }

    public int getHomeTeamScoreExtraTime() {
        return HomeTeamScoreExtraTime;
    }

    public void setHomeTeamScoreExtraTime(int homeTeamScoreExtraTime) {
        HomeTeamScoreExtraTime = homeTeamScoreExtraTime;
    }

    public int getHomeTeamScorePenalty() {
        return HomeTeamScorePenalty;
    }

    public void setHomeTeamScorePenalty(int homeTeamScorePenalty) {
        HomeTeamScorePenalty = homeTeamScorePenalty;
    }

    public int getHomeTeamMoneyLine() {
        return HomeTeamMoneyLine;
    }

    public void setHomeTeamMoneyLine(int homeTeamMoneyLine) {
        HomeTeamMoneyLine = homeTeamMoneyLine;
    }

    public int getAwayTeamMoneyLine() {
        return AwayTeamMoneyLine;
    }

    public void setAwayTeamMoneyLine(int awayTeamMoneyLine) {
        AwayTeamMoneyLine = awayTeamMoneyLine;
    }

    public int getDrawMoneyLine() {
        return DrawMoneyLine;
    }

    public void setDrawMoneyLine(int drawMoneyLine) {
        DrawMoneyLine = drawMoneyLine;
    }

    public float getPointSpread() {
        return PointSpread;
    }

    public void setPointSpread(float pointSpread) {
        PointSpread = pointSpread;
    }

    public int getHomeTeamPointSpreadPayout() {
        return HomeTeamPointSpreadPayout;
    }

    public void setHomeTeamPointSpreadPayout(int homeTeamPointSpreadPayout) {
        HomeTeamPointSpreadPayout = homeTeamPointSpreadPayout;
    }

    public int getAwayTeamPointSpreadPayout() {
        return AwayTeamPointSpreadPayout;
    }

    public void setAwayTeamPointSpreadPayout(int awayTeamPointSpreadPayout) {
        AwayTeamPointSpreadPayout = awayTeamPointSpreadPayout;
    }

    public float getOverUnder() {
        return OverUnder;
    }

    public void setOverUnder(float overUnder) {
        OverUnder = overUnder;
    }

    public int getOverPayout() {
        return OverPayout;
    }

    public void setOverPayout(int overPayout) {
        OverPayout = overPayout;
    }

    public int getUnderPayout() {
        return UnderPayout;
    }

    public void setUnderPayout(int underPayout) {
        UnderPayout = underPayout;
    }

    public int getAttendance() {
        return Attendance;
    }

    public void setAttendance(int attendance) {
        Attendance = attendance;
    }

    public Date getUpdated() {
        return Updated;
    }

    public void setUpdated(Date updated) {
        Updated = updated;
    }

    public Date getUpdatedUtc() {
        return UpdatedUtc;
    }

    public void setUpdatedUtc(Date updatedUtc) {
        UpdatedUtc = updatedUtc;
    }

    public int getGlobalGameId() {
        return GlobalGameId;
    }

    public void setGlobalGameId(int globalGameId) {
        GlobalGameId = globalGameId;
    }

    public int getGlobalAwayTeamId() {
        return GlobalAwayTeamId;
    }

    public void setGlobalAwayTeamId(int globalAwayTeamId) {
        GlobalAwayTeamId = globalAwayTeamId;
    }

    public int getGlobalHomeTeamId() {
        return GlobalHomeTeamId;
    }

    public void setGlobalHomeTeamId(int globalHomeTeamId) {
        GlobalHomeTeamId = globalHomeTeamId;
    }

    public int getClockExtra() {
        return ClockExtra;
    }

    public void setClockExtra(int clockExtra) {
        ClockExtra = clockExtra;
    }

    public String getClockDisplay() {
        return ClockDisplay;
    }

    public void setClockDisplay(String clockDisplay) {
        ClockDisplay = clockDisplay;
    }

    public boolean isIsClosed() {
        return IsClosed;
    }

    public void setIsClosed(boolean isClosed) {
        IsClosed = isClosed;
    }

    public String getHomeTeamFormation() {
        return HomeTeamFormation;
    }

    public void setHomeTeamFormation(String homeTeamFormation) {
        HomeTeamFormation = homeTeamFormation;
    }

    public String getAwayTeamFormation() {
        return AwayTeamFormation;
    }

    public void setAwayTeamFormation(String awayTeamFormation) {
        AwayTeamFormation = awayTeamFormation;
    }

    public PlayoffAggregateScore getPlayoffAggregateScore() {
        return playoffAggregateScore;
    }

    public void setPlayoffAggregateScore(PlayoffAggregateScore playoffAggregateScore) {
        this.playoffAggregateScore = playoffAggregateScore;
    }

}
