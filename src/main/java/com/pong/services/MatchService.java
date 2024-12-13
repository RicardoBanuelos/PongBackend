package com.pong.services;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.entities.User;
import com.pong.exceptions.AppException;
import com.pong.mappers.MatchMapper;
import com.pong.repositories.MatchRepository;
import com.pong.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public List<MatchDto> getMatches(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Match> matches = matchRepository.findAll(pageable);
        List<MatchDto> matchesDto = new ArrayList<>();

        for(Match match : matches) {
            MatchDto matchDto = matchMapper.toMatchDto(match);
            matchesDto.add(matchDto);
        }

        return matchesDto;
    }

    public MatchDto addMatch(NewMatchDto newMatchDto) {
        User userOne = userRepository.findById(newMatchDto.userOneId())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        User userTwo = userRepository.findById(newMatchDto.userTwoId())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        Match newMatch = matchMapper.createMatch(newMatchDto, userOne, userTwo);
        Match savedMatch = matchRepository.save(newMatch);
        return matchMapper.toMatchDto(savedMatch);
    }

    public Page<MatchDto> getMatchesById(Long id, int page, int size) {
        Page<Match> matches = matchRepository.findAllById(id, PageRequest.of(page, size));
        return matches.map(matchMapper::toMatchDto);
    }

    public Page<MatchDto> getMatchesByUsername(String username, int page, int size) {
        Page<Match> matches = matchRepository.findAllByUsername(username, PageRequest.of(page, size));
        return matches.map(matchMapper::toMatchDto);
    }
}

