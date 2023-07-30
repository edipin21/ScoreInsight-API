package com.example.sport_api.repositories.betting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport_api.models.betting.SportsBook;

public interface SportsBookRepository extends JpaRepository<SportsBook, Integer> {

}
