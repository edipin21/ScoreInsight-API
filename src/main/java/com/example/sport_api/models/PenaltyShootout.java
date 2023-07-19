package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonPropertyOrder({ "penaltyShootoutId", "gameId", "type", "teamId", "playerId", "name", "position", "order" })
public class PenaltyShootout {

    @Id
    private Integer PenaltyShootoutId;
    private Integer GameId;
    private String Type;
    private Integer TeamId;
    private Integer PlayerId;
    private String Name;
    private String Position;
    @Column(name = "order_number")
    private Integer Order;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_score_id", insertable = false, updatable = false)
    private BoxScore boxScore;

    public PenaltyShootout() {
    }

    public PenaltyShootout(Integer penaltyShootoutId, Integer gameId, String type, Integer teamId, Integer playerId,
            String name, String position, Integer order) {
        PenaltyShootoutId = penaltyShootoutId;
        GameId = gameId;
        Type = type;
        TeamId = teamId;
        PlayerId = playerId;
        Name = name;
        Position = position;
        Order = order;
    }

    public BoxScore getBoxScore() {
        return boxScore;
    }

    public void setBoxScore(BoxScore boxScore) {
        this.boxScore = boxScore;
    }

    public Integer getPenaltyShootoutId() {
        return PenaltyShootoutId;
    }

    public void setPenaltyShootoutId(Integer penaltyShootoutId) {
        PenaltyShootoutId = penaltyShootoutId;
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

    public Integer getOrder() {
        return Order;
    }

    public void setOrder(Integer order) {
        Order = order;
    }

}
