package com.example.sport_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.Area;
import com.example.sport_api.models.AreaDto;
import com.example.sport_api.repositories.AreaRepository;
import com.example.sport_api.services.AreaService;

@RestController
public class AreaConrtoller {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaRepository areaRepository;

    public AreaConrtoller(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping("/areas")
    public List<AreaDto> retriveAllArea() {
        return areaService.getAllAreasWithCompetitions();
    }

}
