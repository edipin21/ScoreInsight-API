package com.example.sport_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sport_api.models.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query(value = "SELECT team_id FROM team", nativeQuery = true)
    List<Integer> findAllTeamsIds();

}
