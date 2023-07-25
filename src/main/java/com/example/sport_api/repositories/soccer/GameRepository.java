package com.example.sport_api.repositories.soccer;

import java.util.List;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.sport.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(value = "SELECT * FROM Game WHERE day = :date AND competition_id = :competitionId", nativeQuery = true)
    List<Game> findByDateAndCompetitionId(@Param("date") Date date,
            @Param("competitionId") Integer competitionId);

    @Query(value = "SELECT GameId AS gameId, competition_id FROM Game", nativeQuery = true)
    List<Object[]> findGameIdAndCompetition();

    @Query(value = "SELECT GameId FROM Game", nativeQuery = true)
    List<Integer> findAllGameIds();

}
