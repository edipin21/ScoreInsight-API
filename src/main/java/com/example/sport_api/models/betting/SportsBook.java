package com.example.sport_api.models.betting;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SportsBook {

    @Id
    int SportsbookID;
    String Name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BettingMarketId", insertable = false, updatable = false)
    private BettingMarket bettingMarket;

    public SportsBook() {
    }

    public SportsBook(int sportsbookID, String name, BettingMarket bettingMarket) {
        SportsbookID = sportsbookID;
        Name = name;
        this.bettingMarket = bettingMarket;
    }

    public int getSportsbookID() {
        return SportsbookID;
    }

    public void setSportsbookID(int sportsbookID) {
        SportsbookID = sportsbookID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BettingMarket getBettingMarket() {
        return bettingMarket;
    }

    public void setBettingMarket(BettingMarket bettingMarket) {
        this.bettingMarket = bettingMarket;
    }

}
