package com.example.sport_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.sport.Area;

public interface AreaRepository extends JpaRepository<Area, Integer> {

}
