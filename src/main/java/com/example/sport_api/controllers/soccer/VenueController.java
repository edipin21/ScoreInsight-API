package com.example.sport_api.controllers.soccer;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.constants.OpenApiParameters;
import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.services.soccer.VenueService;
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
@Tag(name = "Venue", description = "Endpoints for retrieving Venues information")
@RequestMapping("/soccer/scores")
public class VenueController {

    private static final Logger logger = LogManager.getLogger(VenueController.class);

    @Autowired
    private VenueService venueService;

    @Operation(summary = "Get all Venues ", description = "Retrieves a list of all Venues available.  \n"
            + "Recommended Call Interval: 4 Hours.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Venue.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/venues")
    public ResponseEntity<?> retriveAllVenues(
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        try {
            List<Venue> venues = venueService.getAllVenues();

            return ResponseUtil.createOkResponse(venues);
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while retrieving venues: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
