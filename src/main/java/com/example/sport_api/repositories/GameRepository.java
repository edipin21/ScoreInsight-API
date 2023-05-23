package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
