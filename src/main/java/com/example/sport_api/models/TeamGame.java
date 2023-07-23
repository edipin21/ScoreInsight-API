package com.example.sport_api.models;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "statId", "seasonType", "season", "roundId", "teamId", "name", "team", "globalTeamId",
        "possession", "gameId", "opponentId", "opponent", "day", "dateTime", "homeOrAway", "isGameOver", "globalGameId",
        "globalOpponentId", "updated", "updatedUtc", "games", "fantasyPoints", "fantasyPointsFanDuel",
        "fantasyPointsDraftKings", "fantasyPointsYahoo", "fantasyPointsMondogoal", "minutes", "goals", "assists",
        "shots", "shotsOnGoal", "yellowCards", "redCards", "yellowRedCards", "crosses", "tacklesWon", "interceptions",
        "ownGoals", "fouls", "fouled", "offsides", "passes", "passesCompleted", "lastManTackle", "cornersWon",
        "blockedShots", "touches", "defenderCleanSheets", "goalkeeperSaves", "goalkeeperGoalsAgainst",
        "goalkeeperSingleGoalAgainst", "goalkeeperCleanSheets", "goalkeeperWins", "penaltyKickGoals",
        "penaltyKickMisses", "penaltyKickSaves", "penaltiesWon", "penaltiesConceded", "score", "opponentScore",
        "tackles" })
public class TeamGame {

    @Id
    private Integer StatId;
    private Integer SeasonType;
    private Integer Season;
    private Integer RoundId;
    private Integer TeamId;
    private String Name;
    private String Team;
    private Integer GlobalTeamId;
    private int Possession;
    private Integer GameId;
    private Integer OpponentId;
    private String Opponent;
    private Date Day;
    private Date DateTime;
    private String HomeOrAway;
    private Boolean IsGameOver;
    private Integer GlobalGameId;
    private Integer GlobalOpponentId;
    private Date Updated;
    private Date UpdatedUtc;
    private Integer Games;
    private int FantasyPoints;
    private int FantasyPointsFanDuel;
    private int FantasyPointsDraftKings;
    private int FantasyPointsYahoo;
    private int FantasyPointsMondogoal;
    private int Minutes;
    private int Goals;
    private int Assists;
    private int Shots;
    private int ShotsOnGoal;
    private int YellowCards;
    private int RedCards;
    private int YellowRedCards;
    private int Crosses;
    private int TacklesWon;
    private int Interceptions;
    private int OwnGoals;
    private int Fouls;
    private int Fouled;
    private int Offsides;
    private int Passes;
    private int PassesCompleted;
    private int LastManTackle;
    private int CornersWon;
    private int BlockedShots;
    private int Touches;
    private int DefenderCleanSheets;
    private int GoalkeeperSaves;
    private int GoalkeeperGoalsAgainst;
    private int GoalkeeperSingleGoalAgainst;
    private int GoalkeeperCleanSheets;
    private int GoalkeeperWins;
    private int PenaltyKickGoals;
    private int PenaltyKickMisses;
    private int PenaltyKickSaves;
    private int PenaltiesWon;
    private int PenaltiesConceded;
    private int Score;
    private int OpponentScore;
    private int Tackles;

    public TeamGame() {
    }

