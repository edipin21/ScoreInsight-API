package com.example.sport_api.services.soccer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.sport_api.constants.ExternalSoccerApiEndpoints;
import com.example.sport_api.exceptions.NotFoundException;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.CompetitionDto;
import com.example.sport_api.repositories.soccer.CompetitionRepository;
import com.example.sport_api.repositories.soccer.GameRepository;
import com.example.sport_api.repositories.soccer.PlayerRepository;
import com.example.sport_api.repositories.soccer.TeamDetailRepository;
import com.example.sport_api.util.ExternalApiDataFetcherUtil;
import com.example.sport_api.util.TimeMeasurementUtil;
import com.example.sport_api.util.soccerUtil.CompetitionDtoUtils;
import com.example.sport_api.util.soccerUtil.CompetitionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class CompetitionService {

    private static final Logger logger = LogManager.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamDetailRepository teamDetailRepository;

    public void syncCompetitionsFromExternalApi() throws JsonProcessingException {

        try {
            TimeMeasurementUtil.startTimer();
            TypeReference<List<Competition>> competitionTypeRef = new TypeReference<>() {
            };

            List<Competition> competitions = ExternalApiDataFetcherUtil
                    .fetchListDataFromExternalApi(ExternalSoccerApiEndpoints.COMPETITIONS_RESOURCE_URL,
                            competitionTypeRef);

            CompetitionUtils.setCompetitionsIdToRoundsParallel(competitions);
            CompetitionUtils.saveCompetitionsToDB(competitions, competitionRepository);
            TimeMeasurementUtil.timeTaken();

        } catch (Exception e) {
            CompetitionUtils.handleExternalApiException(e);
            throw e;
        }

    }

    public void syncCompetitionsFixturesFromExternalApi() throws JsonProcessingException, IOException {

        try {

            TimeMeasurementUtil.startTimer();
            List<Integer> competitionIntegers = getSortedCompetitionIds();

            for (Integer competitionId : competitionIntegers) {

                Competition competition = ExternalApiDataFetcherUtil.fetchDataFromExternalApi(
                        ExternalSoccerApiEndpoints.COMPETITION_FIXTURES_RESOURCE_URL + competitionId,
                        Competition.class);

                CompetitionUtils.saveOrUpdateCompetitionDataToDB(competition, playerRepository, competitionRepository,
                        gameRepository, teamDetailRepository);

            }
            TimeMeasurementUtil.timeTaken();
        } catch (Exception e) {
            CompetitionUtils.handleExternalApiException(e);
            throw e;
        }
    }

    public void syncCompetitionsFixturesFromExternalApiAsyncParallel() throws JsonProcessingException, IOException {
        try {
            TimeMeasurementUtil.startTimer();
            List<Integer> competitionIds = getSortedCompetitionIds();
            List<String> competitionFixtureJsons = new ArrayList<>();
            List<Competition> competitionFixtures = new ArrayList<>();

            competitionFixtureJsons = CompetitionUtils.getCompetitionFixtureJsonsParallel(competitionIds);

            competitionFixtures = CompetitionUtils
                    .deserializeCompetitionFixturesParallel(competitionFixtureJsons);

            List<CompletableFuture<Void>> competitionProcessingFutures = competitionFixtures.parallelStream()
                    .map(competition -> {
                        return CompetitionUtils.processCompetitionFixtureAndSaveToDBAsync(
                                competition, playerRepository, competitionRepository, teamDetailRepository,
                                gameRepository);
                    }).collect(Collectors.toList());

            CompletableFuture<Void> allCompetitionsProcessingFutures = CompletableFuture.allOf(
                    competitionProcessingFutures.toArray(new CompletableFuture[0]));

            allCompetitionsProcessingFutures.join();

            TimeMeasurementUtil.timeTaken();
        } catch (Exception e) {
            CompetitionUtils.handleExternalApiException(e);
            throw e;
        }
    }

    public List<CompetitionDto> getAllCompetitionsDtos() {

        try {
            List<Competition> competitions = competitionRepository.findAll();

            return CompetitionDtoUtils.mapCompetitionsToDtos(competitions);

        } catch (DataAccessException e) {
            logger.error("A data access error occurred while retrieving competitions: " + e.getMessage());
            throw e;
        }

    }

    public Competition getCompetitionById(int competitionId) {
        try {
            Optional<Competition> competition = competitionRepository.findById(competitionId);

            if (competition.isPresent()) {
                return competition.get();
            } else {
                throw new NotFoundException("Competition not found");
            }
        } catch (NotFoundException e) {
            logger.error("Competition not found for ID: " + competitionId, e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred: " + e.getMessage());
            throw e;
        }

    }

    public boolean isCompetitionValid(Integer competitionId) {
        try {
            List<Integer> allCompetitionIds = competitionRepository.findAllCompetitionsNumbers();
            return allCompetitionIds.contains(competitionId);
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while validating competition ID: " + e.getMessage());
            throw e;
        }

    }

    public List<Integer> getSortedCompetitionIds() {
        try {
            List<Integer> competitionIds = competitionRepository.findAllCompetitionsNumbers();
            competitionIds.sort(null);
            return competitionIds;
        } catch (DataAccessException e) {
            logger.error("A data access error occurred while retrieving competitions ids: " + e.getMessage());
            throw e;
        }

    }

}
