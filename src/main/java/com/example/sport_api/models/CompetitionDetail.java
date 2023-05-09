// package com.example.sport_api.models;

// import java.util.List;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonPropertyOrder;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.Inheritance;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
// // import jakarta.persistence.PrimaryKeyJoinColumn;
// import jakarta.persistence.PrimaryKeyJoinColumn;

// // @DiscriminatorValue("CHILD")
// @Entity
// @JsonPropertyOrder({ "competitionId", "areaId", "areaName", "name", "gender",
// "type", "format", "stringKey",
// "currentSeason", "seasons", "Teams", "games" })
// @PrimaryKeyJoinColumn(name = "CompetitionId")
// // @Inheritance
// public class CompetitionDetail extends Competition {

// @OneToOne // (cascade = CascadeType.MERGE)
// private Season CurrentSeason;

// @OneToMany(mappedBy = "competitionDetail", cascade = CascadeType.ALL, fetch =
// FetchType.EAGER)
// @JsonManagedReference
// private List<TeamDetail> Teams;

// @OneToMany(mappedBy = "competitionDetail", cascade = CascadeType.ALL, fetch =
// FetchType.EAGER)
// @JsonManagedReference
// private List<Game> games;

// // @OneToMany(mappedBy = "competitionDetail", cascade = CascadeType.ALL,
// fetch =
// // FetchType.EAGER)
// // @JsonManagedReference("competitionDetail-seasons")
// // private List<Season> Seasons;
// public CompetitionDetail() {
// }

// public CompetitionDetail(int competitionId, String areaName, String name,
// String gender, String type, String format,
// String stringKey, Area area, List<Season> seasons) {
// super(competitionId, areaName, name, gender, type, format, stringKey, area,
// seasons);

// }

// public CompetitionDetail(int competitionId, String areaName, String name,
// String gender, String type, String format,
// String stringKey, Area area, List<Season> seasons, Season currentSeason,
// List<TeamDetail> teams,
// List<Game> games) {
// super(competitionId, areaName, name, gender, type, format, stringKey, area,
// seasons);
// CurrentSeason = currentSeason;
// Teams = teams;
// this.games = games;
// }

// public Season getCurrentSeason() {
// return CurrentSeason;
// }

// public void setCurrentSeason(Season currentSeason) {
// CurrentSeason = currentSeason;
// }

// public List<TeamDetail> getTeams() {
// return Teams;
// }

// public void setTeams(List<TeamDetail> teams) {
// Teams = teams;
// }

// public List<Game> getGames() {
// return games;
// }

// public void setGames(List<Game> games) {
// this.games = games;
// }

// // @OneToOne
// // @JoinColumn(name = "competitionId")
// // private Competition competition;

// }
