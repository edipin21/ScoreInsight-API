package com.example.sport_api.services.soccer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.models.sport.Membership;
import com.example.sport_api.repositories.soccer.MembershipRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.TimeMeasurementUtil;
import com.example.sport_api.util.soccerUtil.MembershipUtils;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class MembershipService {

    private static final Logger logger = LogManager.getLogger(MembershipService.class);

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private CompetitionService competitionService;

    public void syncActiveMembershipsFromExternalApi() {

        try {
            List<Integer> competitioIntegers = competitionService.getSortedCompetitionIds();
            TypeReference<List<Membership>> membershipTypeRef = new TypeReference<>() {
            };

            for (Integer competitionId : competitioIntegers) {

                String apiUrl = ExternalSoccerApiEndpoints.ACTIVE_MEMBERSHIPS_RESOURCE_URL + competitionId;

                List<Membership> activeMemberships = ExternalApiDataFetcherUtil
                        .fetchListDataFromExternalApi(apiUrl, membershipTypeRef);

                MembershipUtils.setCompetitionIdToMemberships(activeMemberships, competitionId);

                MembershipUtils.saveMemberships(activeMemberships, membershipRepository);
            }

        } catch (Exception e) {
            MembershipUtils.handleExternalApiException(e);
            throw e;
        }

    }

    public void syncActiveMembershipsFromExternalApiParallel() {

        try {

            TimeMeasurementUtil.startTimer();
            List<Integer> competitionIds = competitionService.getSortedCompetitionIds();
            Map<Integer, String> activeMembershipJsons = new HashMap<>();
            List<List<Membership>> lstOfActiveMemberships = new ArrayList<>();

            Function<Integer, String> activeEndpointConstructor = competitionId -> ExternalSoccerApiEndpoints.ACTIVE_MEMBERSHIPS_RESOURCE_URL
                    + competitionId;

            activeMembershipJsons = MembershipUtils.getMembershipJsonsByCompetitionParallel(competitionIds,
                    activeEndpointConstructor);

            lstOfActiveMemberships = MembershipUtils.deserializeMembershipsParallel(activeMembershipJsons);

            lstOfActiveMemberships.forEach(activeMemberships -> {
                MembershipUtils.saveMemberships(activeMemberships, membershipRepository);
            });

            TimeMeasurementUtil.timeTaken();

        } catch (Exception e) {
            MembershipUtils.handleExternalApiException(e);
            throw e;
        }
    }

    public void syncRecentlyChangedMembershipsFromExternalApi() {

        try {
            List<Membership> recentlyChangedMemberships = new ArrayList<>();
            List<Integer> competitioIntegers = competitionService.getSortedCompetitionIds();
            int numOfDays = 30;

            TypeReference<List<Membership>> MembershipTypeRef = new TypeReference<>() {
            };

            for (Integer competitionId : competitioIntegers) {

                String apiUrl = ExternalSoccerApiEndpoints.RECENTLY_CHANGED_MEMBERSHIPS_RESOURCE_URL + competitionId
                        + "/"
                        + numOfDays;

                recentlyChangedMemberships = ExternalApiDataFetcherUtil.fetchListDataFromExternalApi(
                        apiUrl,
                        MembershipTypeRef);

                MembershipUtils.updateCompetitionId(recentlyChangedMemberships, competitionId);

                MembershipUtils.saveMemberships(recentlyChangedMemberships, membershipRepository);

            }
        } catch (Exception e) {
            MembershipUtils.handleExternalApiException(e);
            throw e;
        }
    }

    public void syncRecentlyChangedMembershipsFromExternalApiParallel() {

        try {
            final int NUM_OF_DAYS = 30;
            List<Integer> competitioIntegers = competitionService.getSortedCompetitionIds();
            Map<Integer, String> recentlyChangedMembershipJsons = new HashMap<>();
            List<List<Membership>> lstOfrecentlyChangedMemberships = new ArrayList<>();

            Function<Integer, String> recentlyChangedEndpointConstructor = competitionId -> ExternalSoccerApiEndpoints.RECENTLY_CHANGED_MEMBERSHIPS_RESOURCE_URL
                    + competitionId + "/" + NUM_OF_DAYS;

            recentlyChangedMembershipJsons = MembershipUtils.getMembershipJsonsByCompetitionParallel(competitioIntegers,
                    recentlyChangedEndpointConstructor);

            lstOfrecentlyChangedMemberships = MembershipUtils
                    .deserializeMembershipsParallel(recentlyChangedMembershipJsons);

            lstOfrecentlyChangedMemberships.forEach(recentlyChangedMemberships -> {
                MembershipUtils.saveMemberships(recentlyChangedMemberships, membershipRepository);
            });

        } catch (Exception e) {
            MembershipUtils.handleExternalApiException(e);
            throw e;
        }
    }

    public List<Membership> getMembershipsByCompetition(Integer CompetitionId) {
        try {
            List<Membership> memberships = membershipRepository.findByCompetitionId(CompetitionId);

            return memberships;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
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
