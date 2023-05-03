// package com.example.sport_api.models;

// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.DiscriminatorColumn;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Inheritance;
// import jakarta.persistence.InheritanceType;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.MappedSuperclass;
// import jakarta.persistence.OneToMany;

// // @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

// @MappedSuperclass
// @DiscriminatorColumn(name = "entity_type")
// public class CompetitionBase {
// @Id
// @Column(name = "CompetitionId")
// private int CompetitionId;
// private String AreaName;
// private String Name;
// private String Gender;
// private String Type;
// private String Format;
// @JsonProperty("Key")
// private String StringKey;

// @ManyToOne
// @JoinColumn(name = "AreaId")
// @JsonBackReference
// private Area area;

// @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch =
// FetchType.EAGER)
// @JsonManagedReference
// private List<Season> Seasons;

// public CompetitionBase() {
// super();
// }

// public CompetitionBase(int competitionId, String areaName, String name,
// String gender, String type, String format,
// String stringKey, Area area, List<Season> seasons) {
// super();
// CompetitionId = competitionId;
// AreaName = areaName;
// Name = name;
// Gender = gender;
// Type = type;
// Format = format;
// StringKey = stringKey;
// this.area = area;
// Seasons = seasons;
// }

// public int getCompetitionId() {
// return CompetitionId;
// }

// public void setCompetitionId(int competitionId) {
// CompetitionId = competitionId;
// }

// public String getAreaName() {
// return AreaName;
// }

// public void setAreaName(String areaName) {
// AreaName = areaName;
// }

// public String getName() {
// return Name;
// }

// public void setName(String name) {
// Name = name;
// }

// public String getGender() {
// return Gender;
// }

// public void setGender(String gender) {
// Gender = gender;
// }

// public String getType() {
// return Type;
// }

// public void setType(String type) {
// Type = type;
// }

// public String getFormat() {
// return Format;
// }

// public void setFormat(String format) {
// Format = format;
// }

// public String getStringKey() {
// return StringKey;
// }

// public void setStringKey(String stringKey) {
// StringKey = stringKey;
// }

// public Area getArea() {
// return area;
// }

// public void setArea(Area area) {
// this.area = area;
// }

// public List<Season> getSeasons() {
// return Seasons;
// }

// public void setSeasons(List<Season> seasons) {
// Seasons = seasons;
// }

// }
