package com.example.sport_api.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.example.sport_api.models.Area;

public interface AreaRepository extends JpaRepository<Area, Integer> {

    // @Query("SELECT e FROM Area e JOIN FETCH e.Competitions")
    // List<Area> findAllWithRelatedEntities();

}
