package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
