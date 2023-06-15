package com.example.sport_api.repositories;

import java.util.List;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(value = "SELECT * FROM game WHERE date_time = :dateTime AND competition_id = :competitionId", nativeQuery = true)
    List<Game> findByDateTimeAndCompetitionId(@Param("dateTime") Date dateTime,
            @Param("competitionId") Integer competitionId);

}
