package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

}
