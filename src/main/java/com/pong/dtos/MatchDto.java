package com.pong.dtos;

import java.util.Date;
import com.pong.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MatchDto {
    Long id;
    Date date;
    String usernameOne;
    String usernameTwo;
    int playerOneScore;
    int playerTwoScore;
}
