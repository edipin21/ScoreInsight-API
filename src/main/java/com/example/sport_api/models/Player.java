package com.example.sport_api.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@JsonPropertyOrder({ "playerId", "firstName", "lastName", "commonName", "shortName", "position", "positionCategory",
        "jersey", "foot", "height", "weight", "gender", "birthCity", "birthCity", "birthCountry", "nationality",
        "injuryStatus", "injuryBodyPart", "injuryNotes", "injuryStartDate", "updated", "photoUrl", "rotoWirePlayerID",
        "draftKingsPosition", "usaTodayPlayerID", "usaTodayHeadshotUrl",
        "usaTodayHeadshotNoBackgroundUrl", "usaTodayHeadshotUpdated", "usaTodayHeadshotNoBackgroundUpdated" })
@Entity
public class Player {
    @Id
    private int PlayerId;
    private String FirstName;
    private String LastName;
    private String CommonName;
    private String ShortName;
    private String Position;
    private String PositionCategory;
    private int Jersey;
    private String Foot;
    private int Height;
    private int Weight;
    private String Gender;
    private Date BirthDate;
    private String BirthCity;
    private String BirthCountry;
    private String Nationality;
    private String InjuryStatus;
    private String InjuryBodyPart;
    private String InjuryNotes;
    private Date InjuryStartDate;
    private Date Updated;
    private String PhotoUrl;
    private int RotoWirePlayerID;
    private String DraftKingsPosition;
    private int UsaTodayPlayerID;
    private String UsaTodayHeadshotUrl;
    private String UsaTodayHeadshotNoBackgroundUrl;
    private Date UsaTodayHeadshotUpdated;
    private Date UsaTodayHeadshotNoBackgroundUpdated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private TeamDetail teamDetail;

    public int getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(int playerId) {
        PlayerId = playerId;
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

    public String getCommonName() {
        return CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPositionCategory() {
        return PositionCategory;
    }

    public void setPositionCategory(String positionCategory) {
        PositionCategory = positionCategory;
    }

    public int getJersey() {
        return Jersey;
    }

    public void setJersey(int jersey) {
        Jersey = jersey;
    }

    public String getFoot() {
        return Foot;
    }

    public void setFoot(String foot) {
        Foot = foot;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getBirthCity() {
        return BirthCity;
    }

    public void setBirthCity(String birthCity) {
        BirthCity = birthCity;
    }

    public String getBirthCountry() {
        return BirthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        BirthCountry = birthCountry;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getInjuryStatus() {
        return InjuryStatus;
    }

    public void setInjuryStatus(String injuryStatus) {
        InjuryStatus = injuryStatus;
    }

    public String getInjuryBodyPart() {
        return InjuryBodyPart;
    }

    public void setInjuryBodyPart(String injuryBodyPart) {
        InjuryBodyPart = injuryBodyPart;
    }

    public String getInjuryNotes() {
        return InjuryNotes;
    }

    public void setInjuryNotes(String injuryNotes) {
        InjuryNotes = injuryNotes;
    }

    public Date getInjuryStartDate() {
        return InjuryStartDate;
    }

    public void setInjuryStartDate(Date injuryStartDate) {
        InjuryStartDate = injuryStartDate;
    }

    public Date getUpdated() {
        return Updated;
    }

    public void setUpdated(Date updated) {
        Updated = updated;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public int getRotoWirePlayerID() {
        return RotoWirePlayerID;
    }

    public void setRotoWirePlayerID(int rotoWirePlayerID) {
        RotoWirePlayerID = rotoWirePlayerID;
    }

    public String getDraftKingsPosition() {
        return DraftKingsPosition;
    }

    public void setDraftKingsPosition(String draftKingsPosition) {
        DraftKingsPosition = draftKingsPosition;
    }

    public int getUsaTodayPlayerID() {
        return UsaTodayPlayerID;
    }

    public void setUsaTodayPlayerID(int usaTodayPlayerID) {
        UsaTodayPlayerID = usaTodayPlayerID;
    }

    public String getUsaTodayHeadshotUrl() {
        return UsaTodayHeadshotUrl;
    }

    public void setUsaTodayHeadshotUrl(String usaTodayHeadshotUrl) {
        UsaTodayHeadshotUrl = usaTodayHeadshotUrl;
    }

    public String getUsaTodayHeadshotNoBackgroundUrl() {
        return UsaTodayHeadshotNoBackgroundUrl;
    }

    public void setUsaTodayHeadshotNoBackgroundUrl(String usaTodayHeadshotNoBackgroundUrl) {
        UsaTodayHeadshotNoBackgroundUrl = usaTodayHeadshotNoBackgroundUrl;
    }

    public Date getUsaTodayHeadshotUpdated() {
        return UsaTodayHeadshotUpdated;
    }

    public void setUsaTodayHeadshotUpdated(Date usaTodayHeadshotUpdated) {
        UsaTodayHeadshotUpdated = usaTodayHeadshotUpdated;
    }

    public Date getUsaTodayHeadshotNoBackgroundUpdated() {
        return UsaTodayHeadshotNoBackgroundUpdated;
    }

    public void setUsaTodayHeadshotNoBackgroundUpdated(Date usaTodayHeadshotNoBackgroundUpdated) {
        UsaTodayHeadshotNoBackgroundUpdated = usaTodayHeadshotNoBackgroundUpdated;
    }

}
