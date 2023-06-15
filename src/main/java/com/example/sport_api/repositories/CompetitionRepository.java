package com.example.sport_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionDto;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    @Query(value = "SELECT c.CompetitionId AS competitionId,c.Format AS format,c.AreaName AS areaName, c.Name AS name,c.Gender AS gender,c.Type AS type,c.StringKey AS stringKey , c.Seasons AS seasons, c.AreaId AS areaId FROM Competition c")
    List<CompetitionDto> findAllCompetitions();

    @Query(value = "SELECT competition_id FROM competition", nativeQuery = true)
    List<Integer> findAllCompetitionIds();
}
