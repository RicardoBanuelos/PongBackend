package com.pong.dtos;

import com.pong.entities.User;

import java.util.Date;

public record NewMatchDto (
        Date date,
        String playerOneUsername,
        String playerTwoUsername,
        int playerOneScore,
        int playerTwoScore
) {}
