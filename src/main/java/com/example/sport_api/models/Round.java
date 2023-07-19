package com.example.sport_api.models;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity
@JsonPropertyOrder({ "roundId", "seasonId", "soccerSeason", "seasonType", "name", "type",
        "startDate", "endDate", "currentWeek",
        "currentRound", "games", "standings", "teamSeasons", "playerSeasons" })
public class Round {
    @Id
    private int RoundId;

    @Column(name = "season_id")
    private int SeasonId;

    // @JsonProperty("Season")
    private Integer SoccerSeason;
    private Integer SeasonType;
    private String Name;
    private String Type;
    private Date StartDate;
    private Date EndDate;
    private Integer CurrentWeek;
    private boolean CurrentRound;
    private Integer CompetitionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id", insertable = false, updatable = false)
    private Season season;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "round_id")
    private List<Game> Games;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "round_id")
    private List<Standing> Standings;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "round_id")
    private List<TeamSeason> TeamSeasons;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PlayerSeason> PlayerSeasons;

    public Round() {
        super();
    }

    public Round(int roundId, int seasonId, int soccerSeason, int seasonType, String name, String type, Date startDate,
            Date endDate, int currentWeek, boolean currentRound, Season season, List<Game> games,
            List<Standing> standings, List<TeamSeason> teamSeasons, List<PlayerSeason> playerSeasons) {
        RoundId = roundId;
        SeasonId = seasonId;
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

    public Integer getRoundId() {
        return RoundId;
    }

    public void setRoundId(Integer roundId) {
        RoundId = roundId;
    }

    public Integer getSeasonType() {
        return SeasonType;
    }

    public void setSeasonType(Integer seasonType) {
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

    public Integer getCurrentWeek() {
        return CurrentWeek;
    }

    public void setCurrentWeek(Integer currentWeek) {
        CurrentWeek = currentWeek;
    }

    public boolean isCurrentRound() {
        return CurrentRound;
    }

    public void setCurrentRound(boolean currentRound) {
        CurrentRound = currentRound;
    }

    @JsonProperty("Season")
    public int getSoccerSeason() {
        return SoccerSeason;
    }

    @JsonProperty("Season")
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

    public Integer getSeasonId() {
        return SeasonId;
    }

    public void setSeasonId(int seasonId) {
        SeasonId = seasonId;
    }

    public Integer getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        CompetitionId = competitionId;
    }

}
