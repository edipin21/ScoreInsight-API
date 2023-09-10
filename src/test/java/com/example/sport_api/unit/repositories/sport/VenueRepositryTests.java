package com.example.sport_api.unit.repositories.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.repositories.soccer.VenueRepository;

@DataJpaTest
public class VenueRepositryTests {

    @Autowired
    private VenueRepository venueRepository;

    @Test
    public void testVenueMapping() {

        // Create a new Venue object with sample data
        Venue venue = new Venue();
        venue.setVenueId(1);
        venue.setName("test");

        // Save the Venue object to the repository
        venueRepository.save(venue);

        // Retrieve the Venue object from the repository using its ID
        // and assign it to the optionalVenue variable
        Optional<Venue> optionalVenue = venueRepository.findById(1);

        assertTrue(optionalVenue.isPresent());

        Venue savedVenue = optionalVenue.get();

        // Perform assertions to verify that the retrieved data matches the expected
        // values
        assertEquals(1, savedVenue.getVenueId());
        assertEquals("test", savedVenue.getName());

    }

}
