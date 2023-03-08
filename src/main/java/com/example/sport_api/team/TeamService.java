package com.example.sport_api.team;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team retrieveTeamById(int teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        if (optionalTeam.isEmpty()) {
            return null;
        }

        return optionalTeam.get();
    }

    // public List<Team> addAllTeams() {
    // RestTemplate rTemplate = new RestTemplate();

    // String resourceUrl =
    // "https://api.sportsdata.io/v3/soccer/scores/json/Teams?key=2db931a014ad4b1e90d3f614e7927f11";

    // ResponseEntity<String> response = rTemplate.getForEntity(resourceUrl,
    // String.class);
    // String s = response.getBody();

    // // String n = s.substring(0, 1).concat("\"Team\":").concat(s.substring(1));
    // // Reader reder = new StringReader(s);
    // ObjectMapper objectMapper = new ObjectMapper();
    // // objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
    // false);

    // objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
    // objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    // List<Team> teams = new ArrayList<>();
    // // String jsonString =
    // // "[{\"TeamId\":111,\"AreaId\":68},{\"TeamId\":112,\"AreaId\":25}]";
    // try {

    // teams = objectMapper.readValue(s, new TypeReference<List<Team>>() {
    // });
    // } catch (JsonProcessingException e) {
    // System.out.println(e.getLocalizedMessage());

    // System.out.println("!!!!!!" + e.getMessage());

    // }
    // // System.out.println(s);
    // System.out.println(teams.get(0));

    // for (Team team : teams) {
    // // System.out.println(team);
    // teamRepository.save(team);

    // }

    // return teamRepository.findAll();

    // }

    public List<Team> retrieveAllTeams() {
        return teamRepository.findAll();
    }

}
