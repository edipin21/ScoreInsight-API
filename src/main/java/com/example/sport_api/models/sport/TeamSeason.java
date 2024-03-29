package com.example.sport_api.models.sport;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TeamSeason {
    @Id
    private int StatId;
    private int SeasonType;
    private int Season;
    private int TeamId;
    private String Name;
    private String Team;
    private int GlobalTeamId;
    private float Possession;
    private Date Updated;
    private Date UpdatedUtc;
    private int Games;
    private float FantasyPoints;
    private float FantasyPointsFanDuel;
    private float FantasyPointsDraftKings;
    private float FantasyPointsYahoo;
    private float FantasyPointsMondogoal;
    private float Minutes;
    private float Goals;
    private float Assists;
    private float Shots;
    private float ShotsOnGoal;
    private float YellowCards;
    private float RedCards;
    private float YellowRedCards;
    private float Crosses;
    private float TacklesWon;
    private float Interceptions;
    private float OwnGoals;
    private float Fouls;
    private float Fouled;
    private float Offsides;
    private float Passes;
    private float PassesCompleted;
    private float LastManTackle;
    private float CornersWon;
    private float BlockedShots;
    private float Touches;
    private float DefenderCleanSheets;
    private float GoalkeeperSaves;
    private float GoalkeeperGoalsAgainst;
    private float GoalkeeperSingleGoalAgainst;
    private float GoalkeeperCleanSheets;
    private float GoalkeeperWins;
    private float PenaltyKickGoals;
    private float PenaltyKickMisses;
    private float PenaltyKickSaves;
    private float PenaltiesWon;
    private float PenaltiesConceded;
    private float Score;
    private float OpponentScore;
    private float Tackles;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id", insertable = false, updatable = false)
    private Round round;

    public TeamSeason() {
        super();
    }

    public TeamSeason(int statId, int seasonType, int season, int teamId, String name, String team, int globalTeamId,
            float possession, Date updated, Date updatedUtc, int games, float fantasyPoints, float fantasyPointsFanDuel,
            float fantasyPointsDraftKings, float fantasyPointsYahoo, float fantasyPointsMondogoal, float minutes,
            float goals, float assists, float shots, float shotsOnGoal, float yellowCards, float redCards,
            float yellowRedCards, float crosses, float tacklesWon, float interceptions, float ownGoals, float fouls,
            float fouled, float offsides, float passes, float passesCompleted, float lastManTackle, float cornersWon,
            float blockedShots, float touches, float defenderCleanSheets, float goalkeeperSaves,
            float goalkeeperGoalsAgainst, float goalkeeperSingleGoalAgainst, float goalkeeperCleanSheets,
            float goalkeeperWins, float penaltyKickGoals, float penaltyKickMisses, float penaltyKickSaves,
            float penaltiesWon, float penaltiesConceded, float score, float opponentScore, float tackles, Round round) {
        StatId = statId;
        SeasonType = seasonType;
        Season = season;
        TeamId = teamId;
        Name = name;
        Team = team;
        GlobalTeamId = globalTeamId;
        Possession = possession;
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
        this.round = round;
    }

    public int getStatId() {
        return StatId;
    }

    public void setStatId(int statId) {
        StatId = statId;
    }

    public int getSeasonType() {
        return SeasonType;
    }

    public void setSeasonType(int seasonType) {
        SeasonType = seasonType;
    }

    public int getSeason() {
        return Season;
    }

    public void setSeason(int season) {
        Season = season;
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

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public int getGlobalTeamId() {
        return GlobalTeamId;
    }

    public void setGlobalTeamId(int globalTeamId) {
        GlobalTeamId = globalTeamId;
    }

    public float getPossession() {
        return Possession;
    }

    public void setPossession(float possession) {
        Possession = possession;
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

    public int getGames() {
        return Games;
    }

    public void setGames(int games) {
        Games = games;
    }

    public float getFantasyPoints() {
        return FantasyPoints;
    }

    public void setFantasyPoints(float fantasyPoints) {
        FantasyPoints = fantasyPoints;
    }

    public float getFantasyPointsFanDuel() {
        return FantasyPointsFanDuel;
    }

    public void setFantasyPointsFanDuel(float fantasyPointsFanDuel) {
        FantasyPointsFanDuel = fantasyPointsFanDuel;
    }

    public float getFantasyPointsDraftKings() {
        return FantasyPointsDraftKings;
    }

    public void setFantasyPointsDraftKings(float fantasyPointsDraftKings) {
        FantasyPointsDraftKings = fantasyPointsDraftKings;
    }

    public float getFantasyPointsYahoo() {
        return FantasyPointsYahoo;
    }

    public void setFantasyPointsYahoo(float fantasyPointsYahoo) {
        FantasyPointsYahoo = fantasyPointsYahoo;
    }

    public float getFantasyPointsMondogoal() {
        return FantasyPointsMondogoal;
    }

    public void setFantasyPointsMondogoal(float fantasyPointsMondogoal) {
        FantasyPointsMondogoal = fantasyPointsMondogoal;
    }

    public float getMinutes() {
        return Minutes;
    }

    public void setMinutes(float minutes) {
        Minutes = minutes;
    }

    public float getGoals() {
        return Goals;
    }

    public void setGoals(float goals) {
        Goals = goals;
    }

    public float getAssists() {
        return Assists;
    }

    public void setAssists(float assists) {
        Assists = assists;
    }

    public float getShots() {
        return Shots;
    }

    public void setShots(float shots) {
        Shots = shots;
    }

    public float getShotsOnGoal() {
        return ShotsOnGoal;
    }

    public void setShotsOnGoal(float shotsOnGoal) {
        ShotsOnGoal = shotsOnGoal;
    }

    public float getYellowCards() {
        return YellowCards;
    }

    public void setYellowCards(float yellowCards) {
        YellowCards = yellowCards;
    }

    public float getRedCards() {
        return RedCards;
    }

    public void setRedCards(float redCards) {
        RedCards = redCards;
    }

    public float getYellowRedCards() {
        return YellowRedCards;
    }

    public void setYellowRedCards(float yellowRedCards) {
        YellowRedCards = yellowRedCards;
    }

    public float getCrosses() {
        return Crosses;
    }

    public void setCrosses(float crosses) {
        Crosses = crosses;
    }

    public float getTacklesWon() {
        return TacklesWon;
    }

    public void setTacklesWon(float tacklesWon) {
        TacklesWon = tacklesWon;
    }

    public float getInterceptions() {
        return Interceptions;
    }

    public void setInterceptions(float interceptions) {
        Interceptions = interceptions;
    }

    public float getOwnGoals() {
        return OwnGoals;
    }

    public void setOwnGoals(float ownGoals) {
        OwnGoals = ownGoals;
    }

    public float getFouls() {
        return Fouls;
    }

    public void setFouls(float fouls) {
        Fouls = fouls;
    }

    public float getFouled() {
        return Fouled;
    }

    public void setFouled(float fouled) {
        Fouled = fouled;
    }

    public float getOffsides() {
        return Offsides;
    }

    public void setOffsides(float offsides) {
        Offsides = offsides;
    }

    public float getPasses() {
        return Passes;
    }

    public void setPasses(float passes) {
        Passes = passes;
    }

    public float getPassesCompleted() {
        return PassesCompleted;
    }

    public void setPassesCompleted(float passesCompleted) {
        PassesCompleted = passesCompleted;
    }

    public float getLastManTackle() {
        return LastManTackle;
    }

    public void setLastManTackle(float lastManTackle) {
        LastManTackle = lastManTackle;
    }

    public float getCornersWon() {
        return CornersWon;
    }

    public void setCornersWon(float cornersWon) {
        CornersWon = cornersWon;
    }

    public float getBlockedShots() {
        return BlockedShots;
    }

    public void setBlockedShots(float blockedShots) {
        BlockedShots = blockedShots;
    }

    public float getTouches() {
        return Touches;
    }

    public void setTouches(float touches) {
        Touches = touches;
    }

    public float getDefenderCleanSheets() {
        return DefenderCleanSheets;
    }

    public void setDefenderCleanSheets(float defenderCleanSheets) {
        DefenderCleanSheets = defenderCleanSheets;
    }

    public float getGoalkeeperSaves() {
        return GoalkeeperSaves;
    }

    public void setGoalkeeperSaves(float goalkeeperSaves) {
        GoalkeeperSaves = goalkeeperSaves;
    }

    public float getGoalkeeperGoalsAgainst() {
        return GoalkeeperGoalsAgainst;
    }

    public void setGoalkeeperGoalsAgainst(float goalkeeperGoalsAgainst) {
        GoalkeeperGoalsAgainst = goalkeeperGoalsAgainst;
    }

    public float getGoalkeeperSingleGoalAgainst() {
        return GoalkeeperSingleGoalAgainst;
    }

    public void setGoalkeeperSingleGoalAgainst(float goalkeeperSingleGoalAgainst) {
        GoalkeeperSingleGoalAgainst = goalkeeperSingleGoalAgainst;
    }

    public float getGoalkeeperCleanSheets() {
        return GoalkeeperCleanSheets;
    }

    public void setGoalkeeperCleanSheets(float goalkeeperCleanSheets) {
        GoalkeeperCleanSheets = goalkeeperCleanSheets;
    }

    public float getGoalkeeperWins() {
        return GoalkeeperWins;
    }

    public void setGoalkeeperWins(float goalkeeperWins) {
        GoalkeeperWins = goalkeeperWins;
    }

    public float getPenaltyKickGoals() {
        return PenaltyKickGoals;
    }

    public void setPenaltyKickGoals(float penaltyKickGoals) {
        PenaltyKickGoals = penaltyKickGoals;
    }

    public float getPenaltyKickMisses() {
        return PenaltyKickMisses;
    }

    public void setPenaltyKickMisses(float penaltyKickMisses) {
        PenaltyKickMisses = penaltyKickMisses;
    }

    public float getPenaltyKickSaves() {
        return PenaltyKickSaves;
    }

    public void setPenaltyKickSaves(float penaltyKickSaves) {
        PenaltyKickSaves = penaltyKickSaves;
    }

    public float getPenaltiesWon() {
        return PenaltiesWon;
    }

    public void setPenaltiesWon(float penaltiesWon) {
        PenaltiesWon = penaltiesWon;
    }

    public float getPenaltiesConceded() {
        return PenaltiesConceded;
    }

    public void setPenaltiesConceded(float penaltiesConceded) {
        PenaltiesConceded = penaltiesConceded;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public float getOpponentScore() {
        return OpponentScore;
    }

    public void setOpponentScore(float opponentScore) {
        OpponentScore = opponentScore;
    }

    public float getTackles() {
        return Tackles;
    }

    public void setTackles(float tackles) {
        Tackles = tackles;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "TeamSeason [StatId=" + StatId + ", SeasonType=" + SeasonType + ", Season=" + Season + ", TeamId="
                + TeamId + ", Name=" + Name + ", Team=" + Team + ", GlobalTeamId=" + GlobalTeamId + ", Possession="
                + Possession + ", Updated=" + Updated + ", UpdatedUtc=" + UpdatedUtc + ", Games=" + Games
                + ", FantasyPoints=" + FantasyPoints + ", FantasyPointsFanDuel=" + FantasyPointsFanDuel
                + ", FantasyPointsDraftKings=" + FantasyPointsDraftKings + ", FantasyPointsYahoo=" + FantasyPointsYahoo
                + ", FantasyPointsMondogoal=" + FantasyPointsMondogoal + ", Minutes=" + Minutes + ", Goals=" + Goals
                + ", Assists=" + Assists + ", Shots=" + Shots + ", ShotsOnGoal=" + ShotsOnGoal + ", YellowCards="
                + YellowCards + ", RedCards=" + RedCards + ", YellowRedCards=" + YellowRedCards + ", Crosses=" + Crosses
                + ", TacklesWon=" + TacklesWon + ", Interceptions=" + Interceptions + ", OwnGoals=" + OwnGoals
                + ", Fouls=" + Fouls + ", Fouled=" + Fouled + ", Offsides=" + Offsides + ", Passes=" + Passes
                + ", PassesCompleted=" + PassesCompleted + ", LastManTackle=" + LastManTackle + ", CornersWon="
                + CornersWon + ", BlockedShots=" + BlockedShots + ", Touches=" + Touches + ", DefenderCleanSheets="
                + DefenderCleanSheets + ", GoalkeeperSaves=" + GoalkeeperSaves + ", GoalkeeperGoalsAgainst="
                + GoalkeeperGoalsAgainst + ", GoalkeeperSingleGoalAgainst=" + GoalkeeperSingleGoalAgainst
                + ", GoalkeeperCleanSheets=" + GoalkeeperCleanSheets + ", GoalkeeperWins=" + GoalkeeperWins
                + ", PenaltyKickGoals=" + PenaltyKickGoals + ", PenaltyKickMisses=" + PenaltyKickMisses
                + ", PenaltyKickSaves=" + PenaltyKickSaves + ", PenaltiesWon=" + PenaltiesWon + ", PenaltiesConceded="
                + PenaltiesConceded + ", Score=" + Score + ", OpponentScore=" + OpponentScore + ", Tackles=" + Tackles
                + ", round=" + round + "]";
    }

}
