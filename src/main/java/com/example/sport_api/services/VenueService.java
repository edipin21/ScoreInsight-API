package com.example.sport_api.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.repositories.soccer.VenueRepository;

@Service
public class VenueService {

    private static final Logger logger = LogManager.getLogger(VenueService.class);

    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getAllVenues() {

        try {
            List<Venue> venues = venueRepository.findAll();

            return venues;
        } catch (DataAccessException e) {
            logger.error("Error occurred while accessing data while retrieving areas: " + e.getMessage(), e);
            throw e;
        }

    }
}
