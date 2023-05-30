package com.example.sport_api.controllers;

import java.util.List;
import com.example.sport_api.models.AreaDto;
import com.example.sport_api.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaConrtoller {

    @Autowired
    private AreaService areaService;

    public AreaConrtoller(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping("/areas")
    public List<AreaDto> retriveAllArea() {
        return areaService.getAllAreasWithCompetitions();
    }

}
