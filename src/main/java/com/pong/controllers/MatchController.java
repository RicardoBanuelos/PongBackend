package com.pong.controllers;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.entities.Match;
import com.pong.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.ReferenceQueue;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/matches")
    public ResponseEntity<List<MatchDto>> getMatches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<MatchDto> matchesDto = matchService.getMatches(page, size);
        return ResponseEntity.ok(matchesDto);
    }

    @PostMapping("/matches")
    public ResponseEntity<MatchDto> addMatch(@RequestBody NewMatchDto newMatchDto) {
        MatchDto matchDto = matchService.addMatch(newMatchDto);
        return ResponseEntity.created(URI.create("/matches/" + matchDto.getId())).body(matchDto);
    }

    @GetMapping("/matches/{id}")
    public ResponseEntity<Page<MatchDto>> getMatchesById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Page<MatchDto> matchesDto = matchService.getMatchesById(id, page, size);
        return ResponseEntity.ok(matchesDto);
    }

    @GetMapping("/matches/username/{username}")
    public ResponseEntity<Page<MatchDto>> getMatchesByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Page<MatchDto> matchesDto = matchService.getMatchesByUsername(username, page, size);
        return ResponseEntity.ok(matchesDto);
    }
}
