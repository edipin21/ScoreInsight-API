package com.example.sport_api.unit.services.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoSession;
import org.mockito.Mock.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.repositories.soccer.AreaRepository;
import com.example.sport_api.services.soccer.AreaService;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.soccerUtil.AreaUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@SpringBootTest
@ActiveProfiles("test")
public class AreaServiceTests {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaService areaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}