    public TeamGame(Integer statId, Integer seasonType, Integer season, Integer roundId, Integer teamId, String name,
            String team, Integer globalTeamId, int possession, Integer gameId, Integer opponentId, String opponent,
            Date day, Date dateTime, String homeOrAway, Boolean isGameOver, Integer globalGameId,
            Integer globalOpponentId, Date updated, Date updatedUtc, Integer games, int fantasyPoints,
            int fantasyPointsFanDuel, int fantasyPointsDraftKings, int fantasyPointsYahoo, int fantasyPointsMondogoal,
            int minutes, int goals, int assists, int shots, int shotsOnGoal, int yellowCards, int redCards,
            int yellowRedCards, int crosses, int tacklesWon, int interceptions, int ownGoals, int fouls, int fouled,
            int offsides, int passes, int passesCompleted, int lastManTackle, int cornersWon, int blockedShots,
            int touches, int defenderCleanSheets, int goalkeeperSaves, int goalkeeperGoalsAgainst,
            int goalkeeperSingleGoalAgainst, int goalkeeperCleanSheets, int goalkeeperWins, int penaltyKickGoals,
            int penaltyKickMisses, int penaltyKickSaves, int penaltiesWon, int penaltiesConceded, int score,
            int opponentScore, int tackles) {
        StatId = statId;
        SeasonType = seasonType;
        Season = season;
        RoundId = roundId;
        TeamId = teamId;
        Name = name;
        Team = team;
        GlobalTeamId = globalTeamId;
        Possession = possession;
        GameId = gameId;
        OpponentId = opponentId;
        Opponent = opponent;
        Day = day;
        DateTime = dateTime;
        HomeOrAway = homeOrAway;
        IsGameOver = isGameOver;
        GlobalGameId = globalGameId;
        GlobalOpponentId = globalOpponentId;
        Updated = updated;
        UpdatedUtc = updatedUtc;
        Games = games;
        FantasyPoints = fantasyPoints;
        FantasyPointsFanDuel = fantasyPointsFanDuel;
        FantasyPointsDraftKings = fantasyPointsDraftKings;
        FantasyPointsYahoo = fantasyPointsYahoo;
        FantasyPointsMondogoal = fantasyPointsMondogoal;
        Minutes = minutes;
        Goals = goals;
        Assists = assists;
        Shots = shots;
        ShotsOnGoal = shotsOnGoal;
        YellowCards = yellowCards;
        RedCards = redCards;
        YellowRedCards = yellowRedCards;
        Crosses = crosses;
        TacklesWon = tacklesWon;
        Interceptions = interceptions;
        OwnGoals = ownGoals;
        Fouls = fouls;
        Fouled = fouled;
        Offsides = offsides;
        Passes = passes;
        PassesCompleted = passesCompleted;
        LastManTackle = lastManTackle;
        CornersWon = cornersWon;
        BlockedShots = blockedShots;
        Touches = touches;
        DefenderCleanSheets = defenderCleanSheets;
        GoalkeeperSaves = goalkeeperSaves;
        GoalkeeperGoalsAgainst = goalkeeperGoalsAgainst;
        GoalkeeperSingleGoalAgainst = goalkeeperSingleGoalAgainst;
        GoalkeeperCleanSheets = goalkeeperCleanSheets;
        GoalkeeperWins = goalkeeperWins;
        PenaltyKickGoals = penaltyKickGoals;
        PenaltyKickMisses = penaltyKickMisses;
        PenaltyKickSaves = penaltyKickSaves;
        PenaltiesWon = penaltiesWon;
        PenaltiesConceded = penaltiesConceded;
        Score = score;
        OpponentScore = opponentScore;
        Tackles = tackles;
    }

    public Integer getStatId() {
        return StatId;
    }

    public void setStatId(Integer statId) {
        StatId = statId;
    }

    public Integer getSeasonType() {
        return SeasonType;
    }

    public void setSeasonType(Integer seasonType) {
        SeasonType = seasonType;
    }

    public Integer getSeason() {
        return Season;
    }

    public void setSeason(Integer season) {
        Season = season;
    }

    public Integer getRoundId() {
        return RoundId;
    }

