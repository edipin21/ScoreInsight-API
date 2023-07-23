package com.example.sport_api.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.sport_api.models.PlayerGame;

public interface playerGameRepository extends JpaRepository<PlayerGame, Integer> {

    @Query(value = "SELECT * FROM player_game WHERE competition = :competition AND date_time = :date", nativeQuery = true)
    List<PlayerGame> findByCompetitionAndDate(@Param("competition") Integer competition, @Param("date") Date date);

}
