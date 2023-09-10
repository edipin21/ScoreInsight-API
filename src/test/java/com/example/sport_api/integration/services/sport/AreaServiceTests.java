package com.example.sport_api.integration.services.sport;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.services.soccer.AreaService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AreaServiceTests {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void testSyncAreasFromExternalApi_Success() {
        // Call the function
        areaService.syncAreasFromExternalApi();

        // Query the test database and assert that the areas were saved correctly
        List<Area> savedAreas = areaRepository.findAll();

        assertNotNull(savedAreas);

    }
}
