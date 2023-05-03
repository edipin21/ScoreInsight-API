package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.CompetitionDetail;

public interface CompetitionDetailRepository extends JpaRepository<CompetitionDetail, Integer> {

}