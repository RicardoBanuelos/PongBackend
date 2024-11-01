package com.pong.repositories;

import com.pong.entities.MatchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchLogRepository extends JpaRepository<MatchLog, Long> {
    @Query("SELECT ml FROM MatchLog ml WHERE ml.match.id = :matchId")
    List<MatchLog> getAllByMatchId(Long matchId);
}
