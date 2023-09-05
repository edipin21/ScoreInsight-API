package com.example.sport_api.models.sport;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@JsonPropertyOrder({ "gameId", "roundId", "season", "seasonType", "groupName", "awayTeamId", "homeTeamId",
        "venueId", "day", "dateTime", "status", "week", "period", "clock", "winner", "venueType", "awayTeamKey",
        "awayTeamName", "awayTeamCountryCode", "awayTeamScore", "awayTeamScorePeriod1", "awayTeamScorePeriod2",
        "AwayTeamScoreExtraTime", "awayTeamScorePenalty", "homeTeamKey", "homeTeamName", "homeTeamCountryCode",
        "homeTeamScore", "homeTeamScorePeriod1", "homeTeamScorePeriod2", "homeTeamScoreExtraTime",
        "homeTeamScorePenalty", "homeTeamMoneyLine", "awayTeamMoneyLine", "drawMoneyLine", "pointSpread",
        "homeTeamPointSpreadPayout", "awayTeamPointSpreadPayout", "overUnder", "overPayout", "underPayout",
        "attendance", "updated", "updatedUtc", "globalGameId", "globalAwayTeamId", "globalHomeTeamId", "clockExtra",
        "clockDisplay", "isClosed", "homeTeamFormation", "awayTeamFormation" })
@Entity
public class Game {

    @Id
    private int GameId;
    @Column(name = "round_id")
    private Integer RoundId;
    private Integer Season;
    private Integer SeasonType;
    private String GroupName;
    private Integer AwayTeamId;
    private Integer HomeTeamId;
    private Integer VenueId;
    private Date Day;
    private Date DateTime;
    private String Status;
    private Integer Week;
    private String Period;
    private Integer Clock;
    private String Winner;
    private String VenueType;
    private String AwayTeamKey;
    private String AwayTeamName;
    private String AwayTeamCountryCode;
    private Integer AwayTeamScore;
    private Integer AwayTeamScorePeriod1;
    private Integer AwayTeamScorePeriod2;
    private Integer AwayTeamScoreExtraTime;
    private Integer AwayTeamScorePenalty;
    private String HomeTeamKey;
    private String HomeTeamName;
    private String HomeTeamCountryCode;
    private Integer HomeTeamScore;
    private Integer HomeTeamScorePeriod1;
    private Integer HomeTeamScorePeriod2;
    private Integer HomeTeamScoreExtraTime;
    private Integer HomeTeamScorePenalty;
    private Integer HomeTeamMoneyLine;
    private Integer AwayTeamMoneyLine;
    private Integer DrawMoneyLine;
    private float PointSpread;
    private Integer HomeTeamPointSpreadPayout;
    private Integer AwayTeamPointSpreadPayout;
    private float OverUnder;
    private Integer OverPayout;
    private Integer UnderPayout;
    private Integer Attendance;
    private Date Updated;
    private Date UpdatedUtc;
    private Integer GlobalGameId;
    private Integer GlobalAwayTeamId;
    private Integer GlobalHomeTeamId;
    private Integer ClockExtra;
    private String ClockDisplay;
    private boolean IsClosed;
    private String HomeTeamFormation;
    private String AwayTeamFormation;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private PlayoffAggregateScore playoffAggregateScore;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id", insertable = false, updatable = false)
    private Round round;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", insertable = false, updatable = false)
    private Competition competition;

    public Game() {
        super();
    }

