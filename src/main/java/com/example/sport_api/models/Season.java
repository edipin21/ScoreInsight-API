package com.example.sport_api.models;

import java.sql.Date;
// import java.util.Arrays;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({ "seasonId", "competitionId", "season", "name", "competitionName", "startDate", "endDate",
        "currentSeason",
        "rounds" })
public class Season {

    @Id
    private int SeasonId;
    @Column(name = "competition_id")
    private int CompetitionId;
    private int Season;
    private String Name;
    private String CompetitionName;
    private Date StartDate;
    private Date EndDate;
    private boolean CurrentSeason;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id")
    private List<Round> Rounds;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", insertable = false, updatable = false)
    private Competition competition;

    public Season() {
        super();
    }

    public Season(int seasonId, int competitionId, int season, String name, String competitionName, Date startDate,
            Date endDate, boolean currentSeason, List<Round> rounds, Competition competition) {
        SeasonId = seasonId;
        CompetitionId = competitionId;
        Season = season;
        Name = name;
        CompetitionName = competitionName;
        StartDate = startDate;
        EndDate = endDate;
        CurrentSeason = currentSeason;
        Rounds = rounds;
        this.competition = competition;
    }

    public int getSeasonId() {
        return SeasonId;
    }

    public void setSeasonId(int seasonId) {
        SeasonId = seasonId;
    }

    public int getSeason() {
        return Season;
    }

    public void setSeason(int season) {
        Season = season;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompetitionName() {
        return CompetitionName;
    }

    public void setCompetitionName(String competitionName) {
        CompetitionName = competitionName;
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

    public boolean isCurrentSeason() {
        return CurrentSeason;
    }

    public void setCurrentSeason(boolean currentSeason) {
        CurrentSeason = currentSeason;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Round> getRounds() {
        return Rounds;
    }

    public void setRounds(List<Round> rounds) {
        Rounds = rounds;
    }

    public int getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(int competitionId) {
        CompetitionId = competitionId;
    }

}
