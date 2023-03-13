package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.Season;

public interface SeasonRepository extends JpaRepository<Season, Integer> {

}
