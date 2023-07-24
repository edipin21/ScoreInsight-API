package com.example.sport_api.models.sport;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "bookingId", "gameId", "type", "teamId", "playerId", "name", "gameMinute", "gameMinuteExtra" })
public class Booking {

    @Id
    private Integer BookingId;
    private Integer GameId;
    private String Type;
    private Integer TeamId;
    private Integer PlayerId;
    private String Name;
    private Integer GameMinute;
    private Integer GameMinuteExtra;

    // @JsonIgnore
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "box_score_id", insertable = false, updatable = false)
    // private BoxScore boxScore;

    public Booking() {
    }

    public Booking(Integer bookingId, Integer gameId, String type, Integer teamId, Integer playerId, String name,
            Integer gameMinute, Integer gameMinuteExtra) {
        BookingId = bookingId;
        GameId = gameId;
        Type = type;
        TeamId = teamId;
        PlayerId = playerId;
        Name = name;
        GameMinute = gameMinute;
        GameMinuteExtra = gameMinuteExtra;
    }

    public Integer getBookingId() {
        return BookingId;
    }

    public void setBookingId(Integer bookingId) {
        BookingId = bookingId;
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
