package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.services.soccer.AreaService;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AreaServiceTests {

    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private AreaService areaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllAreasSuccess() {
        // Arrange
        List<Area> mockAreas = new ArrayList<>();
        mockAreas.add(new Area(1, "test", "test"));
        when(areaRepository.findAll()).thenReturn(mockAreas);

        // Act
        List<Area> result = areaService.retrieveAllAreas();

        // Assert
        assertEquals(mockAreas, result);
    }

   @Test
    public void testRetrieveAllAreasDataAccessException() {
        // Arrange
        when(areaRepository.findAll()).thenThrow(new DataAccessException("Mock Exception") {});

        // Act & Assert
        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            areaService.retrieveAllAreas();
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

}