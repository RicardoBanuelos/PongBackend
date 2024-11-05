package com.pong.repositories;

import com.pong.entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("SELECT m FROM Match m WHERE m.userOne.username = :username OR m.userTwo.username = :username ORDER BY m.date DESC")
    Page<Match> findAllByUsername(String username, Pageable pageable);

    @Query("SELECT m FROM Match m WHERE m.userOne.username = :username OR m.userTwo.username = :username")
    List<Match> findAllByUsername(String username);
}
