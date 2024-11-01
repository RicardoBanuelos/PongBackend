package com.pong.dtos;

import java.util.Date;
import java.util.UUID;

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
