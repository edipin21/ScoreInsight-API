package com.example.sport_api.util.soccerUtil;

import java.util.List;

import com.example.sport_api.models.sport.Round;

public class RoundUtils {

    public static void setStandingsAndTeamSeasonsToRounds(List<Round> rounds, List<Round> standingsRounds,
            List<Round> teamSeasonRounds) {

        if (rounds == null || standingsRounds == null || teamSeasonRounds == null) {
            throw new IllegalArgumentException("Lists cannot be null");
        }

        if (rounds.size() != standingsRounds.size() || rounds.size() != teamSeasonRounds.size()) {
            throw new IllegalArgumentException("Lists must have the same size");
        }

        for (int i = 0; i < rounds.size(); i++) {
            Round round = rounds.get(i);
            Round standingsRound = standingsRounds.get(i);
            Round teamSeasonRound = teamSeasonRounds.get(i);

            copyStandingsAndTeamSeasonsToRound(round, standingsRound, teamSeasonRound);
        }
    }

    public static void setCompetitionToRounds(List<Round> rounds, Integer competition) {
        rounds.forEach(round -> round.setCompetitionId(competition));
    }

    private static void copyStandingsAndTeamSeasonsToRound(Round round, Round standingsRound, Round teamSeasonRound) {
        round.setStandings(standingsRound.getStandings());
        round.setTeamSeasons(teamSeasonRound.getTeamSeasons());
    }

}
