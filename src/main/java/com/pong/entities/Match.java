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
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_one_id", nullable = false)
    private User userOne;

    @ManyToOne
    @JoinColumn(name="user_two_id", nullable = false)
    private User userTwo;

    @Column(name="user_one_score", nullable = false)
    private int userOneScore;

    @Column(name="user_two_score", nullable = false)
    private int userTwoScore;
}
