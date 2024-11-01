package com.pong.services;

import com.pong.dtos.MatchLogDto;
import com.pong.entities.MatchLog;
import com.pong.mappers.MatchLogMapper;
import com.pong.repositories.MatchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchLogService {
    private final MatchLogRepository matchLogRepository;
    private final MatchLogMapper matchLogMapper;

    public List<MatchLogDto> getAllByMatchId(Long matchId) {
        List<MatchLog> matchLogs = matchLogRepository.getAllByMatchId(matchId);
        return matchLogMapper.toMatchLogDto(matchLogs);
    }

    public List<MatchLogDto> addMatchLogs(List<MatchLogDto> matchLogsDto) {
        List<MatchLog> matchLogs = matchLogMapper.createMatchLogs(matchLogsDto);

        for(MatchLog matchLog : matchLogs)
        {
            matchLogRepository.save(matchLog);
        }

        return matchLogsDto;
    }
}
