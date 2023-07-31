package com.example.sport_api.models.betting;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "sportsbookID", "name" })
public class SportsBook {

    @Id
    int SportsbookID;
    String Name;

    public SportsBook() {
    }

    public SportsBook(int sportsbookID, String name) {
        SportsbookID = sportsbookID;
        Name = name;
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

}
