package com.example.sport_api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TeamDetail {

    @Id
    private int TeamId;
    private int AreaId;
    private int VenueId;
    // need to fix
    @JsonProperty("Key")
    private String StringKey;
    private String Name;
    private String FullName;
    private boolean Active;
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

    @OneToMany(mappedBy = "teamDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Player> Players;

    @ManyToOne
    @JoinColumn(name = "competitionId")
    @JsonBackReference
    private CompetitionDetail competitionDetail;

    public TeamDetail() {
        super();
    }

    public TeamDetail(int teamId, int areaId, int venueId, String stringKey, String name, String fullName,
            boolean active, String areaName, String venueName, String gender, String type, String address, String city,
            String zip, String phone, String fax, String website, String email, int founded, String clubColor1,
            String clubColor2, String clubColor3, String nickname1, String nickname2, String nickname3,
            String wikipediaLogoUrl, String wikipediaWordMarkUrl, int globalTeamId, List<Player> players,
            CompetitionDetail competitionDetail) {
        super();
        TeamId = teamId;
        AreaId = areaId;
        VenueId = venueId;
        StringKey = stringKey;
        Name = name;
        FullName = fullName;
        Active = active;
        AreaName = areaName;
        VenueName = venueName;
        Gender = gender;
        Type = type;
        Address = address;
        City = city;
        Zip = zip;
        Phone = phone;
        Fax = fax;
        Website = website;
        Email = email;
        Founded = founded;
        ClubColor1 = clubColor1;
        ClubColor2 = clubColor2;
        ClubColor3 = clubColor3;
        Nickname1 = nickname1;
        Nickname2 = nickname2;
        Nickname3 = nickname3;
        WikipediaLogoUrl = wikipediaLogoUrl;
        WikipediaWordMarkUrl = wikipediaWordMarkUrl;
        GlobalTeamId = globalTeamId;
        Players = players;
        this.competitionDetail = competitionDetail;
    }

    public CompetitionDetail getCompetitionDetail() {
        return competitionDetail;
    }

    public void setCompetitionDetail(CompetitionDetail competitionDetail) {
        this.competitionDetail = competitionDetail;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public int getAreaId() {
        return AreaId;
    }

    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    public int getVenueId() {
        return VenueId;
    }

    public void setVenueId(int venueId) {
        VenueId = venueId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getVenueName() {
        return VenueName;
    }

    public void setVenueName(String venueName) {
        VenueName = venueName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getFounded() {
        return Founded;
    }

    public void setFounded(int founded) {
        Founded = founded;
    }

    public String getClubColor1() {
        return ClubColor1;
    }

    public void setClubColor1(String clubColor1) {
        ClubColor1 = clubColor1;
    }

    public String getClubColor2() {
        return ClubColor2;
    }

    public void setClubColor2(String clubColor2) {
        ClubColor2 = clubColor2;
    }

    public String getClubColor3() {
        return ClubColor3;
    }

    public void setClubColor3(String clubColor3) {
        ClubColor3 = clubColor3;
    }

    public String getNickname1() {
        return Nickname1;
    }

    public void setNickname1(String nickname1) {
        Nickname1 = nickname1;
    }

    public String getNickname2() {
        return Nickname2;
    }

    public void setNickname2(String nickname2) {
        Nickname2 = nickname2;
    }

    public String getNickname3() {
        return Nickname3;
    }

    public void setNickname3(String nickname3) {
        Nickname3 = nickname3;
    }

    public String getWikipediaLogoUrl() {
        return WikipediaLogoUrl;
    }

    public void setWikipediaLogoUrl(String wikipediaLogoUrl) {
        WikipediaLogoUrl = wikipediaLogoUrl;
    }

    public String getWikipediaWordMarkUrl() {
        return WikipediaWordMarkUrl;
    }

    public void setWikipediaWordMarkUrl(String wikipediaWordMarkUrl) {
        WikipediaWordMarkUrl = wikipediaWordMarkUrl;
    }

    public int getGlobalTeamId() {
        return GlobalTeamId;
    }

    public void setGlobalTeamId(int globalTeamId) {
        GlobalTeamId = globalTeamId;
    }

    // public Player[] getPlayers() {
    // return Players;
    // }

    // public void setPlayers(Player[] players) {
    // Players = players;
    // }

    public String getStringKey() {
        return StringKey;
    }

    public void setStringKey(String stringKey) {
        StringKey = stringKey;
    }

    public List<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(List<Player> players) {
        Players = players;
    }

}
