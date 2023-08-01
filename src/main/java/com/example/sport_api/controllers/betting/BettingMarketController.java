package com.example.sport_api.controllers.betting;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.config.OpenApiParameters;
import com.example.sport_api.models.betting.BettingMarket;
import com.example.sport_api.services.betting.BettingMarketService;
import com.example.sport_api.services.soccer.CompetitionService;
import com.example.sport_api.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Betting Market", description = "Endpoints for retrieving Betting Market information")

public class BettingMarketController {

    @Autowired
    private BettingMarketService bettingMarketService;

    @Autowired
    private CompetitionService competitionService;

    @Operation(summary = "Get Betting Market by competition and eventId . ", description = "Retrieves  Betting Market by competition and eventId.  \n"
            + "Recommended Call Interval: 10 Minutes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = BettingMarket.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("odds/BettingMarkets/{competition}/{eventId}")
    public ResponseEntity<?> retriveBettingMarketsByCompetitionAndEventId(
            @Parameter(description = OpenApiParameters.COMPETITION_ID_DESCRIPTION) @PathVariable String competition,
            @Parameter(description = OpenApiParameters.EVENT_ID_DESCRIPTION) @PathVariable String eventId) {

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
