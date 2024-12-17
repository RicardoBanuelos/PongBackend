package com.pong.mappers;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MatchMapper {
    public MatchDto toMatchDto(Match match) {
        if(match == null)
            return null;

        MatchDto.MatchDtoBuilder matchDto = MatchDto.builder();
        matchDto.id(match.getId());
        matchDto.date(match.getDate());
        matchDto.playerOneUsername(match.getUserOne().getUsername());
        matchDto.playerTwoUsername(match.getUserTwo().getUsername());
        matchDto.playerOneScore(match.getUserOneScore());
        matchDto.playerTwoScore(match.getUserTwoScore());

        return matchDto.build();
    }

    public Match createMatch(NewMatchDto newMatchDto, User userOne, User userTwo)
    {
        if(newMatchDto == null)
            return null;

        Match match = new Match();
        match.setDate(newMatchDto.date());
        match.setUserOne(userOne);
        match.setUserTwo(userTwo);
        match.setUserOneScore(newMatchDto.userOneScore());
        match.setUserTwoScore(newMatchDto.userTwoScore());

        return match;
    }
}
