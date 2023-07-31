package com.example.sport_api.repositories.betting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.sport_api.models.betting.BettingMarket;

public interface BettingMarketRepository extends JpaRepository<BettingMarket, Integer> {

    @Query(value = "SELECT * FROM BettingMarket WHERE CompetitionId = :competition AND BettingEventID = :eventId", nativeQuery = true)
    List<BettingMarket> findByCompetitionAndEventId(@Param("competition") Integer competition,
            @Param("eventId") Integer eventId);

    @Query(value = "SELECT BettingEventID FROM BettingMarket", nativeQuery = true)
    List<Integer> findAllEventsIds();
}
