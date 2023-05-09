package com.example.sport_api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "competitionId", "areaId", "areaName",
        "competitionName", "name", "gender", "type",
        "format", "stringKey", "seasons" })
public interface CompetitionProjection {

    int getCompetitionId();

    String getFormat();

    String getAreaName();

    String getName();

    String getGender();

    String getType();

    String getStringKey();

    List<Season> getSeasons();

    Integer getAreaId();

}
