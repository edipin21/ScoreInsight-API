package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.Round;

public interface RoundRepository extends JpaRepository<Round, Integer> {

}