package com.example.sport_api.controllers;

import java.util.List;

import com.example.sport_api.models.sport.AreaDto;
import com.example.sport_api.services.AreaService;
import com.example.sport_api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaConrtoller {

    @Autowired
    private AreaService areaService;

    public AreaConrtoller(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("scores/areas")
    public ResponseEntity<List<AreaDto>> retriveAllArea() {

        try {
            List<AreaDto> areaDtos = areaService.getAllAreasWithCompetitions();
            return ResponseUtil.createOkResponse(areaDtos);
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }

    }

}
