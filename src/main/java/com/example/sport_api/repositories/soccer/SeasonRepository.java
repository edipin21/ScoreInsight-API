package com.example.sport_api.repositories.soccer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.sport.Season;

public interface SeasonRepository extends JpaRepository<Season, Integer> {

}
