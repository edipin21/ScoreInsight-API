package com.example.sport_api.unit.repositories.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.Season;
import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.CompetitionRepository;

@Transactional
@DataJpaTest
public class CompetitionRepositoryTests {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Test
    public void testCompetitionMapping() {
        // Create a new Competition object with sample data
        Competition competition = new Competition();
        competition.setCompetitionId(1);
        competition.setGender("Male");
        competition.setName("test competition");

        // Save the competition object to the repository
        competitionRepository.save(competition);

        // Retrieve the competition object from the repository using its ID
        // and assign it to the retrievedCompetition variable
        Competition retrievedCompetition = competitionRepository.findById(competition.getCompetitionId()).orElse(null);

        // Perform assertions to verify that the retrieved data matches the expected
        // values
        assertEquals("test competition", retrievedCompetition.getName());
        assertEquals("Male", retrievedCompetition.getGender());

    }

    @Test
    public void testEntityRelationships() {
        // Create related entities (e.g., Area, Season, TeamDetail)
        Area area = new Area();
        area.setName("Test Area");

        Season season = new Season();
        season.setName("Test Season");

        TeamDetail teamDetail = new TeamDetail();
        teamDetail.setName("Test Team");

        // Create a Competition entity and associate it with related entities
        Competition competition = new Competition();
        competition.setName("Test Competition");
        competition.setArea(area);
        competition.setSeasons(new ArrayList<>());
        competition.getSeasons().add(season);
        competition.setTeams(new ArrayList<>());
        competition.getTeams().add(teamDetail);
        competition = competitionRepository.save(competition);

        // Retrieve the saved Competition entity
        Competition retrievedCompetition = competitionRepository.findById(competition.getCompetitionId()).orElse(null);

        // Verify that the relationships are correctly established
        assertNotNull(retrievedCompetition);
        assertEquals("Test Area", retrievedCompetition.getArea().getName());
        assertEquals(1, retrievedCompetition.getSeasons().size());
        assertEquals("Test Season", retrievedCompetition.getSeasons().get(0).getName());
        assertEquals(1, retrievedCompetition.getTeams().size());
        assertEquals("Test Team", retrievedCompetition.getTeams().get(0).getName());
    }

    // need to fix !!!!!!
    // @Test
    // public void testFindAllCompetitionsNumbers() {
    // // Create and save some Competition entities
    // Competition competition1 = new Competition();
    // competition1.setCompetitionId(1);
    // competitionRepository.save(competition1);

    // Competition competition2 = new Competition();
    // competition2.setCompetitionId(2);
    // competitionRepository.save(competition2);

    // // Call the custom query method
    // List<Integer> competitionIds =
    // competitionRepository.findAllCompetitionsNumbers();

    // // Assert that the result is not null and contains the expected number of
    // // entries
    // assertNotNull(competitionIds);
    // assertEquals(2, competitionIds.size());

    // }

    // @Test
    // public void testFindAllCompetitions() {
    // // Create and save some Competition entities (you can create more as needed)
    // Competition competition1 = new Competition();
    // competition1.setCompetitionId(1);
    // // Set properties for competition1
    // competitionRepository.save(competition1);

    // Competition competition2 = new Competition();
    // // Set properties for competition2
    // competition2.setCompetitionId(2);
    // competitionRepository.save(competition2);

    // // Call the custom query method
    // List<CompetitionDto> competitionDtos =
    // competitionRepository.findAllCompetitions();

    // // Assert that the result is not null and contains the expected number of
    // // entries
    // assertNotNull(competitionDtos);
    // assertEquals(2, competitionDtos.size());

    // // You can add more specific assertions based on your test data and query
    // // results
    // }

}
