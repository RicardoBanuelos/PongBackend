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
@Table(name="match_log")
public class MatchLog {
    @Id
    private Long id;
    @Column(name="createAt", nullable = false)
    private Date createdAt;
    @Column(name="player_id", nullable = false)
    private Long player_id;
}
