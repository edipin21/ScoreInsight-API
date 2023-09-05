package com.example.sport_api.repositories.soccer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.sport.TeamSeason;

public interface TeamSeasonRepositry extends JpaRepository<TeamSeason, Integer> {

}