    public Game(int gameId, Integer roundId, Integer season, Integer seasonType, String groupName,
            Integer awayTeamId,
            Integer homeTeamId, Integer venueId, Date day, Date dateTime, String status, Integer week, String period,
            Integer clock, String winner, String venueType, String awayTeamKey, String awayTeamName,
            String awayTeamCountryCode, Integer awayTeamScore, Integer awayTeamScorePeriod1,
            Integer awayTeamScorePeriod2, Integer awayTeamScoreExtraTime, Integer awayTeamScorePenalty,
            String homeTeamKey, String homeTeamName, String homeTeamCountryCode, Integer homeTeamScore,
            Integer homeTeamScorePeriod1, Integer homeTeamScorePeriod2, Integer homeTeamScoreExtraTime,
            Integer homeTeamScorePenalty, Integer homeTeamMoneyLine, Integer awayTeamMoneyLine, Integer drawMoneyLine,
            float pointSpread, Integer homeTeamPointSpreadPayout, Integer awayTeamPointSpreadPayout, float overUnder,
            Integer overPayout, Integer underPayout, Integer attendance, Date updated, Date updatedUtc,
            Integer globalGameId, Integer globalAwayTeamId, Integer globalHomeTeamId, Integer clockExtra,
            String clockDisplay, boolean isClosed, String homeTeamFormation, String awayTeamFormation,
            PlayoffAggregateScore playoffAggregateScore, Round round, Competition competition) {
        GameId = gameId;
        RoundId = roundId;
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
        this.competition = competition;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public Integer getRoundId() {
        return RoundId;
    }

    public void setRoundId(Integer roundId) {
        RoundId = roundId;
    }

    public Integer getSeason() {
        return Season;
    }

    public void setSeason(Integer season) {
        Season = season;
    }

    public Integer getSeasonType() {
        return SeasonType;
    }

    public void setSeasonType(Integer seasonType) {
        SeasonType = seasonType;
    }

    public Integer getAwayTeamId() {
        return AwayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        AwayTeamId = awayTeamId;
    }

    public Integer getHomeTeamId() {
        return HomeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        HomeTeamId = homeTeamId;
    }

    public Integer getVenueId() {
        return VenueId;
    }

    public void setVenueId(Integer venueId) {
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

    public Integer getWeek() {
        return Week;
    }

    public void setWeek(Integer week) {
        Week = week;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public Integer getClock() {
        return Clock;
    }

    public void setClock(Integer clock) {
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

    public Integer getAwayTeamScore() {
        return AwayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        AwayTeamScore = awayTeamScore;
    }

    public Integer getAwayTeamScorePeriod1() {
        return AwayTeamScorePeriod1;
    }

    public void setAwayTeamScorePeriod1(Integer awayTeamScorePeriod1) {
        AwayTeamScorePeriod1 = awayTeamScorePeriod1;
    }

    public Integer getAwayTeamScorePeriod2() {
        return AwayTeamScorePeriod2;
    }

    public void setAwayTeamScorePeriod2(Integer awayTeamScorePeriod2) {
        AwayTeamScorePeriod2 = awayTeamScorePeriod2;
    }

    public Integer getAwayTeamScoreExtraTime() {
        return AwayTeamScoreExtraTime;
    }

    public void setAwayTeamScoreExtraTime(Integer awayTeamScoreExtraTime) {
        AwayTeamScoreExtraTime = awayTeamScoreExtraTime;
    }

    public Integer getAwayTeamScorePenalty() {
        return AwayTeamScorePenalty;
    }

    public void setAwayTeamScorePenalty(Integer awayTeamScorePenalty) {
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

    public Integer getHomeTeamScore() {
        return HomeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        HomeTeamScore = homeTeamScore;
    }

    public Integer getHomeTeamScorePeriod1() {
        return HomeTeamScorePeriod1;
    }

    public void setHomeTeamScorePeriod1(Integer homeTeamScorePeriod1) {
        HomeTeamScorePeriod1 = homeTeamScorePeriod1;
    }

    public Integer getHomeTeamScorePeriod2() {
        return HomeTeamScorePeriod2;
    }

    public void setHomeTeamScorePeriod2(Integer homeTeamScorePeriod2) {
        HomeTeamScorePeriod2 = homeTeamScorePeriod2;
    }

    public Integer getHomeTeamScoreExtraTime() {
        return HomeTeamScoreExtraTime;
    }

    public void setHomeTeamScoreExtraTime(Integer homeTeamScoreExtraTime) {
        HomeTeamScoreExtraTime = homeTeamScoreExtraTime;
    }

    public Integer getHomeTeamScorePenalty() {
        return HomeTeamScorePenalty;
    }

    public void setHomeTeamScorePenalty(Integer homeTeamScorePenalty) {
        HomeTeamScorePenalty = homeTeamScorePenalty;
    }

    public Integer getHomeTeamMoneyLine() {
        return HomeTeamMoneyLine;
    }

    public void setHomeTeamMoneyLine(Integer homeTeamMoneyLine) {
        HomeTeamMoneyLine = homeTeamMoneyLine;
    }

    public Integer getAwayTeamMoneyLine() {
        return AwayTeamMoneyLine;
    }

    public void setAwayTeamMoneyLine(Integer awayTeamMoneyLine) {
        AwayTeamMoneyLine = awayTeamMoneyLine;
    }

    public Integer getDrawMoneyLine() {
        return DrawMoneyLine;
    }

    public void setDrawMoneyLine(Integer drawMoneyLine) {
        DrawMoneyLine = drawMoneyLine;
    }

    public float getPointSpread() {
        return PointSpread;
    }

    public void setPointSpread(float pointSpread) {
        PointSpread = pointSpread;
    }

    public Integer getHomeTeamPointSpreadPayout() {
        return HomeTeamPointSpreadPayout;
    }

    public void setHomeTeamPointSpreadPayout(Integer homeTeamPointSpreadPayout) {
        HomeTeamPointSpreadPayout = homeTeamPointSpreadPayout;
    }

    public Integer getAwayTeamPointSpreadPayout() {
        return AwayTeamPointSpreadPayout;
    }

    public void setAwayTeamPointSpreadPayout(Integer awayTeamPointSpreadPayout) {
        AwayTeamPointSpreadPayout = awayTeamPointSpreadPayout;
    }

    public float getOverUnder() {
        return OverUnder;
    }

    public void setOverUnder(float overUnder) {
        OverUnder = overUnder;
    }

    public Integer getOverPayout() {
        return OverPayout;
    }

    public void setOverPayout(Integer overPayout) {
        OverPayout = overPayout;
    }

    public Integer getUnderPayout() {
        return UnderPayout;
    }

    public void setUnderPayout(Integer underPayout) {
        UnderPayout = underPayout;
    }

    public Integer getAttendance() {
        return Attendance;
    }

    public void setAttendance(Integer attendance) {
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

    public Integer getGlobalGameId() {
        return GlobalGameId;
    }

    public void setGlobalGameId(Integer globalGameId) {
        GlobalGameId = globalGameId;
    }

    public Integer getGlobalAwayTeamId() {
        return GlobalAwayTeamId;
    }

    public void setGlobalAwayTeamId(Integer globalAwayTeamId) {
        GlobalAwayTeamId = globalAwayTeamId;
    }

    public Integer getGlobalHomeTeamId() {
        return GlobalHomeTeamId;
    }

    public void setGlobalHomeTeamId(Integer globalHomeTeamId) {
        GlobalHomeTeamId = globalHomeTeamId;
    }

    public Integer getClockExtra() {
        return ClockExtra;
    }

    public void setClockExtra(Integer clockExtra) {
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @JsonProperty("Group")
    public String getGroupName() {
        return GroupName;
    }

    @JsonProperty("Group")
    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

}
