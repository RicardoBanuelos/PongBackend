package com.pong.mappers;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.entities.User;
import com.pong.exceptions.AppException;
import com.pong.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MatchMapper {

    private final UserRepository userRepository;

    public MatchDto toMatchDto(Match match) {
        if(match == null)
            return null;

        MatchDto.MatchDtoBuilder matchDto = MatchDto.builder();
        matchDto.id(match.getId());
        matchDto.date(match.getDate());
        matchDto.usernameOne(match.getUserOne().getUsername());
        matchDto.usernameTwo(match.getUserTwo().getUsername());
        matchDto.playerOneScore(match.getPlayerOneScore());
        matchDto.playerTwoScore(match.getPlayerTwoScore());

        return matchDto.build();
    }

    public Match createMatch(NewMatchDto newMatchDto)
    {
        if(newMatchDto == null)
            return null;

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

        return match;
    }
}
