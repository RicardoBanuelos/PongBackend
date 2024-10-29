package com.pong.dtos;

import com.pong.entities.User;

import java.util.Date;

public record NewMatchDto (
        Date date,
        String usernameOne,
        String usernameTwo,
        int playerOneScore,
        int playerTwoScore
) {}
