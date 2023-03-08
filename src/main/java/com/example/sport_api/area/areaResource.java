package com.example.sport_api.area;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class areaResource {

    private areaService areaService;

    public areaResource(com.example.sport_api.area.areaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping("/areas")
    public List<Area> retriveAllArea() {

        return areaService.retrieveAllAreas();
    }
}
