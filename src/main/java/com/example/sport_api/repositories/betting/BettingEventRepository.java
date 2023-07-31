package com.example.sport_api.repositories.betting;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sport_api.models.betting.BettingEvent;

public interface BettingEventRepository extends JpaRepository<BettingEvent, Integer> {

    @Query(value = "SELECT * FROM BettingEvent WHERE CompetitionId = :competition AND Season = :season", nativeQuery = true)
    List<BettingEvent> findByCompetitionAndSeason(@Param("competition") Integer competition,
            @Param("season") Integer season);

    @Query(value = "SELECT BettingEventID,CompetitionId  FROM BettingEvent", nativeQuery = true)
    List<Object[]> findBettingEventIdAndCompetition();

}
