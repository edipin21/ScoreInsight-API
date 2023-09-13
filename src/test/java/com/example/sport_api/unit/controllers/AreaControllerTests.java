package com.example.sport_api.unit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.sport_api.controllers.soccer.AreaController;
import com.example.sport_api.models.sport.AreaDto;
import com.example.sport_api.services.soccer.AreaService;
import com.example.sport_api.util.ResponseUtil;

public class AreaControllerTests {

    @InjectMocks
    private AreaController areaController;

    @Mock
    private AreaService areaService;

    @BeforeAll
    public static void staticSetUp() {
        Mockito.mockStatic(ResponseUtil.class);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllAreaSuccess() {

        String apiKey = "key";

        AreaDto area1 = new AreaDto(1, "test1", "test1");
        AreaDto area2 = new AreaDto(2, "test2", "test2");

        List<AreaDto> mockAreaDtos = new ArrayList<>();
        mockAreaDtos.add(area1);
        mockAreaDtos.add(area2);

        when(areaService.getAllAreasWithCompetitions()).thenReturn(mockAreaDtos);

        ResponseEntity<List<AreaDto>> expectedResponse = ResponseEntity.ok(mockAreaDtos);

        when(ResponseUtil.createOkResponse(mockAreaDtos))
                .thenReturn(expectedResponse);

        ResponseEntity<?> result = areaController.retriveAllArea(apiKey);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(expectedResponse, result);

    }

    @Test
    public void testRetrieveAllAreaDataAccessException() {
        String apiKey = "key";

        when(areaService.getAllAreasWithCompetitions())
                .thenThrow(new DataAccessException("Mock Exception") {
                });

        ResponseEntity<?> expectedErrorResponse = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Mock Exception");

        when(ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Mock Exception"))
                .thenReturn((ResponseEntity<Map<String, Object>>) expectedErrorResponse);

        ResponseEntity<?> result = areaController.retriveAllArea(apiKey);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());

        assertSame(expectedErrorResponse, result);
    }
}
