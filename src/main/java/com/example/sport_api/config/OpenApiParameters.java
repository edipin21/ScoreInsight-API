package com.example.sport_api.config;

public class OpenApiParameters {

    public static final String COMPETITION_ID_DESCRIPTION = "An indication of a soccer competition/league. This value is the CompetitionId.Possible values include: 1,2...48 ";
    public static final String TEAM_ID_DESCRIPTION = "An indication of a soccer team. This value is the teamId.";
    public static final String GAME_ID_DESCRIPTION = "The GameID of a Soccer game. GameIDs can be found in the Games API. Valid entries are 702, 1274, etc.";
    public static final String DATE_FORMAT_DESCRIPTION = "The date in 'YYYY-MM-DD' format";
    public static final String DAYS_DESCRIPTION = "The number of days since memberships were updated. For example, if you pass 3, you'll receive all memberships that have been updated in the past 3 days. Valid entries are: 1, 2 ... 30";
    public static final String YEAR_DESCRITION = "Year of the season  \n" + "Examples: 2020, 2021, etc.";
    public static final String EVENT_ID_DESCRIPTION = "The EventId of the desired event/game for which to pull all betting markets (includes outcomes/bets).";

}