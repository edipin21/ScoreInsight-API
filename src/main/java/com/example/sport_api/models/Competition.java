package com.example.sport_api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonPropertyOrder({ "competitionId", "AreaId", "areaName",
        "competitionName", "name", "gender", "type",
        "format", "seasons", "stringKey" })
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "competitionId")
@Entity
// @Inheritance(strategy = InheritanceType.JOINED)
public class Competition {

    @Id
    // @Column(name = "CompetitionId")
    private int CompetitionId;
    private String AreaName;
    private String Name;
    private String Gender;
    private String Type;
    private String Format;
    @JsonProperty("Key")
    private String StringKey;

    @ManyToOne
    @JoinColumn(name = "AreaId")
    @JsonBackReference
    private Area area;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("competition-season")
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

    public Competition(int competitionId, String areaName, String name, String gender, String type, String format,
            String stringKey, Area area, List<Season> seasons, Season currentSeason, List<TeamDetail> teams,
            List<Game> games) {
        CompetitionId = competitionId;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Season> getSeasons() {
        return Seasons;
    }

    public void setSeasons(List<Season> seasons) {
        Seasons = seasons;
    }

    @Override
    public String toString() {
        return "Competition [CompetitionId=" + CompetitionId + ", AreaName=" + AreaName + ", Name=" + Name + ", Gender="
                + Gender + ", Type=" + Type + ", Format=" + Format + ", StringKey=" + StringKey + ", area=" + area
                + ", Seasons=" + Seasons + "]";
    }

}
