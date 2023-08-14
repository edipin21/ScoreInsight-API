package com.example.sport_api.util.soccerUtil;

import java.util.List;
import java.util.stream.Collectors;
import com.example.sport_api.models.sport.Competition;
import com.example.sport_api.models.sport.CompetitionDto;

public class CompetitionDtoUtils {

    public static List<CompetitionDto> mapCompetitionsToDtos(List<Competition> competitions) {
        return competitions.stream()
                .map(CompetitionDtoUtils::mapCompetitionToDto)
                .collect(Collectors.toList());
    }

    private static CompetitionDto mapCompetitionToDto(Competition competition) {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setAreaId(competition.getAreaId());
        competitionDto.setAreaName(competition.getAreaName());
        competitionDto.setCompetitionId(competition.getCompetitionId());
        competitionDto.setFormat(competition.getFormat());
        competitionDto.setGender(competition.getGender());
        competitionDto.setName(competition.getName());
        competitionDto.setSeasons(competition.getSeasons());
        competitionDto.setStringKey(competition.getStringKey());
        competitionDto.setType(competition.getType());
        return competitionDto;
    }

}
