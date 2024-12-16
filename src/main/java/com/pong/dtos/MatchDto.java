package com.pong.dtos;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Date date;
    @JsonProperty("player_one_username")
    String playerOneUsername;
    @JsonProperty("player_two_username")
    String playerTwoUsername;
    @JsonProperty("player_one_score")
    int playerOneScore;
    @JsonProperty("player_two_score")
    int playerTwoScore;
}
