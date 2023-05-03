package com.example.sport_api.models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity
@JsonPropertyOrder({ "roundId", "soccerSeason", "seasonType", "name", "type",
        "startDate", "endDate", "currentWeek",
        "currentRound", "games", "standings", "teamSeasons", "playerSeasons" })
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
    @JoinColumn(name = "seasonId")
    @JsonBackReference(("season-rounds"))
    private Season season;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("round-game")
    private List<Game> Games;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("round-standings")
    private List<Standing> Standings;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("round-TeamSeasons")
    private List<TeamSeason> TeamSeasons;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("round-PlayerSeasons")
    private List<PlayerSeason> PlayerSeasons;

    public Round() {
        super();
    }

    // public Round(int roundId, int soccerSeason, int seasonType, String name,
    // String type, Date startDate, Date endDate,
    // int currentWeek, boolean currentRound, Season season, Game[] games,
    // Standing[] standings,
    // TeamSeason[] teamSeasons, PlayerSeason[] playerSeasons) {
    // super();
    // RoundId = roundId;
    // SoccerSeason = soccerSeason;
    // SeasonType = seasonType;
    // Name = name;
    // Type = type;
    // StartDate = startDate;
    // EndDate = endDate;
    // CurrentWeek = currentWeek;
    // CurrentRound = currentRound;
    // this.season = season;
    // Games = games;
    // Standings = standings;
    // TeamSeasons = teamSeasons;
    // PlayerSeasons = playerSeasons;
    // }

    public Round(int roundId, int soccerSeason, int seasonType, String name, String type, Date startDate, Date endDate,
            int currentWeek, boolean currentRound, Season season, List<Game> games, List<Standing> standings,
            List<TeamSeason> teamSeasons, List<PlayerSeason> playerSeasons) {
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

    // public Game[] getGames() {
    // return Games;
    // }

    // public void setGames(Game[] games) {
    // Games = games;
    // }

    // public Standing[] getStandings() {
    // return Standings;
    // }

    // public void setStandings(Standing[] standings) {
    // Standings = standings;
    // }

    // public TeamSeason[] getTeamSeasons() {
    // return TeamSeasons;
    // }

    // public void setTeamSeasons(TeamSeason[] teamSeasons) {
    // TeamSeasons = teamSeasons;
    // }

    // public PlayerSeason[] getPlayerSeasons() {
    // return PlayerSeasons;
    // }

    // public void setPlayerSeasons(PlayerSeason[] playerSeasons) {
    // PlayerSeasons = playerSeasons;
    // }

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

    public List<Game> getGames() {
        return Games;
    }

    public void setGames(List<Game> games) {
        Games = games;
    }

    public List<Standing> getStandings() {
        return Standings;
    }

    public void setStandings(List<Standing> standings) {
        Standings = standings;
    }

    public List<TeamSeason> getTeamSeasons() {
        return TeamSeasons;
    }

    public void setTeamSeasons(List<TeamSeason> teamSeasons) {
        TeamSeasons = teamSeasons;
    }

    public List<PlayerSeason> getPlayerSeasons() {
        return PlayerSeasons;
    }

    public void setPlayerSeasons(List<PlayerSeason> playerSeasons) {
        PlayerSeasons = playerSeasons;
    }

}
