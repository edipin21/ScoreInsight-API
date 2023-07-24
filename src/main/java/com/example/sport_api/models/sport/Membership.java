package com.example.sport_api.models.sport;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "membershipId", "teamId", "playerId", "playerName", "teamName", "teamArea", "active", "startDate",
        "endDate", "updated", "jersey" })
public class Membership {

    @Id
    private Integer MembershipId;
    private Integer TeamId;
    private Integer PlayerId;
    private String PlayerName;
    private String TeamName;
    private String TeamArea;
    private Boolean Active;
    private Date StartDate;
    private Date endDate;
    private Date Updated;
    private Integer Jersey;
    private Integer CompetitionId;

    public Membership() {
    }

    public Membership(Integer membershipId, Integer teamId, Integer playerId, String playerName, String teamName,
            String teamArea, Boolean active, Date startDate, Date endDate, Date updated, Integer jersey,
            Integer competitionId) {
        MembershipId = membershipId;
        TeamId = teamId;
        PlayerId = playerId;
        PlayerName = playerName;
        TeamName = teamName;
        TeamArea = teamArea;
        Active = active;
        StartDate = startDate;
        this.endDate = endDate;
        Updated = updated;
        Jersey = jersey;
        CompetitionId = competitionId;
    }

    public Integer getMembershipId() {
        return MembershipId;
    }

    public void setMembershipId(Integer membershipId) {
        MembershipId = membershipId;
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

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTeamArea() {
        return TeamArea;
    }

    public void setTeamArea(String teamArea) {
        TeamArea = teamArea;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdated() {
        return Updated;
    }

    public void setUpdated(Date updated) {
        Updated = updated;
    }

    public Integer getJersey() {
        return Jersey;
    }

    public void setJersey(Integer jersey) {
        Jersey = jersey;
    }

    public Integer getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        CompetitionId = competitionId;
    }

}
