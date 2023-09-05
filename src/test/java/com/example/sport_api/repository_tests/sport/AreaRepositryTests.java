package com.example.sport_api.repository_tests.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.repositories.soccer.AreaRepository;

@DataJpaTest
public class AreaRepositryTests {

    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void testAreaMapping() {

        // Create a new Area object with sample data
        Area area = new Area();
        area.setAreaId(1);
        area.setCountryCode("us");
        area.setName("test area");

        // Save the Area object to the repository
        areaRepository.save(area);
        // Retrieve the Area object from the repository using its ID
        // and assign it to the savedArea variable
        Area savedArea = areaRepository.findById(1).orElse(null);

        // Perform assertions to verify that the retrieved data matches the expected
        // values
        assertEquals("test area", savedArea.getName());
        assertEquals("us", savedArea.getCountryCode());
    }

    @Test
    public void testAreaRelationships() {

        // Create a new Area object with sample data
        Area area = new Area();
        area.setAreaId(1);
        area.setName("test area");

        // Create related entity
        Competition competition = new Competition();
        competition.setName("test competition");
        competition.setArea(area);
        area.setCompetitions(new ArrayList<>());
        area.getCompetitions().add(competition);

        areaRepository.save(area);
        // Retrieve the saved Area entity
        Area savedArea = areaRepository.findById(1).orElse(null);

        // Verify that the relationships are correctly established
        assertEquals("test competition",
                savedArea.getCompetitions().get(0).getName());

    }

}
