package com.pong.controllers;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/matches")
    public ResponseEntity<Page<MatchDto>> getMatchesByUsername(
            @RequestParam String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size) {
        Page<MatchDto> matchesDto = matchService.getAllMatchesByUsername(username, page, size);
        return ResponseEntity.ok(matchesDto);
    }

    @GetMapping("/matches/record")
    public ResponseEntity<List<Integer>> getRecordByUsername(
            @RequestParam String username
    ) {
        List<Match> matches = matchService.getAllMatchesByUsername(username);
        int wins = 0;
        int losses = 0;

        for(Match match: matches) {
            boolean playerOneWon = match.getPlayerOneScore() > match.getPlayerTwoScore();
            if(playerOneWon) {
                wins += Objects.equals(username, match.getUserOne().getUsername()) ? 1 :0;
                losses += Objects.equals(username, match.getUserTwo().getUsername()) ? 1 :0;
            }
            else {
                wins += Objects.equals(username, match.getUserTwo().getUsername()) ? 1 :0;
                losses += Objects.equals(username, match.getUserOne().getUsername()) ? 1 :0;
            }
        }

        List<Integer> record = new ArrayList<>();
        record.add(wins);
        record.add(losses);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/add_match")
    public ResponseEntity<MatchDto> addMatch(@RequestBody NewMatchDto newMatchDto) {
        MatchDto matchDto = matchService.addMatch(newMatchDto);
        return ResponseEntity.created(URI.create("/matches/" + matchDto.getId())).body(matchDto);
    }
}