    public void setRoundId(Integer roundId) {
        RoundId = roundId;
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

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public Integer getGlobalTeamId() {
        return GlobalTeamId;
    }

    public void setGlobalTeamId(Integer globalTeamId) {
        GlobalTeamId = globalTeamId;
    }

    public int getPossession() {
        return Possession;
    }

    public void setPossession(int possession) {
        Possession = possession;
    }

    public Integer getGameId() {
        return GameId;
    }

    public void setGameId(Integer gameId) {
        GameId = gameId;
    }

    public Integer getOpponentId() {
        return OpponentId;
    }

    public void setOpponentId(Integer opponentId) {
        OpponentId = opponentId;
    }

    public String getOpponent() {
        return Opponent;
    }

    public void setOpponent(String opponent) {
        Opponent = opponent;
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

    public String getHomeOrAway() {
        return HomeOrAway;
    }

    public void setHomeOrAway(String homeOrAway) {
        HomeOrAway = homeOrAway;
    }

    public Boolean getIsGameOver() {
        return IsGameOver;
    }

    public void setIsGameOver(Boolean isGameOver) {
        IsGameOver = isGameOver;
    }

    public Integer getGlobalGameId() {
        return GlobalGameId;
    }

    public void setGlobalGameId(Integer globalGameId) {
        GlobalGameId = globalGameId;
    }

    public Integer getGlobalOpponentId() {
        return GlobalOpponentId;
    }

    public void setGlobalOpponentId(Integer globalOpponentId) {
        GlobalOpponentId = globalOpponentId;
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

    public Integer getGames() {
        return Games;
    }

    public void setGames(Integer games) {
        Games = games;
    }

    public int getFantasyPoints() {
        return FantasyPoints;
    }

    public void setFantasyPoints(int fantasyPoints) {
        FantasyPoints = fantasyPoints;
    }

    public int getFantasyPointsFanDuel() {
        return FantasyPointsFanDuel;
    }

    public void setFantasyPointsFanDuel(int fantasyPointsFanDuel) {
        FantasyPointsFanDuel = fantasyPointsFanDuel;
    }

    public int getFantasyPointsDraftKings() {
        return FantasyPointsDraftKings;
    }

    public void setFantasyPointsDraftKings(int fantasyPointsDraftKings) {
        FantasyPointsDraftKings = fantasyPointsDraftKings;
    }

    public int getFantasyPointsYahoo() {
        return FantasyPointsYahoo;
    }

    public void setFantasyPointsYahoo(int fantasyPointsYahoo) {
        FantasyPointsYahoo = fantasyPointsYahoo;
    }

    public int getFantasyPointsMondogoal() {
        return FantasyPointsMondogoal;
    }

    public void setFantasyPointsMondogoal(int fantasyPointsMondogoal) {
        FantasyPointsMondogoal = fantasyPointsMondogoal;
    }

    public int getMinutes() {
        return Minutes;
    }

    public void setMinutes(int minutes) {
        Minutes = minutes;
    }

    public int getGoals() {
        return Goals;
    }

    public void setGoals(int goals) {
        Goals = goals;
    }

    public int getAssists() {
        return Assists;
    }

    public void setAssists(int assists) {
        Assists = assists;
    }

    public int getShots() {
        return Shots;
    }

    public void setShots(int shots) {
        Shots = shots;
    }

    public int getShotsOnGoal() {
        return ShotsOnGoal;
    }

    public void setShotsOnGoal(int shotsOnGoal) {
        ShotsOnGoal = shotsOnGoal;
    }

    public int getYellowCards() {
        return YellowCards;
    }

    public void setYellowCards(int yellowCards) {
        YellowCards = yellowCards;
    }

    public int getRedCards() {
        return RedCards;
    }

    public void setRedCards(int redCards) {
        RedCards = redCards;
    }

    public int getYellowRedCards() {
        return YellowRedCards;
    }

    public void setYellowRedCards(int yellowRedCards) {
        YellowRedCards = yellowRedCards;
    }

    public int getCrosses() {
        return Crosses;
    }

    public void setCrosses(int crosses) {
        Crosses = crosses;
    }

    public int getTacklesWon() {
        return TacklesWon;
    }

    public void setTacklesWon(int tacklesWon) {
        TacklesWon = tacklesWon;
    }

    public int getInterceptions() {
        return Interceptions;
    }

    public void setInterceptions(int interceptions) {
        Interceptions = interceptions;
    }

    public int getOwnGoals() {
        return OwnGoals;
    }

    public void setOwnGoals(int ownGoals) {
        OwnGoals = ownGoals;
    }

    public int getFouls() {
        return Fouls;
    }

    public void setFouls(int fouls) {
        Fouls = fouls;
    }

    public int getFouled() {
        return Fouled;
    }

    public void setFouled(int fouled) {
        Fouled = fouled;
    }

    public int getOffsides() {
        return Offsides;
    }

    public void setOffsides(int offsides) {
        Offsides = offsides;
    }

    public int getPasses() {
        return Passes;
    }

    public void setPasses(int passes) {
        Passes = passes;
    }

    public int getPassesCompleted() {
        return PassesCompleted;
    }

    public void setPassesCompleted(int passesCompleted) {
        PassesCompleted = passesCompleted;
    }

    public int getLastManTackle() {
        return LastManTackle;
    }

    public void setLastManTackle(int lastManTackle) {
        LastManTackle = lastManTackle;
    }

    public int getCornersWon() {
        return CornersWon;
    }

    public void setCornersWon(int cornersWon) {
        CornersWon = cornersWon;
    }

    public int getBlockedShots() {
        return BlockedShots;
    }

    public void setBlockedShots(int blockedShots) {
        BlockedShots = blockedShots;
    }

    public int getTouches() {
        return Touches;
    }

    public void setTouches(int touches) {
        Touches = touches;
    }

    public int getDefenderCleanSheets() {
        return DefenderCleanSheets;
    }

    public void setDefenderCleanSheets(int defenderCleanSheets) {
        DefenderCleanSheets = defenderCleanSheets;
    }

    public int getGoalkeeperSaves() {
        return GoalkeeperSaves;
    }

    public void setGoalkeeperSaves(int goalkeeperSaves) {
        GoalkeeperSaves = goalkeeperSaves;
    }

    public int getGoalkeeperGoalsAgainst() {
        return GoalkeeperGoalsAgainst;
    }

    public void setGoalkeeperGoalsAgainst(int goalkeeperGoalsAgainst) {
        GoalkeeperGoalsAgainst = goalkeeperGoalsAgainst;
    }

    public int getGoalkeeperSingleGoalAgainst() {
        return GoalkeeperSingleGoalAgainst;
    }

    public void setGoalkeeperSingleGoalAgainst(int goalkeeperSingleGoalAgainst) {
        GoalkeeperSingleGoalAgainst = goalkeeperSingleGoalAgainst;
    }

    public int getGoalkeeperCleanSheets() {
        return GoalkeeperCleanSheets;
    }

    public void setGoalkeeperCleanSheets(int goalkeeperCleanSheets) {
        GoalkeeperCleanSheets = goalkeeperCleanSheets;
    }

    public int getGoalkeeperWins() {
        return GoalkeeperWins;
    }

    public void setGoalkeeperWins(int goalkeeperWins) {
        GoalkeeperWins = goalkeeperWins;
    }

    public int getPenaltyKickGoals() {
        return PenaltyKickGoals;
    }

    public void setPenaltyKickGoals(int penaltyKickGoals) {
        PenaltyKickGoals = penaltyKickGoals;
    }

    public int getPenaltyKickMisses() {
        return PenaltyKickMisses;
    }

    public void setPenaltyKickMisses(int penaltyKickMisses) {
        PenaltyKickMisses = penaltyKickMisses;
    }

    public int getPenaltyKickSaves() {
        return PenaltyKickSaves;
    }

    public void setPenaltyKickSaves(int penaltyKickSaves) {
        PenaltyKickSaves = penaltyKickSaves;
    }

    public int getPenaltiesWon() {
        return PenaltiesWon;
    }

    public void setPenaltiesWon(int penaltiesWon) {
        PenaltiesWon = penaltiesWon;
    }

    public int getPenaltiesConceded() {
        return PenaltiesConceded;
    }

    public void setPenaltiesConceded(int penaltiesConceded) {
        PenaltiesConceded = penaltiesConceded;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getOpponentScore() {
        return OpponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        OpponentScore = opponentScore;
    }

    public int getTackles() {
        return Tackles;
    }

    public void setTackles(int tackles) {
        Tackles = tackles;
    }
}
