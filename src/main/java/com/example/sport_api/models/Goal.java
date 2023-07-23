package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "goalId", "gameId", "teamId", "playerId", "name", "type", "assistedByPlayerId1",
        "assistedByPlayerName1", "assistedByPlayerId2", "assistedByPlayerName2", "gameMinute", "gameMinuteExtra" })
public class Goal {

    @Id
    private Integer GoalId;
    private Integer GameId;
    private Integer TeamId;
    private Integer PlayerId;
    private String Name;
    private String Type;
    private Integer AssistedByPlayerId1;
    private String AssistedByPlayerName1;
    private Integer AssistedByPlayerId2;
    private String AssistedByPlayerName2;
    private Integer GameMinute;
    private Integer GameMinuteExtra;

    // @JsonIgnore
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "box_score_id", insertable = false, updatable = false)
    // private BoxScore boxScore;

    public Goal() {
    }

    public Goal(Integer goalId, Integer gameId, Integer teamId, Integer playerId, String name, String type,
            Integer assistedByPlayerId1, String assistedByPlayerName1, Integer assistedByPlayerId2,
            String assistedByPlayerName2, Integer gameMinute, Integer gameMinuteExtra) {
        GoalId = goalId;
        GameId = gameId;
        TeamId = teamId;
        PlayerId = playerId;
        Name = name;
        Type = type;
        AssistedByPlayerId1 = assistedByPlayerId1;
        AssistedByPlayerName1 = assistedByPlayerName1;
        AssistedByPlayerId2 = assistedByPlayerId2;
        AssistedByPlayerName2 = assistedByPlayerName2;
        GameMinute = gameMinute;
        GameMinuteExtra = gameMinuteExtra;
    }

    public Integer getGoalId() {
        return GoalId;
    }

    public void setGoalId(Integer goalId) {
        GoalId = goalId;
    }

    public Integer getGameId() {
        return GameId;
    }

    public void setGameId(Integer gameId) {
        GameId = gameId;
    }

    public Integer getTeamId() {
        return TeamId;
    }

    public void setTeamId(Integer teamId) {
        TeamId = teamId;
    }

    public Integer getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(Integer playerId) {
        PlayerId = playerId;
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

    public Integer getAssistedByPlayerId1() {
        return AssistedByPlayerId1;
    }

    public void setAssistedByPlayerId1(Integer assistedByPlayerId1) {
        AssistedByPlayerId1 = assistedByPlayerId1;
    }

    public String getAssistedByPlayerName1() {
        return AssistedByPlayerName1;
    }

    public void setAssistedByPlayerName1(String assistedByPlayerName1) {
        AssistedByPlayerName1 = assistedByPlayerName1;
    }

    public Integer getAssistedByPlayerId2() {
        return AssistedByPlayerId2;
    }

    public void setAssistedByPlayerId2(Integer assistedByPlayerId2) {
        AssistedByPlayerId2 = assistedByPlayerId2;
    }

    public String getAssistedByPlayerName2() {
        return AssistedByPlayerName2;
    }

    public void setAssistedByPlayerName2(String assistedByPlayerName2) {
        AssistedByPlayerName2 = assistedByPlayerName2;
    }

    public Integer getGameMinute() {
        return GameMinute;
    }

    public void setGameMinute(Integer gameMinute) {
        GameMinute = gameMinute;
    }

    public Integer getGameMinuteExtra() {
        return GameMinuteExtra;
    }

    public void setGameMinuteExtra(Integer gameMinuteExtra) {
        GameMinuteExtra = gameMinuteExtra;
    }

    // public BoxScore getBoxScore() {
    // return boxScore;
    // }

    // public void setBoxScore(BoxScore boxScore) {
    // this.boxScore = boxScore;
    // }

}