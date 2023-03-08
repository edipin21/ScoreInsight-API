package com.example.sport_api.round;

import java.sql.Date;

import com.example.sport_api.game.Game;
import com.example.sport_api.playerSeason.PlayerSeason;
import com.example.sport_api.season.Season;
import com.example.sport_api.standing.Standing;
import com.example.sport_api.teamSeason.TeamSeason;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Round {
    @Id
    private int RoundId;

    @JsonProperty("Season")
    private int SoccerSeason;
    private int SeasonType;
    private String Name;
    private String Type;
    private Date StartDate;
    private Date EndDate;
    private int CurrentWeek;
    private boolean CurrentRound;

    @ManyToOne
    @JoinColumn(name = "SeasonId")
    private Season season;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private Game[] Games;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private Standing[] Standings;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private TeamSeason[] TeamSeasons;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerSeason[] PlayerSeasons;

    public Round() {
    }

    public int getRoundId() {
        return RoundId;
    }

    public void setRoundId(int roundId) {
        RoundId = roundId;
    }

    public int getSeasonType() {
        return SeasonType;
    }

    public void setSeasonType(int seasonType) {
        SeasonType = seasonType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public int getCurrentWeek() {
        return CurrentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        CurrentWeek = currentWeek;
    }

    public boolean isCurrentRound() {
        return CurrentRound;
    }

    public void setCurrentRound(boolean currentRound) {
        CurrentRound = currentRound;
    }

    public Game[] getGames() {
        return Games;
    }

    public void setGames(Game[] games) {
        Games = games;
    }

    public Standing[] getStandings() {
        return Standings;
    }

    public void setStandings(Standing[] standings) {
        Standings = standings;
    }

    public TeamSeason[] getTeamSeasons() {
        return TeamSeasons;
    }

    public void setTeamSeasons(TeamSeason[] teamSeasons) {
        TeamSeasons = teamSeasons;
    }

    public PlayerSeason[] getPlayerSeasons() {
        return PlayerSeasons;
    }

    public void setPlayerSeasons(PlayerSeason[] playerSeasons) {
        PlayerSeasons = playerSeasons;
    }

}
