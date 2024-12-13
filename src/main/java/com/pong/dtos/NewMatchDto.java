package com.pong.dtos;

import com.pong.entities.User;

import java.util.Date;

public record NewMatchDto (
        Date date,
        Long userOneId,
        Long userTwoId,
        int userOneScore,
        int userTwoScore
) {}
