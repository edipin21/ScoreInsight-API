package com.example.sport_api.controllers.betting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.services.betting.BettingMarketService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class BettingMarketController {

    @Autowired
    private BettingMarketService bettingMarketService;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("odds/BettingMarkets/{competition}/{eventId}")
    public ResponseEntity<?> retriveBettingMarketsByCompetitionAndEventId(@PathVariable String competition,
            @PathVariable String eventId) {

        Integer theCompetition = Integer.parseInt(competition);
        Integer theEventId = Integer.parseInt(eventId);

        if (competition == null ||
                !competitionService.isCompetitionValid(theCompetition)) {
            throw new IllegalArgumentException("Invalid Argument: The competition parameter is invalid ");
        }

        if (theEventId == null ||
                !bettingMarketService.isEventIdValid(theEventId)) {
            throw new IllegalArgumentException("Invalid Argument: The eventId parameter is invalid ");
        }

        List<BettingMarket> bettingMarkets = bettingMarketService
                .getBettingMarketsByCompetitionAndEventId(theCompetition, theEventId);

        return ResponseUtil.createOkResponse(bettingMarkets);
    }

}
