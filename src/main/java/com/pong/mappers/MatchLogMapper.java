package com.pong.mappers;

import com.pong.dtos.MatchLogDto;
import com.pong.entities.Match;
import com.pong.entities.MatchLog;
import com.pong.entities.User;
import com.pong.exceptions.AppException;
import com.pong.repositories.MatchRepository;
import com.pong.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MatchLogMapper {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public List<MatchLog> createMatchLogs(List<MatchLogDto> matchLogsDto) {
        List<MatchLog> matchLogs = new ArrayList<>();

        if(matchLogsDto.isEmpty())
            return matchLogs;

        Optional<Match> match = matchRepository.findById(matchLogsDto.get(0).getMatchId());
        if(match.isEmpty())
            throw new AppException("Invalid match ID", HttpStatus.NOT_FOUND);

        for(MatchLogDto matchLogDto : matchLogsDto) {
            MatchLog matchLog = new MatchLog();
            matchLog.setMatch(match.get());
            matchLog.setCreatedAt(matchLogDto.getCreatedAt());

            Optional<User> user = userRepository.findByUsername(matchLogDto.getPlayerUsername());
            if(user.isEmpty())
                throw new AppException("Invalid username", HttpStatus.NOT_FOUND);

            matchLog.setUser(user.get());

            matchLogs.add(matchLog);
        }

        return matchLogs;
    }

    public MatchLogDto toMatchLogDto(MatchLog matchLog) {
        MatchLogDto matchLogDto = new MatchLogDto();
        matchLogDto.setCreatedAt(matchLog.getCreatedAt());
        matchLogDto.setMatchId(matchLog.getMatch().getId());
        matchLogDto.setPlayerUsername(matchLog.getUser().getUsername());

        return matchLogDto;
    }

    public List<MatchLogDto> toMatchLogsDto(List<MatchLog> matchLogs) {
        List<MatchLogDto> matchLogsDto = new ArrayList<>();

        for(MatchLog matchLog : matchLogs) {
            MatchLogDto matchLogDto = new MatchLogDto();
            matchLogDto.setCreatedAt(matchLog.getCreatedAt());
            matchLogDto.setMatchId(matchLog.getMatch().getId());
            matchLogDto.setPlayerUsername(matchLog.getUser().getUsername());
            matchLogsDto.add(matchLogDto);
        }


        return matchLogsDto;
    }
}
