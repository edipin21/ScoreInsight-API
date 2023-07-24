package com.example.sport_api.models.sport;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PlayoffAggregateScore {

    @Id
    private int TeamA_Id;
    private int TeamA_AggregateScore;
    private int TeamB_Id;
    private int TeamB_AggregateScore;
    private int WinningTeamId;
    private Date Created;
    private Date Updated;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public PlayoffAggregateScore(int teamA_Id, int teamA_AggregateScore, int teamB_Id, int teamB_AggregateScore,
            int winningTeamId, Date created, Date updated, Game game) {
        TeamA_Id = teamA_Id;
        TeamA_AggregateScore = teamA_AggregateScore;
        TeamB_Id = teamB_Id;
        TeamB_AggregateScore = teamB_AggregateScore;
        WinningTeamId = winningTeamId;
        Created = created;
        Updated = updated;
        this.game = game;
    }

    public PlayoffAggregateScore() {
        super();
    }

    public int getTeamA_Id() {
        return TeamA_Id;
    }

    public void setTeamA_Id(int teamA_Id) {
        TeamA_Id = teamA_Id;
    }

    public int getTeamA_AggregateScore() {
        return TeamA_AggregateScore;
    }

    public void setTeamA_AggregateScore(int teamA_AggregateScore) {
        TeamA_AggregateScore = teamA_AggregateScore;
    }

    public int getTeamB_Id() {
        return TeamB_Id;
    }

    public void setTeamB_Id(int teamB_Id) {
        TeamB_Id = teamB_Id;
    }

    public int getTeamB_AggregateScore() {
        return TeamB_AggregateScore;
    }

    public void setTeamB_AggregateScore(int teamB_AggregateScore) {
        TeamB_AggregateScore = teamB_AggregateScore;
    }

    public int getWinningTeamId() {
        return WinningTeamId;
    }

    public void setWinningTeamId(int winningTeamId) {
        WinningTeamId = winningTeamId;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public Date getUpdated() {
        return Updated;
    }

    public void setUpdated(Date updated) {
        Updated = updated;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "PlayoffAggregateScore [TeamA_Id=" + TeamA_Id + ", TeamA_AggregateScore=" + TeamA_AggregateScore
                + ", TeamB_Id=" + TeamB_Id + ", TeamB_AggregateScore=" + TeamB_AggregateScore + ", WinningTeamId="
                + WinningTeamId + ", Created=" + Created + ", Updated=" + Updated + "]";
    }
}
