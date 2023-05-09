// package com.example.sport_api.services;

// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.sport_api.models.Competition;
// import com.example.sport_api.models.CompetitionDetail;
// import com.example.sport_api.repositories.CompetitionDetailRepository;
// import com.example.sport_api.repositories.CompetitionRepository;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;
// import jakarta.transaction.Transactional;

// @Service
// public class CompetitionDetailService {

// @PersistenceContext
// private EntityManager entityManager;

// @Autowired
// private CompetitionDetailRepository competitionDetailRepository;
// @Autowired
// private CompetitionRepository competitionRepository;

// public CompetitionDetailService(CompetitionDetailRepository
// competitionDetailRepository) {
// this.competitionDetailRepository = competitionDetailRepository;
// }

// public CompetitionDetail retrieveCompetitionDetailById(int
// competitionDetailId) {

// return competitionDetailRepository.findById(competitionDetailId).get();
// }

// @Transactional
// public void addCompetitionDetail(CompetitionDetail competitionDetail) {
// // check if the parent entity exists in the database
// // Competition existingCompetition = entityManager.find(Competition.class,
// // competitionDetail.getCompetitionId());
// // System.out.println("im here1!!!!!!!!!!!!!!!!!!!!!");
// // if (existingCompetition != null) {
// // System.out.println("im here2!!!!!!!!!!!!!!!!!!!!!");

// // entityManager.merge(competitionDetail);
// // // competitionDetailRepository.save(competitionDetail);

// // }
// // CompetitionDetail competitionDetail1 = new CompetitionDetail(1, "pini",
// // "mini", "male", "theType", "f", "ewr",
// // null, null, null, null, null);

// Optional<CompetitionDetail> competition = competitionDetailRepository
// .findById(competitionDetail.getCompetitionId());

// Optional<Competition> competition1 = competitionRepository
// .findById(competitionDetail.getCompetitionId());

// if (competition1.isPresent()) {
// System.out.println("the competition is present in the database");

// System.out.println(competition1.get().toString());

// }

// if (competition.isPresent()) {
// CompetitionDetail competitionDetail2 = competition.get();
// System.out.println(competitionDetail.getCurrentSeason());
// System.out.println("im here1!!!!!!!!!!!!!!!!!!!!!");
// competitionDetail2.setCurrentSeason(competitionDetail.getCurrentSeason());
// // entityManager.remove(competition);

// // competition.get().setCurrentSeason(competitionDetail.getCurrentSeason());
// // competitionDetailRepository.save(competitionDetail2);
// System.out.println("im here2!!!!!!!!!!!!!!!!!!!!!");

// } else if (!competition.isPresent()) {
// System.out.println("not get to the if statment !");
// entityManager.merge(competitionDetail);
// }
// // competitionDetailRepository.save(competitionDetail);
// // competitionDetailRepository.save(competitionDetail);
// }

// // @Transactional
// // public void addCompetitionDetail(CompetitionDetail competitionDetail) {
// // // check if the parent entity exists in the database
// // Competition existingCompetition = entityManager.find(Competition.class,
// // competitionDetail.getCompetitionId());
// // if (existingCompetition != null) {
// // // check if the child entity already exists in the database
// // CompetitionDetail existingCompetitionDetail =
// // entityManager.find(CompetitionDetail.class,
// // competitionDetail.getCompetitionId());
// // if (existingCompetitionDetail == null) {
// // entityManager.merge(competitionDetail);
// // } else {
// // // update the existing entity with the new data
// //
// existingCompetitionDetail.setCurrentSeason(competitionDetail.getCurrentSeason());
// // existingCompetitionDetail.setTeams(competitionDetail.getTeams());
// // existingCompetitionDetail.setGames(competitionDetail.getGames());
// // entityManager.merge(existingCompetitionDetail);
// // }
// // }
// // }

// }
