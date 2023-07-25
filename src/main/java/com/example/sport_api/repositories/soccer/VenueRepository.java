package com.example.sport_api.repositories.soccer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.sport.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
