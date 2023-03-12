package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
