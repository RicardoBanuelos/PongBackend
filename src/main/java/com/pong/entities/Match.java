package com.pong.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="match")
public class Match {
    @Id
    private Long id;
    @Column(name="date", nullable = false)
    private Date date;
    @Column(name="winner", nullable = false)
    private Long winner;
    @Column(name="player_one_id", nullable = false)
    private Long playerOneId;
    @Column(name="player_two_id", nullable = false)
    private Long playerTwoId;
    @Column(name="player_one_score", nullable = false)
    private int playerOneScore;
    @Column(name="player_two_score", nullable = false)
    private int playerTwoScore;
}
