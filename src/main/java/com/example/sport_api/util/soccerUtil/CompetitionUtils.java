package com.example.sport_api.util.soccerUtil;

import java.util.ArrayList;
import java.util.List;

import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.Game;
import com.example.sport_api.models.sport.Round;
import com.example.sport_api.models.sport.Season;
import com.example.sport_api.models.sport.TeamDetail;

public class CompetitionUtils {

    public static void setCompetitionsIdToRounds(List<Competition> competitions) {

        competitions.forEach(competition -> {
            List<Season> seasons = competition.getSeasons();
            seasons.forEach(season -> {
                List<Round> rounds = season.getRounds();
                rounds.forEach(round -> round.setCompetitionId(competition.getCompetitionId()));
            });
        });

    }

    public static List<Game> setCompetitionIdToGames(Competition competition) {

        List<Game> theGames = competition.getGames();
        if (!theGames.isEmpty())
            theGames.forEach(game -> game.setCompetition(competition));

        return theGames;

    }

    public static List<TeamDetail> setCompetitionIdToTeamsDetail(Competition competition) {

        List<TeamDetail> theTeams = competition.getTeams();

        if (!theTeams.isEmpty()) {
            List<Competition> competitions = new ArrayList<>();
            competitions.add(competition);
            theTeams.forEach(team -> team.setCompetition(competitions));
        }
        return theTeams;

    }
}
