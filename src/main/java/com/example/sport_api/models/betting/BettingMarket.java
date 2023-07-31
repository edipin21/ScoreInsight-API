package com.example.sport_api.models.betting;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({ "bettingMarketID", "bettingEventID", "bettingMarketTypeID", "bettingMarketType",
        "bettingBetTypeID", "bettingBetType", "bettingPeriodTypeID", "bettingPeriodType", "name", "teamID", "teamKey",
        "playerID", "playerName", "created", "updated", "anyBetsAvailable", "availableSportsbooks", "bettingOutcomes",
        "consensusOutcomes" })
public class BettingMarket {

    @Id
    private int BettingMarketID;
    private int BettingEventID;
    private int BettingMarketTypeID;
    private String BettingMarketType;
    private int BettingBetTypeID;
    private String BettingBetType;
    private int BettingPeriodTypeID;
    private String BettingPeriodType;
    private String Name;
    private int TeamID;
    private String TeamKey;
    private int PlayerID;
    private String PlayerName;
    private Date Created;
    private Date Updated;
    private boolean AnyBetsAvailable;
    private Integer CompetitionId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BettingMarketID")
    private List<SportsBook> AvailableSportsbooks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BettingMarketID")
    private List<BettingOutcome> BettingOutcomes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BettingMarketID")
    private List<ConsensusOutcome> ConsensusOutcomes;

    public BettingMarket() {
    }

    public BettingMarket(int bettingMarketID, int bettingEventID, int bettingMarketTypeID, String bettingMarketType,
            int bettingBetTypeID, String bettingBetType, int bettingPeriodTypeID, String bettingPeriodType, String name,
            int teamID, String teamKey, int playerID, String playerName, Date created, Date updated,
            boolean anyBetsAvailable, Integer competitionId, List<SportsBook> availableSportsbooks,
            List<BettingOutcome> bettingOutcomes, List<ConsensusOutcome> consensusOutcomes) {
        BettingMarketID = bettingMarketID;
        BettingEventID = bettingEventID;
        BettingMarketTypeID = bettingMarketTypeID;
        BettingMarketType = bettingMarketType;
        BettingBetTypeID = bettingBetTypeID;
        BettingBetType = bettingBetType;
        BettingPeriodTypeID = bettingPeriodTypeID;
        BettingPeriodType = bettingPeriodType;
        Name = name;
        TeamID = teamID;
        TeamKey = teamKey;
        PlayerID = playerID;
        PlayerName = playerName;
        Created = created;
        Updated = updated;
        AnyBetsAvailable = anyBetsAvailable;
        CompetitionId = competitionId;
        AvailableSportsbooks = availableSportsbooks;
        BettingOutcomes = bettingOutcomes;
        ConsensusOutcomes = consensusOutcomes;
    }

    public int getBettingMarketID() {
        return BettingMarketID;
    }

    public void setBettingMarketID(int bettingMarketID) {
        BettingMarketID = bettingMarketID;
    }

    public int getBettingEventID() {
        return BettingEventID;
    }

    public void setBettingEventID(int bettingEventID) {
        BettingEventID = bettingEventID;
    }

    public int getBettingMarketTypeID() {
        return BettingMarketTypeID;
    }

    public void setBettingMarketTypeID(int bettingMarketTypeID) {
        BettingMarketTypeID = bettingMarketTypeID;
    }

    public String getBettingMarketType() {
        return BettingMarketType;
    }

    public void setBettingMarketType(String bettingMarketType) {
        BettingMarketType = bettingMarketType;
    }

    public int getBettingBetTypeID() {
        return BettingBetTypeID;
    }

    public void setBettingBetTypeID(int bettingBetTypeID) {
        BettingBetTypeID = bettingBetTypeID;
    }

    public String getBettingBetType() {
        return BettingBetType;
    }

    public void setBettingBetType(String bettingBetType) {
        BettingBetType = bettingBetType;
    }

    public int getBettingPeriodTypeID() {
        return BettingPeriodTypeID;
    }

    public void setBettingPeriodTypeID(int bettingPeriodTypeID) {
        BettingPeriodTypeID = bettingPeriodTypeID;
    }

    public String getBettingPeriodType() {
        return BettingPeriodType;
    }

    public void setBettingPeriodType(String bettingPeriodType) {
        BettingPeriodType = bettingPeriodType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public String getTeamKey() {
        return TeamKey;
    }

    public void setTeamKey(String teamKey) {
        TeamKey = teamKey;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
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

    public boolean isAnyBetsAvailable() {
        return AnyBetsAvailable;
    }

    public void setAnyBetsAvailable(boolean anyBetsAvailable) {
        AnyBetsAvailable = anyBetsAvailable;
    }

    public List<SportsBook> getAvailableSportsbooks() {
        return AvailableSportsbooks;
    }

    public void setAvailableSportsbooks(List<SportsBook> availableSportsbooks) {
        AvailableSportsbooks = availableSportsbooks;
    }

    public List<BettingOutcome> getBettingOutcomes() {
        return BettingOutcomes;
    }

    public void setBettingOutcomes(List<BettingOutcome> bettingOutcomes) {
        BettingOutcomes = bettingOutcomes;
    }

    public List<ConsensusOutcome> getConsensusOutcomes() {
        return ConsensusOutcomes;
    }

    public void setConsensusOutcomes(List<ConsensusOutcome> consensusOutcomes) {
        ConsensusOutcomes = consensusOutcomes;
    }

    @JsonIgnore
    public Integer getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        CompetitionId = competitionId;
    }

}
