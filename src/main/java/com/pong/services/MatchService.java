package com.pong.services;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.mappers.MatchMapper;
import com.pong.repositories.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public Page<MatchDto> getAllMatchesByUsername(String username, int page, int size) {
        Page<Match> matchesPage = matchRepository.findAllByUsername(username, PageRequest.of(page, size));
        return matchesPage.map(matchMapper::toMatchDto);
    }

    public List<Match> getAllMatchesByUsername(String username) {
        return matchRepository.findAllByUsername(username);
    }

    public MatchDto addMatch(NewMatchDto newMatchDto) {
        Match newMatch = matchMapper.createMatch(newMatchDto);
        Match savedMatch = matchRepository.save(newMatch);
        return matchMapper.toMatchDto(savedMatch);
    }
}

