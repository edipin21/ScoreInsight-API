package com.example.sport_api.util.soccerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.sport_api.models.sport.Membership;
import com.example.sport_api.repositories.soccer.MembershipRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MembershipUtils {

    private static final Logger logger = LogManager.getLogger(MembershipUtils.class);

    public static void setCompetitionIdToMemberships(List<Membership> activMemberships, Integer competitionId) {

        activMemberships.forEach(membership -> membership.setCompetitionId(competitionId));
    }

    public static void setCompetitionIdToMembershipsParallel(List<Membership> activMemberships, Integer competitionId) {

        activMemberships.parallelStream().forEach(membership -> membership.setCompetitionId(competitionId));
    }

    public static Map<Integer, String> getMembershipJsonsByCompetitionParallel(
            List<Integer> competitionIds, Function<Integer, String> endpointConstructor) {

        Map<Integer, String> membershipJsons = new HashMap<>();

        competitionIds.parallelStream().forEach(competitionId -> {
            String endpoint = endpointConstructor.apply(competitionId);
            String membershipJson = ExternalApiDataFetcherUtil.fetchData(endpoint);
            membershipJsons.put(competitionId, membershipJson);
        });

        return membershipJsons;
    }

    public static List<Membership> deserializeMembership(String membershipJson, Integer competitionId) {
        try {
            TypeReference<List<Membership>> membershipTypeRef = new TypeReference<>() {
            };

            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            List<Membership> membership = objectMapper.readValue(membershipJson, membershipTypeRef);
            setCompetitionIdToMemberships(membership, competitionId);
            return membership;

        } catch (JsonProcessingException e) {
            logger.error("Error occurred while deserializeing membership");
            return null;
        }
    }

    public static List<List<Membership>> deserializeMembershipsParallel(Map<Integer, String> activeMembershipJsons) {
        List<List<Membership>> activeMemberships = new ArrayList<>();

        activeMembershipJsons.entrySet().parallelStream().forEach(entry -> {

            int competitionId = entry.getKey();
            String membershipJson = entry.getValue();

            activeMemberships.add(deserializeMembership(membershipJson, competitionId));
        }

        );

        return activeMemberships;
    }

    public static void handleExternalApiException(Exception e) {
        ExternalApiDataFetcherUtil.handleException(e);
        throw new RuntimeException("Failed to synchronize Memberships from external API", e);
    }

    public static void updateCompetitionId(List<Membership> memberships, Integer competitionId) {
        memberships.forEach(membership -> membership.setCompetitionId(competitionId));
    }

    public static void saveMemberships(List<Membership> memberships, MembershipRepository membershipRepository) {
        if (!memberships.isEmpty()) {
            membershipRepository.saveAll(memberships);
        }
    }
}
