package com.example.sport_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sport_api.team.Team;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Team> fetchTeams() {

    }
}
