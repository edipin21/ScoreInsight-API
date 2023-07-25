package com.example.sport_api.repositories.soccer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sport_api.models.sport.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query(value = "SELECT TeamId FROM Team", nativeQuery = true)
    List<Integer> findAllTeamsIds();

}
