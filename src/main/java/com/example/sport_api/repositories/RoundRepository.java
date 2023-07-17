package com.example.sport_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.sport_api.models.Round;

public interface RoundRepository extends JpaRepository<Round, Integer> {

    @Query(value = "SELECT * FROM round WHERE competition_id = :competitionId AND soccer_season = :year ", nativeQuery = true)
    List<Round> findRoundsByCompetitionAndYear(@Param("competitionId") Integer competitionId,
            @Param("year") Integer year);
}
