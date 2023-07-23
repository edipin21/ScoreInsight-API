package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.sport_api.models.BoxScore;

public interface BoxScoreRepository extends JpaRepository<BoxScore, Long> {

    @Query(value = "SELECT * FROM box_score WHERE game_game_id = :gameId", nativeQuery = true)
    BoxScore findByGameId(@Param("gameId") Integer gameId);

    @Query(value = "SELECT * FROM box_score WHERE competition = :competition AND game_game_id = :gameId", nativeQuery = true)
    BoxScore findByCompetitionAndGameId(@Param("competition") Integer competition, @Param("gameId") Integer gameId);

}
