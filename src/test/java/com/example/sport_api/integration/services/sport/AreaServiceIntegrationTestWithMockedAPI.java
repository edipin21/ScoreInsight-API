package com.example.sport_api.integration.services.sport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.services.soccer.AreaService;
import com.github.tomakehurst.wiremock.WireMockServer;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8080)
public class AreaServiceIntegrationTestWithMockedAPI {

    // @Autowired
    // private MockMvc mockMvc;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaService areaService;

    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        wireMockServer.start();
        wireMockServer.resetAll(); // Reset WireMock before each test
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.shutdown(); // Shutdown WireMock after each test
    }

    @Test
    public void testSyncAreasFromExternalApi_Success() throws Exception {
        // Configure WireMock to respond to external API requests
        wireMockServer.stubFor(get(urlEqualTo(ExternalSoccerApiEndpoints.AREAS_RESOURCE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\": 1, \"name\": \"Area1\"}]")));

        // Call the function that interacts with the external API
        areaService.syncAreasFromExternalApi();

        List<Area> savedAreas = areaRepository.findAll();

        assertEquals(savedAreas.get(0).getAreaId(), 1);
        // Assert the behavior or state of your application after the call
        // For example, you can check the database state or other assertions
    }
}
