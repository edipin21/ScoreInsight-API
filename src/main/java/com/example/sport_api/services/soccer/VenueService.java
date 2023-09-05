package com.example.sport_api.services.soccer;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Venue;
import com.example.sport_api.repositories.soccer.VenueRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.soccerUtil.VenueUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class VenueService {

    private static final Logger logger = LogManager.getLogger(VenueService.class);

    @Autowired
    private VenueRepository venueRepository;

    public void syncVenuesFromExternalApi() {
        try {
            String VenuesApiUrl = ExternalSoccerApiEndpoints.VENUES_RESOURCE_URL;
            TypeReference<List<Venue>> venueTypeRef = new TypeReference<>() {
            };

            List<Venue> venues = ExternalApiDataFetcherUtil.fetchListDataFromExternalApi(
                    VenuesApiUrl,
                    venueTypeRef);

            VenueUtils.saveVenuesToDB(venues, venueRepository);

        } catch (Exception e) {
            ExternalApiDataFetcherUtil.handleException(e);
        }
    }

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
