package com.example.sport_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    @Query(value = "SELECT * FROM membership WHERE  competition_id = :competitionId AND active = true ", nativeQuery = true)
    List<Membership> findByCompetitionId(@Param("competitionId") Integer competitionId);

    @Query(value = "SELECT * FROM membership WHERE competition_id = :competitionId AND Updated >= DATE_SUB(NOW(), INTERVAL :days DAY)", nativeQuery = true)
    List<Membership> findRecentMembershipsByCompetitionId(@Param("competitionId") Integer competitionId,
            @Param("days") int days);

    @Query(value = "SELECT * FROM membership WHERE  competition_id = :competitionId AND team_id =:teamId ", nativeQuery = true)
    List<Membership> findByCompetitionIdAndTeamId(@Param("competitionId") Integer competitionId,
            @Param("teamId") Integer teamId);

}
