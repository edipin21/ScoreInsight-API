package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@JsonPropertyOrder({ "coachId", "firstName", "lastName", "shortName", "nationality" })
public class Coach {

    @Id
    private Integer CoachId;
    private String FirstName;
    private String LastName;
    private String ShortName;
    private String Nationality;

    // @OneToOne
    // @JoinColumn(name = "box_score_id")
    // private BoxScore boxScore;

    public Coach() {
    }

    public Coach(Integer coachId, String firstName, String lastName, String shortName, String nationality) {
        CoachId = coachId;
        FirstName = firstName;
        LastName = lastName;
        ShortName = shortName;
        Nationality = nationality;
    }

    public Integer getCoachId() {
        return CoachId;
    }

    public void setCoachId(Integer coachId) {
        CoachId = coachId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    // public BoxScore getBoxScore() {
    // return boxScore;
    // }

    // public void setBoxScore(BoxScore boxScore) {
    // this.boxScore = boxScore;
    // }

}
