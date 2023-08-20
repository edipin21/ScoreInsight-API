package com.example.sport_api.services.soccer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.sport.TeamDetail;
import com.example.sport_api.repositories.soccer.TeamDetailRepository;

@Service
public class TeamDetailService {

    private static final Logger logger = LogManager.getLogger(TeamDetailService.class);

    @Autowired
    private TeamDetailRepository teamDetailRepository;

    public List<TeamDetail> retriveAllTeamsDetails() {
        try {
            return teamDetailRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }
    }
}
