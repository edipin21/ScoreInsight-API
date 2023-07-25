package com.example.sport_api.models.betting;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BettingOutcome {

    @Id
    private int BettingOutcomeID;
    private int BettingMarketID;
    private int BettingOutcomeTypeID;
    private String BettingOutcomeType;
    private int PayoutAmerican;
    private BigDecimal PayoutDecimal;
    private BigDecimal Value;
    private String Participant;
    private boolean IsAvailable;
    private boolean IsAlternate;
    private Date Created;
    private Date Updated;
    private Date Unlisted;
    private int TeamID;
    private int PlayerID;
    private int GlobalTeamID;
    private String SportsbookUrl;
    private boolean IsInPlay;

    @OneToOne
    private SportsBook sportsBook;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BettingMarketId", insertable = false, updatable = false)
    private BettingMarket bettingMarket;

    public BettingOutcome() {
    }

    public BettingOutcome(int bettingOutcomeID, int bettingMarketID, int bettingOutcomeTypeID,
            String bettingOutcomeType, int payoutAmerican, BigDecimal payoutDecimal, BigDecimal value,
            String participant, boolean isAvailable, boolean isAlternate, Date created, Date updated, Date unlisted,
            int teamID, int playerID, int globalTeamID, String sportsbookUrl, boolean isInPlay, SportsBook sportsBook,
            BettingMarket bettingMarket) {
        BettingOutcomeID = bettingOutcomeID;
        BettingMarketID = bettingMarketID;
        BettingOutcomeTypeID = bettingOutcomeTypeID;
        BettingOutcomeType = bettingOutcomeType;
        PayoutAmerican = payoutAmerican;
        PayoutDecimal = payoutDecimal;
        Value = value;
        Participant = participant;
        IsAvailable = isAvailable;
        IsAlternate = isAlternate;
        Created = created;
        Updated = updated;
        Unlisted = unlisted;
        TeamID = teamID;
        PlayerID = playerID;
        GlobalTeamID = globalTeamID;
        SportsbookUrl = sportsbookUrl;
        IsInPlay = isInPlay;
        this.sportsBook = sportsBook;
        this.bettingMarket = bettingMarket;
    }

    public int getBettingOutcomeID() {
        return BettingOutcomeID;
    }

    public void setBettingOutcomeID(int bettingOutcomeID) {
        BettingOutcomeID = bettingOutcomeID;
    }

    public int getBettingMarketID() {
        return BettingMarketID;
    }

    public void setBettingMarketID(int bettingMarketID) {
        BettingMarketID = bettingMarketID;
    }

    public int getBettingOutcomeTypeID() {
        return BettingOutcomeTypeID;
    }

    public void setBettingOutcomeTypeID(int bettingOutcomeTypeID) {
        BettingOutcomeTypeID = bettingOutcomeTypeID;
    }

    public String getBettingOutcomeType() {
        return BettingOutcomeType;
    }

    public void setBettingOutcomeType(String bettingOutcomeType) {
        BettingOutcomeType = bettingOutcomeType;
    }

    public int getPayoutAmerican() {
        return PayoutAmerican;
    }

    public void setPayoutAmerican(int payoutAmerican) {
        PayoutAmerican = payoutAmerican;
    }

    public BigDecimal getPayoutDecimal() {
        return PayoutDecimal;
    }

    public void setPayoutDecimal(BigDecimal payoutDecimal) {
        PayoutDecimal = payoutDecimal;
    }

    public BigDecimal getValue() {
        return Value;
    }

    public void setValue(BigDecimal value) {
        Value = value;
    }

    public String getParticipant() {
        return Participant;
    }

    public void setParticipant(String participant) {
        Participant = participant;
    }

    public boolean isIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        IsAvailable = isAvailable;
    }

    public boolean isIsAlternate() {
        return IsAlternate;
    }

    public void setIsAlternate(boolean isAlternate) {
        IsAlternate = isAlternate;
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

    public Date getUnlisted() {
        return Unlisted;
    }

    public void setUnlisted(Date unlisted) {
        Unlisted = unlisted;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }

    public int getGlobalTeamID() {
        return GlobalTeamID;
    }

    public void setGlobalTeamID(int globalTeamID) {
        GlobalTeamID = globalTeamID;
    }

    public String getSportsbookUrl() {
        return SportsbookUrl;
    }

    public void setSportsbookUrl(String sportsbookUrl) {
        SportsbookUrl = sportsbookUrl;
    }

    public boolean isIsInPlay() {
        return IsInPlay;
    }

    public void setIsInPlay(boolean isInPlay) {
        IsInPlay = isInPlay;
    }

    public SportsBook getSportsBook() {
        return sportsBook;
    }

    public void setSportsBook(SportsBook sportsBook) {
        this.sportsBook = sportsBook;
    }

    public BettingMarket getBettingMarket() {
        return bettingMarket;
    }

    public void setBettingMarket(BettingMarket bettingMarket) {
        this.bettingMarket = bettingMarket;
    }

}
