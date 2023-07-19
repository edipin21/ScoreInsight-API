package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonPropertyOrder({ "lineupId", "gameId", "type", "teamId", "playerId", "name", "position", "replacedPlayerId",
        "replacedPlayerName", "gameMinute", "gameMinuteExtra", "pitchPositionHorizontal", "pitchPositionVertical" })
public class Lineup {

    @Id
    private Integer LineupId;
    private Integer GameId;
    private String Type;
    private Integer TeamId;
    private Integer PlayerId;
    private String Name;
    private String Position;
    private Integer ReplacedPlayerId;
    private String ReplacedPlayerName;
    private Integer GameMinute;
    private Integer GameMinuteExtra;
    private Integer PitchPositionHorizontal;
    private Integer PitchPositionVertical;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_score_id", insertable = false, updatable = false)
    private BoxScore boxScore;

    public Lineup() {
    }

    public Lineup(Integer lineupId, Integer gameId, String type, Integer teamId, Integer playerId, String name,
            String position, Integer replacedPlayerId, String replacedPlayerName, Integer gameMinute,
            Integer gameMinuteExtra, Integer pitchPositionHorizontal, Integer pitchPositionVertical) {
        LineupId = lineupId;
        GameId = gameId;
        Type = type;
        TeamId = teamId;
        PlayerId = playerId;
        Name = name;
        Position = position;
        ReplacedPlayerId = replacedPlayerId;
        ReplacedPlayerName = replacedPlayerName;
        GameMinute = gameMinute;
        GameMinuteExtra = gameMinuteExtra;
        PitchPositionHorizontal = pitchPositionHorizontal;
        PitchPositionVertical = pitchPositionVertical;
    }

    public BoxScore getBoxScore() {
        return boxScore;
    }

    public void setBoxScore(BoxScore boxScore) {
        this.boxScore = boxScore;
    }

    public Integer getLineupId() {
        return LineupId;
    }

    public void setLineupId(Integer lineupId) {
        LineupId = lineupId;
    }

    public Integer getGameId() {
        return GameId;
    }

    public void setGameId(Integer gameId) {
        GameId = gameId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public Integer getReplacedPlayerId() {
        return ReplacedPlayerId;
    }

    public void setReplacedPlayerId(Integer replacedPlayerId) {
        ReplacedPlayerId = replacedPlayerId;
    }

    public String getReplacedPlayerName() {
        return ReplacedPlayerName;
    }

    public void setReplacedPlayerName(String replacedPlayerName) {
        ReplacedPlayerName = replacedPlayerName;
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

    public Integer getPitchPositionHorizontal() {
        return PitchPositionHorizontal;
    }

    public void setPitchPositionHorizontal(Integer pitchPositionHorizontal) {
        PitchPositionHorizontal = pitchPositionHorizontal;
    }

    public Integer getPitchPositionVertical() {
        return PitchPositionVertical;
    }

    public void setPitchPositionVertical(Integer pitchPositionVertical) {
        PitchPositionVertical = pitchPositionVertical;
    }

    @Override
    public String toString() {
        return "Lineup [LineupId=" + LineupId + ", GameId=" + GameId + ", Type=" + Type + ", TeamId=" + TeamId
                + ", PlayerId=" + PlayerId + ", Name=" + Name + ", Position=" + Position + ", ReplacedPlayerId="
                + ReplacedPlayerId + ", ReplacedPlayerName=" + ReplacedPlayerName + ", GameMinute=" + GameMinute
                + ", GameMinuteExtra=" + GameMinuteExtra + ", PitchPositionHorizontal=" + PitchPositionHorizontal
                + ", PitchPositionVertical=" + PitchPositionVertical + "]";
    }

}
