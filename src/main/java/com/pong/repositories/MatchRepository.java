package com.pong.repositories;

import com.pong.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("SELECT m FROM Match m WHERE m.userOne.id = :userId OR m.userTwo.id = :userId")
    List<Match> findAllByUserId(Long userId);

    @Query("SELECT m FROM Match m WHERE m.userOne.username = :username OR m.userTwo.username = :username")
    List<Match> findAllByUsername(String username);
}
