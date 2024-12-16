package com.pong.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pong.entities.User;

import java.util.Date;

public record NewMatchDto (
        Date date,
        @JsonProperty("user_one_id")
        Long userOneId,
        @JsonProperty("user_two_id")
        Long userTwoId,
        @JsonProperty("user_one_score")
        int userOneScore,
        @JsonProperty("user_two_score")
        int userTwoScore
) {}
