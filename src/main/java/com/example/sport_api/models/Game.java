package com.example.sport_api.models;

import java.sql.Date;

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

    public Game(int gameId, int season, int seasonType, String groupName, int awayTeamId, int homeTeamId, int venueId,
            Date day, Date dateTime, String status, int week, String period, int clock, String winner, String venueType,
            String awayTeamKey, String awayTeamName, String awayTeamCountryCode, int awayTeamScore,
            int awayTeamScorePeriod1, int awayTeamScorePeriod2, int awayTeamScoreExtraTime, int awayTeamScorePenalty,
            String homeTeamKey, String homeTeamName, String homeTeamCountryCode, int homeTeamScore,
            int homeTeamScorePeriod1, int homeTeamScorePeriod2, int homeTeamScoreExtraTime, int homeTeamScorePenalty,
            int homeTeamMoneyLine, int awayTeamMoneyLine, int drawMoneyLine, float pointSpread,
            int homeTeamPointSpreadPayout, int awayTeamPointSpreadPayout, float overUnder, int overPayout,
            int underPayout, int attendance, Date updated, Date updatedUtc, int globalGameId, int globalAwayTeamId,
            int globalHomeTeamId, int clockExtra, String clockDisplay, boolean isClosed, String homeTeamFormation,
            String awayTeamFormation, PlayoffAggregateScore playoffAggregateScore, Round round) {
        super();
        GameId = gameId;
        Season = season;
        SeasonType = seasonType;
        GroupName = groupName;
        AwayTeamId = awayTeamId;
        HomeTeamId = homeTeamId;
        VenueId = venueId;
        Day = day;
        DateTime = dateTime;
        Status = status;
        Week = week;
        Period = period;
        Clock = clock;
        Winner = winner;
        VenueType = venueType;
        AwayTeamKey = awayTeamKey;
        AwayTeamName = awayTeamName;
        AwayTeamCountryCode = awayTeamCountryCode;
        AwayTeamScore = awayTeamScore;
        AwayTeamScorePeriod1 = awayTeamScorePeriod1;
        AwayTeamScorePeriod2 = awayTeamScorePeriod2;
        AwayTeamScoreExtraTime = awayTeamScoreExtraTime;
        AwayTeamScorePenalty = awayTeamScorePenalty;
        HomeTeamKey = homeTeamKey;
        HomeTeamName = homeTeamName;
        HomeTeamCountryCode = homeTeamCountryCode;
        HomeTeamScore = homeTeamScore;
        HomeTeamScorePeriod1 = homeTeamScorePeriod1;
        HomeTeamScorePeriod2 = homeTeamScorePeriod2;
        HomeTeamScoreExtraTime = homeTeamScoreExtraTime;
        HomeTeamScorePenalty = homeTeamScorePenalty;
        HomeTeamMoneyLine = homeTeamMoneyLine;
        AwayTeamMoneyLine = awayTeamMoneyLine;
        DrawMoneyLine = drawMoneyLine;
        PointSpread = pointSpread;
        HomeTeamPointSpreadPayout = homeTeamPointSpreadPayout;
        AwayTeamPointSpreadPayout = awayTeamPointSpreadPayout;
        OverUnder = overUnder;
        OverPayout = overPayout;
        UnderPayout = underPayout;
        Attendance = attendance;
        Updated = updated;
        UpdatedUtc = updatedUtc;
        GlobalGameId = globalGameId;
        GlobalAwayTeamId = globalAwayTeamId;
        GlobalHomeTeamId = globalHomeTeamId;
        ClockExtra = clockExtra;
        ClockDisplay = clockDisplay;
        IsClosed = isClosed;
        HomeTeamFormation = homeTeamFormation;
        AwayTeamFormation = awayTeamFormation;
        this.playoffAggregateScore = playoffAggregateScore;
        this.round = round;
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

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "Game [GameId=" + GameId + ", Season=" + Season + ", SeasonType=" + SeasonType + ", GroupName="
                + GroupName + ", AwayTeamId=" + AwayTeamId + ", HomeTeamId=" + HomeTeamId + ", VenueId=" + VenueId
                + ", Day=" + Day + ", DateTime=" + DateTime + ", Status=" + Status + ", Week=" + Week + ", Period="
                + Period + ", Clock=" + Clock + ", Winner=" + Winner + ", VenueType=" + VenueType + ", AwayTeamKey="
                + AwayTeamKey + ", AwayTeamName=" + AwayTeamName + ", AwayTeamCountryCode=" + AwayTeamCountryCode
                + ", AwayTeamScore=" + AwayTeamScore + ", AwayTeamScorePeriod1=" + AwayTeamScorePeriod1
                + ", AwayTeamScorePeriod2=" + AwayTeamScorePeriod2 + ", AwayTeamScoreExtraTime="
                + AwayTeamScoreExtraTime + ", AwayTeamScorePenalty=" + AwayTeamScorePenalty + ", HomeTeamKey="
                + HomeTeamKey + ", HomeTeamName=" + HomeTeamName + ", HomeTeamCountryCode=" + HomeTeamCountryCode
                + ", HomeTeamScore=" + HomeTeamScore + ", HomeTeamScorePeriod1=" + HomeTeamScorePeriod1
                + ", HomeTeamScorePeriod2=" + HomeTeamScorePeriod2 + ", HomeTeamScoreExtraTime="
                + HomeTeamScoreExtraTime + ", HomeTeamScorePenalty=" + HomeTeamScorePenalty + ", HomeTeamMoneyLine="
                + HomeTeamMoneyLine + ", AwayTeamMoneyLine=" + AwayTeamMoneyLine + ", DrawMoneyLine=" + DrawMoneyLine
                + ", PointSpread=" + PointSpread + ", HomeTeamPointSpreadPayout=" + HomeTeamPointSpreadPayout
                + ", AwayTeamPointSpreadPayout=" + AwayTeamPointSpreadPayout + ", OverUnder=" + OverUnder
                + ", OverPayout=" + OverPayout + ", UnderPayout=" + UnderPayout + ", Attendance=" + Attendance
                + ", Updated=" + Updated + ", UpdatedUtc=" + UpdatedUtc + ", GlobalGameId=" + GlobalGameId
                + ", GlobalAwayTeamId=" + GlobalAwayTeamId + ", GlobalHomeTeamId=" + GlobalHomeTeamId + ", ClockExtra="
                + ClockExtra + ", ClockDisplay=" + ClockDisplay + ", IsClosed=" + IsClosed + ", HomeTeamFormation="
                + HomeTeamFormation + ", AwayTeamFormation=" + AwayTeamFormation + ", playoffAggregateScore="
                + playoffAggregateScore + ", round=" + round + "]";
    }

}
