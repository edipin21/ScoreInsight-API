package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity
@JsonPropertyOrder({ "competitionId", "areaId", "areaName", "name", "gender", "type", "format", "stringKey",
        "seasons" })
public class Competition {
    @Id
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
    @JsonManagedReference
    private Season[] Seasons;

    public Competition() {
        super();
    }

    public Competition(int competitionId, String areaName, String name, String gender, String type, String format,
            Area area, Season[] seasons, String stringKey) {
        super();
        CompetitionId = competitionId;
        AreaName = areaName;
        Name = name;
        Gender = gender;
        Type = type;
        Format = format;
        this.area = area;
        Seasons = seasons;
        StringKey = stringKey;
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

    public Season[] getSeasons() {
        return Seasons;
    }

    public void setSeasons(Season[] seasons) {
        Seasons = seasons;
    }

}
