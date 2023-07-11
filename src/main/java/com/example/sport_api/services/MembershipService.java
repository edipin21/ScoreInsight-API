package com.example.sport_api.services;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.models.Membership;
import com.example.sport_api.repositories.MembershipRepository;

@Service
public class MembershipService {

    private static final Logger logger = LogManager.getLogger(MembershipService.class);

    @Autowired
    private MembershipRepository membershipRepository;

    public List<Membership> getMembershipsByCompetition(Integer Competition) {
        try {
            List<Membership> memberships = membershipRepository.findByCompetitionId(Competition);

            return memberships;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while retrieving memberships  " + e.getMessage());
            throw e;
        }

    }

    public List<Membership> getRecentlyChangedMemberships(Integer competitionId, Integer days) {

        try {
            List<Membership> memberships = membershipRepository.findRecentMembershipsByCompetitionId(competitionId,
                    days);
            return memberships;

        } catch (DataAccessException e) {
            logger.error("A data access error occurred while validating competition ID: " + e.getMessage());
            throw e;
        }
    }

    public List<Membership> getMembershipsByCompetitionAndTeam(Integer Competition, Integer team) {
        try {
            List<Membership> memberships = membershipRepository.findByCompetitionIdAndTeamId(Competition, team);

            return memberships;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while retrieving memberships  " + e.getMessage());
            throw e;
        }

    }

}
