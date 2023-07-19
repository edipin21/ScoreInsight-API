package com.example.sport_api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonPropertyOrder({ "game", "AwayTeamCoach", "HomeTeamCoach", "MainReferee",
        "AssistantReferee1", "AssistantReferee2",
        "FourthReferee", "AdditionalAssistantReferee1",
        "AdditionalAssistantReferee2", "Lineups", "Goals", "Bookings",
        "PenaltyShootouts", "TeamGames", "PlayerGames", "VideoAssistantReferee" })
public class BoxScore {

    @Id
    private Integer boxScoreId;

    @JsonProperty("Game")
    @OneToOne(cascade = CascadeType.ALL)
    private Game game;

    @OneToOne(cascade = CascadeType.ALL)
    private Coach AwayTeamCoach;

    @OneToOne(cascade = CascadeType.ALL)
    private Coach HomeTeamCoach;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee MainReferee;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee AssistantReferee1;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee AssistantReferee2;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee FourthReferee;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee AdditionalAssistantReferee1;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee AdditionalAssistantReferee2;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "box_score_id")
    private List<Lineup> Lineups;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "box_score_id")
    private List<Goal> Goals;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "box_score_id")
    private List<Booking> Bookings;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "box_score_id")
    private List<PenaltyShootout> PenaltyShootouts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "box_score_id")
    private List<TeamGame> TeamGames;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "box_score_id")
    private List<PlayerGame> PlayerGames;

    @OneToOne(cascade = CascadeType.ALL)
    private Referee VideoAssistantReferee;

    public BoxScore() {
    }

    public BoxScore(Game game, Coach awayTeamCoach, Coach homeTeamCoach, Referee mainReferee, Referee assistantReferee1,
            Referee assistantReferee2, Referee fourthReferee, Referee additionalAssistantReferee1,
            Referee additionalAssistantReferee2, List<Lineup> lineups, List<Goal> goals,
            List<Booking> bookings,
            List<PenaltyShootout> penaltyShootouts, List<TeamGame> teamGames,
            List<PlayerGame> playerGames,
            Referee videoAssistantReferee) {
        this.game = game;
        AwayTeamCoach = awayTeamCoach;
        HomeTeamCoach = homeTeamCoach;
        MainReferee = mainReferee;
        AssistantReferee1 = assistantReferee1;
        AssistantReferee2 = assistantReferee2;
        FourthReferee = fourthReferee;
        AdditionalAssistantReferee1 = additionalAssistantReferee1;
        AdditionalAssistantReferee2 = additionalAssistantReferee2;
        Lineups = lineups;
        Goals = goals;
        Bookings = bookings;
        PenaltyShootouts = penaltyShootouts;
        TeamGames = teamGames;
        PlayerGames = playerGames;
        VideoAssistantReferee = videoAssistantReferee;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Coach getAwayTeamCoach() {
        return AwayTeamCoach;
    }

    public void setAwayTeamCoach(Coach awayTeamCoach) {
        AwayTeamCoach = awayTeamCoach;
    }

    public Coach getHomeTeamCoach() {
        return HomeTeamCoach;
    }

    public void setHomeTeamCoach(Coach homeTeamCoach) {
        HomeTeamCoach = homeTeamCoach;
    }

    public Referee getMainReferee() {
        return MainReferee;
    }

    public void setMainReferee(Referee mainReferee) {
        MainReferee = mainReferee;
    }

    public Referee getAssistantReferee1() {
        return AssistantReferee1;
    }

    public void setAssistantReferee1(Referee assistantReferee1) {
        AssistantReferee1 = assistantReferee1;
    }

    public Referee getAssistantReferee2() {
        return AssistantReferee2;
    }

    public void setAssistantReferee2(Referee assistantReferee2) {
        AssistantReferee2 = assistantReferee2;
    }

    public Referee getFourthReferee() {
        return FourthReferee;
    }

    public void setFourthReferee(Referee fourthReferee) {
        FourthReferee = fourthReferee;
    }

    public Referee getAdditionalAssistantReferee1() {
        return AdditionalAssistantReferee1;
    }

    public void setAdditionalAssistantReferee1(Referee additionalAssistantReferee1) {
        AdditionalAssistantReferee1 = additionalAssistantReferee1;
    }

    public Referee getAdditionalAssistantReferee2() {
        return AdditionalAssistantReferee2;
    }

    public void setAdditionalAssistantReferee2(Referee additionalAssistantReferee2) {
        AdditionalAssistantReferee2 = additionalAssistantReferee2;
    }

    public List<Lineup> getLineups() {
        return Lineups;
    }

    public void setLineups(List<Lineup> lineups) {
        Lineups = lineups;
    }

    public List<Goal> getGoals() {
        return Goals;
    }

    public void setGoals(List<Goal> goals) {
        Goals = goals;
    }

    public List<Booking> getBookings() {
        return Bookings;
    }

    public void setBookings(List<Booking> bookings) {
        Bookings = bookings;
    }

    public List<PenaltyShootout> getPenaltyShootouts() {
        return PenaltyShootouts;
    }

    public void setPenaltyShootouts(List<PenaltyShootout> penaltyShootouts) {
        PenaltyShootouts = penaltyShootouts;
    }

    public List<TeamGame> getTeamGames() {
        return TeamGames;
    }

    public void setTeamGames(List<TeamGame> teamGames) {
        TeamGames = teamGames;
    }

    public List<PlayerGame> getPlayerGames() {
        return PlayerGames;
    }

    public void setPlayerGames(List<PlayerGame> playerGames) {
        PlayerGames = playerGames;
    }

    public Referee getVideoAssistantReferee() {
        return VideoAssistantReferee;
    }

    public void setVideoAssistantReferee(Referee videoAssistantReferee) {
        VideoAssistantReferee = videoAssistantReferee;
    }

    public Integer getBoxScoreId() {
        return boxScoreId;
    }

    public void setBoxScoreId(Integer boxScoreId) {
        this.boxScoreId = boxScoreId;
    }

}
