package com.example.sport_api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Area;
import com.example.sport_api.repositories.AreaRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> retrieveAllAreas() {
        return areaRepository.findAll();
        // return areaRepository.findAllWithRelatedEntities();
    }

}
