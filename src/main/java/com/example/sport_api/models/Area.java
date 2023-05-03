package com.example.sport_api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({ "areaId", "countryCode", "name", "competitions" })
public class Area {

    @Id
    private int AreaId;
    private String CountryCode;
    private String Name;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Competition> Competitions;

    public Area() {
        super();
    }

    // public Area(int areaId, String countryCode, String name, Competition[]
    // competitions) {
    // super();
    // AreaId = areaId;
    // CountryCode = countryCode;
    // Name = name;
    // Competitions = competitions;
    // }

    public int getAreaId() {
        return AreaId;
    }

    public Area(int areaId, String countryCode, String name, List<Competition> competitions) {
        AreaId = areaId;
        CountryCode = countryCode;
        Name = name;
        Competitions = competitions;
    }

    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Competition> getCompetitions() {
        return Competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        Competitions = competitions;
    }

    // public Competition[] getCompetitions() {
    // return Competitions;
    // }

    // public void setCompetitions(Competition[] competitions) {
    // Competitions = competitions;
    // }

}
