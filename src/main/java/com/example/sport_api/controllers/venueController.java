package com.example.sport_api.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.services.VenueService;
import com.example.sport_api.util.ResponseUtil;

@RestController
public class VenueController {

    private static final Logger logger = LogManager.getLogger(VenueController.class);

    @Autowired
    private VenueService venueService;

    @GetMapping("scores/venues")
    public ResponseEntity<List<Venue>> retriveAllVenues() {

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
