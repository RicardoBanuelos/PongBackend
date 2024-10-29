package com.pong.services;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.entities.User;
import com.pong.exceptions.AppException;
import com.pong.repositories.MatchRepository;
import com.pong.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public List<MatchDto> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        List<MatchDto> matchesDto;
        return null;
    }

    public List<MatchDto> getAllMatchesById(Long id) {
        List<Match> matches = matchRepository.findAllByUserId(id);
        List<MatchDto> matchesDto;
        return null;
    }

    public List<MatchDto> getAllMatchesByUsername(String username) {
        List<Match> matches =  matchRepository.findAllByUsername(username);
        List<MatchDto> matchesDto;
        return null;
    }

    public MatchDto addMatch(NewMatchDto newMatchDto) {
        User userOne = userRepository.findByUsername(newMatchDto.usernameOne())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        User userTwo = userRepository.findByUsername(newMatchDto.usernameTwo())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        Match match = new Match();
        match.setDate(newMatchDto.date());
        match.setUserOne(userOne);
        match.setUserTwo(userTwo);
        match.setPlayerOneScore(newMatchDto.playerOneScore());
        match.setPlayerTwoScore(newMatchDto.playerTwoScore());

        Match savedMatch = matchRepository.save(match);

        MatchDto matchDto = new MatchDto();

        matchDto.setId(savedMatch.getId());
        matchDto.setDate(savedMatch.getDate());
        matchDto.setUsernameOne(savedMatch.getUserOne().getUsername());
        matchDto.setUsernameTwo(savedMatch.getUserTwo().getUsername());
        matchDto.setPlayerOneScore(savedMatch.getPlayerOneScore());
        matchDto.setPlayerTwoScore(savedMatch.getPlayerTwoScore());

        return matchDto;
    }
}

