package com.example.sport_api.team;

public class TeamControllerJpa {

    private TeamRepository teamRepository;

    public TeamControllerJpa(TeamRepository teamRepository) {
        super();
        this.teamRepository = teamRepository;
    }

}
