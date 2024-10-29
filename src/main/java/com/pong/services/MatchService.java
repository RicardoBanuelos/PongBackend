package com.pong.services;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.mappers.MatchMapper;
import com.pong.repositories.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public List<MatchDto> getAllMatchesByUsername(String username) {
        List<Match> matches =  matchRepository.findAllByUsername(username);

        List<MatchDto> matchesDto = new ArrayList<>();
        for(Match match : matches) {
            matchesDto.add(matchMapper.toMatchDto(match));
        }

        return matchesDto;
    }

    public MatchDto addMatch(NewMatchDto newMatchDto) {
        Match newMatch = matchMapper.createMatch(newMatchDto);
        Match savedMatch = matchRepository.save(newMatch);

        return matchMapper.toMatchDto(savedMatch);
    }
}

