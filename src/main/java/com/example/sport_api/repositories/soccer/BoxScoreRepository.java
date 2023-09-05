package com.example.sport_api.repositories.soccer;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.sport.BoxScore;

public interface BoxScoreRepository extends JpaRepository<BoxScore, Integer> {

    @Query(value = "SELECT * FROM BoxScore WHERE game_GameId = :gameId", nativeQuery = true)
    BoxScore findByGameId(@Param("gameId") Integer gameId);

    @Query(value = "SELECT * FROM BoxScore WHERE competition = :competition AND game_GameId = :gameId", nativeQuery = true)
    BoxScore findByCompetitionAndGameId(@Param("competition") Integer competition, @Param("gameId") Integer gameId);

    @Query(value = "SELECT * FROM BoxScore WHERE competition = :competition AND dateTime = :date", nativeQuery = true)
    List<BoxScore> findByCompetitionAndDate(@Param("competition") Integer competition, @Param("date") Date date);

}
