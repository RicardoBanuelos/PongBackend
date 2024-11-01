package com.pong.mappers;

import com.pong.dtos.MatchLogDto;
import com.pong.entities.Match;
import com.pong.entities.MatchLog;
import com.pong.repositories.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MatchLogMapper {
    private final MatchRepository matchRepository;
    public List<MatchLog> createMatchLogs(List<MatchLogDto> matchLogsDto) {
        List<MatchLog> matchLogs = new ArrayList<>();

        if(matchLogsDto.isEmpty())
            return matchLogs;

        Optional<Match> match = matchRepository.findById(matchLogsDto.get(0).getMatchId());
        if(match.isEmpty())
            return matchLogs;

        for(MatchLogDto matchLogDto : matchLogsDto) {
            MatchLog matchLog = new MatchLog();
            matchLog.setMatch(match.get());
            matchLog.setCreatedAt(matchLogDto.getCreatedAt());
            matchLog.setPlayerUsername(matchLogDto.getPlayerUsername());
        }

        return matchLogs;
    }

    public List<MatchLogDto> toMatchLogDto(List<MatchLog> matchLogs) {
        List<MatchLogDto> matchLogsDto = new ArrayList<>();

        for(MatchLog matchLog : matchLogs) {
            MatchLogDto matchLogDto = new MatchLogDto();
            matchLogDto.setCreatedAt(matchLog.getCreatedAt());
            matchLogDto.setMatchId(matchLog.getMatch().getId());
            matchLogDto.setPlayerUsername(matchLog.getPlayerUsername());

            matchLogsDto.add(matchLogDto);
        }

        return matchLogsDto;
    }
}
