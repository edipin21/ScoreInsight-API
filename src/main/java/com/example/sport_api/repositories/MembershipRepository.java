package com.example.sport_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    @Query(value = "SELECT * FROM membership WHERE  competition_id = :competitionId", nativeQuery = true)
    List<Membership> findByCompetitionId(@Param("competitionId") Integer competitionId);
}
