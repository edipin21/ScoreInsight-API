package com.example.sport_api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonPropertyOrder({ "competitionId", "areaId", "areaName", "name", "gender", "type", "format",
        "Key", "currentSeason", "seasons" })
@Entity
public class Competition {

    @Id
    private int CompetitionId;
    @Column(name = "area_id")
    private int AreaId;
    private String AreaName;
    private String Name;
    private String Gender;
    private String Type;
    private String Format;
    @JsonProperty("Key")
    private String StringKey;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    private Area area;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private List<Season> Seasons;

    @OneToOne // (cascade = CascadeType.MERGE)
    private Season CurrentSeason;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // CompetitionDetail
    @JsonManagedReference("competition-teamDetail")
    private List<TeamDetail> Teams;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // CompetitionDetail
    @JsonManagedReference("competition-game")
    private List<Game> games;

    public Competition() {
    }

    public Competition(int competitionId, int areaId, String areaName, String name, String gender, String type,
            String format, String stringKey, Area area, List<Season> seasons, Season currentSeason,
            List<TeamDetail> teams, List<Game> games) {
        CompetitionId = competitionId;
        AreaId = areaId;
        AreaName = areaName;
        Name = name;
        Gender = gender;
        Type = type;
        Format = format;
        StringKey = stringKey;
        this.area = area;
        Seasons = seasons;
        CurrentSeason = currentSeason;
        Teams = teams;
        this.games = games;
    }

    public Season getCurrentSeason() {
        return CurrentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        CurrentSeason = currentSeason;
    }

    public List<TeamDetail> getTeams() {
        return Teams;
    }

    public void setTeams(List<TeamDetail> teams) {
        Teams = teams;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(int competitionId) {
        CompetitionId = competitionId;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getStringKey() {
        return StringKey;
    }

    public void setStringKey(String stringKey) {
        StringKey = stringKey;
    }

    public List<Season> getSeasons() {
        return Seasons;
    }

    public void setSeasons(List<Season> seasons) {
        Seasons = seasons;
    }

    public int getAreaId() {
        return AreaId;
    }

    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
