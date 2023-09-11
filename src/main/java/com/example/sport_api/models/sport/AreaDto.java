package com.example.sport_api.models.sport;

import java.util.List;

public class AreaDto {
    private int areaId;
    private String countryCode;
    private String name;
    private List<CompetitionDto> competitions;

    public AreaDto() {
    }

    public AreaDto(int areaId, String countryCode, String name, List<CompetitionDto> competitions) {
        this.areaId = areaId;
        this.countryCode = countryCode;
        this.name = name;
        this.competitions = competitions;
    }

    public AreaDto(int areaId, String countryCode, String name) {
        this.areaId = areaId;
        this.countryCode = countryCode;
        this.name = name;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompetitionDto> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionDto> competitions) {
        this.competitions = competitions;
    }
}
