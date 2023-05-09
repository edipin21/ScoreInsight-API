package com.example.sport_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sport_api.models.Competition;
import com.example.sport_api.models.CompetitionProjection;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    // @Query(value = "SELECT c.competition_id ,c.format FROM competition c",
    // nativeQuery = true)
    // @Query(value = "SELECT c.CompetitionId, c.Format FROM Competition c")
    @Query(value = "SELECT c.CompetitionId AS competitionId, c.Format AS format,c.AreaName AS areaName, c.Name AS name,c.Gender AS gender,c.Type AS type,c.StringKey AS stringKey , c.Seasons AS seasons, c.area.id AS areaId FROM Competition c")
    List<CompetitionProjection> findAllCompetitions();
}
