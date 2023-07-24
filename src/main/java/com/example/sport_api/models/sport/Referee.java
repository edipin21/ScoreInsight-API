package com.example.sport_api.models.sport;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "refereeId", "firstName", "lastName", "shortName", "nationality" })
public class Referee {

    @Id
    private Integer RefereeId;
    private String FirstName;
    private String LastName;
    private String ShortName;
    private String Nationality;

    public Referee() {
    }

    public Referee(Integer refereeId, String firstName, String lastName, String shortName, String nationality) {
        RefereeId = refereeId;
        FirstName = firstName;
        LastName = lastName;
        ShortName = shortName;
        Nationality = nationality;
    }

    public Integer getRefereeId() {
        return RefereeId;
    }

    public void setRefereeId(Integer refereeId) {
        RefereeId = refereeId;
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

    @Override
    public String toString() {
        return "Referee [RefereeId=" + RefereeId + ", FirstName=" + FirstName + ", LastName=" + LastName
                + ", ShortName=" + ShortName + ", Nationality=" + Nationality + "]";
    }

}
