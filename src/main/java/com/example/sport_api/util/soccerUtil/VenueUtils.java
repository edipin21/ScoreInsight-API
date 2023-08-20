package com.example.sport_api.util.soccerUtil;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.repositories.soccer.VenueRepository;

public class VenueUtils {

    public static void saveVenuesToDB(List<Venue> venues, VenueRepository venueRepository) throws DataAccessException {

        venueRepository.saveAll(venues);

    }

}
