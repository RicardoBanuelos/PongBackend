package com.pong.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pong.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record NewMatchDto (
        @NotEmpty
        Date date,
        @NotEmpty
        @JsonProperty("user_one_id")
        Long userOneId,
        @NotEmpty
        @JsonProperty("user_two_id")
        Long userTwoId,
        @NotEmpty
        @JsonProperty("user_one_score")
        int userOneScore,
        @NotEmpty
        @JsonProperty("user_two_score")
        int userTwoScore
) {}
