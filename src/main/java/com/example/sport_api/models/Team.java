package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {

    @Id
    private int TeamId;

    private int AreaId;
    private int VenueId;
    private String shortName;
    private String Name;
    private String FullName;
    private Boolean Active;
    private String AreaName;
    private String VenueName;
    private String Gender;
    private String Type;
    private String Address;
    private String City;
    private String Zip;
    private String Phone;
    private String Fax;
    private String Website;
    private String Email;
    private int Founded;
    private String ClubColor1;
    private String ClubColor2;
    private String ClubColor3;
    private String Nickname1;
    private String Nickname2;
    private String Nickname3;
    private String WikipediaLogoUrl;
    private String WikipediaWordMarkUrl;
    private int GlobalTeamId;

    public Team() {
        super();
    }

    public Team(int TeamId, int AreaId, int VenueId, String shortName, String Name, String FullName, Boolean Active,
            String AreaName, String VenueName, String Gender, String Type, String Address, String City, String Zip,
            String Phone, String Fax, String Website, String Email, int Founded, String ClubColor1, String ClubColor2,
            String ClubColor3, String Nickname1, String Nickname2, String Nickname3, String WikipediaLogoUrl,
            String WikipediaWordMarkUrl, int GlobalTeamId) {
        super();
        this.TeamId = TeamId;
        this.AreaId = AreaId;
        this.VenueId = VenueId;
        this.shortName = shortName;
        this.Name = Name;
        this.FullName = FullName;
        this.Active = Active;
        this.AreaName = AreaName;
        this.VenueName = VenueName;
        this.Gender = Gender;
        this.Type = Type;
        this.Address = Address;
        this.City = City;
        this.Zip = Zip;
        this.Phone = Phone;
        this.Fax = Fax;
        this.Website = Website;
        this.Email = Email;
        this.Founded = Founded;
        this.ClubColor1 = ClubColor1;
        this.ClubColor2 = ClubColor2;
        this.ClubColor3 = ClubColor3;
        this.Nickname1 = Nickname1;
        this.Nickname2 = Nickname2;
        this.Nickname3 = Nickname3;
        this.WikipediaLogoUrl = WikipediaLogoUrl;
        this.WikipediaWordMarkUrl = WikipediaWordMarkUrl;
        this.GlobalTeamId = GlobalTeamId;
    }

    public int getTeamId() {
        return TeamId;
    }

    public int getAreaId() {
        return AreaId;
    }

    public int getVenueId() {
        return VenueId;
    }

    @JsonProperty("Key")
    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return Name;
    }

    public String getFullName() {
        return FullName;
    }

    public Boolean getActive() {
        return Active;
    }

    public String getAreaName() {
        return AreaName;
    }

    public String getVenueName() {
        return VenueName;
    }

    public String getGender() {
        return Gender;
    }

    public String getType() {
        return Type;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getZip() {
        return Zip;
    }

    public String getPhone() {
        return Phone;
    }

    public String getFax() {
        return Fax;
    }

    public String getWebsite() {
        return Website;
    }

    public String getEmail() {
        return Email;
    }

    public int getFounded() {
        return Founded;
    }

    public String getClubColor1() {
        return ClubColor1;
    }

    public String getClubColor2() {
        return ClubColor2;
    }

    public String getClubColor3() {
        return ClubColor3;
    }

    public String getNickname1() {
        return Nickname1;
    }

    public String getNickname2() {
        return Nickname2;
    }

    public String getNickname3() {
        return Nickname3;
    }

    public String getWikipediaLogoUrl() {
        return WikipediaLogoUrl;
    }

    public String getWikipediaWordMarkUrl() {
        return WikipediaWordMarkUrl;
    }

    public int getGlobalTeamId() {
        return GlobalTeamId;
    }

    public void setTeamId(int TeamId) {
        this.TeamId = TeamId;
    }

    public void setAreaId(int AreaId) {
        this.AreaId = AreaId;
    }

    public void setVenueId(int venueId) {
        VenueId = venueId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public void setVenueName(String venueName) {
        VenueName = venueName;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setFounded(int founded) {
        Founded = founded;
    }

    public void setClubColor1(String clubColor1) {
        ClubColor1 = clubColor1;
    }

    public void setClubColor2(String clubColor2) {
        ClubColor2 = clubColor2;
    }

    public void setClubColor3(String clubColor3) {
        ClubColor3 = clubColor3;
    }

    public void setNickname1(String nickname1) {
        Nickname1 = nickname1;
    }

    public void setNickname2(String nickname2) {
        Nickname2 = nickname2;
    }

    public void setNickname3(String nickname3) {
        Nickname3 = nickname3;
    }

    public void setWikipediaLogoUrl(String wikipediaLogoUrl) {
        WikipediaLogoUrl = wikipediaLogoUrl;
    }

    public void setWikipediaWordMarkUrl(String wikipediaWordMarkUrl) {
        WikipediaWordMarkUrl = wikipediaWordMarkUrl;
    }

    public void setGlobalTeamId(int globalTeamId) {
        GlobalTeamId = globalTeamId;
    }

    @JsonProperty("Key")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Team [TeamId=" + TeamId + ", AreaId=" + AreaId + ", VenueId=" + VenueId + ", shortName=" + shortName
                + ", Name="
                + Name + ", FullName=" + FullName + ", Active=" + Active + ", AreaName=" + AreaName + ", VenueName="
                + VenueName + ", Gender=" + Gender + ", Type=" + Type + ", Address=" + Address + ", City=" + City
                + ", Zip=" + Zip + ", Phone=" + Phone + ", Fax=" + Fax + ", Website=" + Website + ", Email=" + Email
                + ", Founded=" + Founded + ", ClubColor1=" + ClubColor1 + ", ClubColor2=" + ClubColor2 + ", ClubColor3="
                + ClubColor3 + ", Nickname1=" + Nickname1 + ", Nickname2=" + Nickname2 + ", Nickname3=" + Nickname3
                + ", WikipediaLogoUrl=" + WikipediaLogoUrl + ", WikipediaWordMarkUrl=" + WikipediaWordMarkUrl
                + ", GlobalTeamId=" + GlobalTeamId + "]";
    }

}
