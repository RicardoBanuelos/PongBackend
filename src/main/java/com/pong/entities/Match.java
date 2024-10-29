package com.pong.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_one", nullable = false)
    private User userOne;

    @ManyToOne
    @JoinColumn(name="user_two", nullable = false)
    private User userTwo;

    @Column(name="player_one_score", nullable = false)
    private int playerOneScore;

    @Column(name="player_two_score", nullable = false)
    private int playerTwoScore;
}
