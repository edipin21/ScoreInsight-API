package com.example.sport_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query(value = "SELECT * FROM player WHERE team_id = :teamId", nativeQuery = true)
    List<Player> findPlayersByTeam(@Param("teamId") Integer teamId);
}
