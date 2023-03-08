package com.example.sport_api.season;

import java.sql.Date;
import com.example.sport_api.competition.Competition;
import com.example.sport_api.round.Round;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Season {

    @Id
    private int SeasonId;
    // private int CompetitionId;
    private int Season;
    private String Name;
    private String CompetitionName;
    private Date StartDate;
    private Date EndDate;
    private boolean CurrentSeason;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private Round[] Rounds;

    @ManyToOne
    @JoinColumn(name = "CompetitionId")
    private Competition competition;

    public Season() {
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

    public Round[] getRounds() {
        return Rounds;
    }

    public void setRounds(Round[] rounds) {
        Rounds = rounds;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

}
