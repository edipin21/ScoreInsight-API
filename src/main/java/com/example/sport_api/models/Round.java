package com.example.sport_api.models;

import java.sql.Date;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

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

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Game[] Games;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Standing[] Standings;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TeamSeason[] TeamSeasons;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PlayerSeason[] PlayerSeasons;

    public Round() {
        super();
    }

    public Round(int roundId, int soccerSeason, int seasonType, String name, String type, Date startDate, Date endDate,
            int currentWeek, boolean currentRound, Season season, Game[] games, Standing[] standings,
            TeamSeason[] teamSeasons, PlayerSeason[] playerSeasons) {
        super();
        RoundId = roundId;
        SoccerSeason = soccerSeason;
        SeasonType = seasonType;
        Name = name;
        Type = type;
        StartDate = startDate;
        EndDate = endDate;
        CurrentWeek = currentWeek;
        CurrentRound = currentRound;
        this.season = season;
        Games = games;
        Standings = standings;
        TeamSeasons = teamSeasons;
        PlayerSeasons = playerSeasons;
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

    public int getSoccerSeason() {
        return SoccerSeason;
    }

    public void setSoccerSeason(int soccerSeason) {
        SoccerSeason = soccerSeason;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Round [RoundId=" + RoundId + ", SoccerSeason=" + SoccerSeason + ", SeasonType=" + SeasonType + ", Name="
                + Name + ", Type=" + Type + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", CurrentWeek="
                + CurrentWeek + ", CurrentRound=" + CurrentRound + ", season=" + season + ", Games="
                + Arrays.toString(Games) + ", Standings=" + Arrays.toString(Standings) + ", TeamSeasons="
                + Arrays.toString(TeamSeasons) + ", PlayerSeasons=" + Arrays.toString(PlayerSeasons) + "]";
    }

}
