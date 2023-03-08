package com.example.sport_api.area;

import java.util.Arrays;

import com.example.sport_api.competition.Competition;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Area {

    @Id
    private int AreaId;
    private String CountryCode;
    private String Name;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private Competition[] Competitions;

    public Area() {
        super();
    }

    public Area(int areaId, String countryCode, String name, Competition[] competitions) {
        super();
        AreaId = areaId;
        CountryCode = countryCode;
        Name = name;
        Competitions = competitions;
    }

    public int getAreaId() {
        return AreaId;
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

    public Competition[] getCompetitions() {
        return Competitions;
    }

    public void setCompetitions(Competition[] competitions) {
        Competitions = competitions;
    }

    @Override
    public String toString() {
        return "Area [AreaId=" + AreaId + ", CountryCode=" + CountryCode + ", Name=" + Name + ", Competitions="
                + Arrays.toString(Competitions) + "]";
    }

}
