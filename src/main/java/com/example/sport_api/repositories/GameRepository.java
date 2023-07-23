package com.example.sport_api.repositories;

import java.util.List;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.sport_api.models.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(value = "SELECT * FROM game WHERE day = :date AND competition_id = :competitionId", nativeQuery = true)
    List<Game> findByDateAndCompetitionId(@Param("date") Date date,
            @Param("competitionId") Integer competitionId);

    @Query(value = "SELECT game_id AS gameId, competition_id FROM game", nativeQuery = true)
    List<Object[]> findGameIdAndCompetition();

    @Query(value = "SELECT game_id FROM game", nativeQuery = true)
    List<Integer> findAllGameIds();

}
