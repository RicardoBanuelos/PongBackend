package com.pong.controllers;

import com.pong.dtos.MatchDto;
import com.pong.dtos.NewMatchDto;
import com.pong.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/matches")
    public ResponseEntity<List<MatchDto>> getMatchesByUsername(
            @RequestParam String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size) {
        List<MatchDto> matchesDto = matchService.getAllMatchesByUsername(username, page, size);
        return ResponseEntity.ok(matchesDto);
    }

    @PostMapping("/add_match")
    public ResponseEntity<MatchDto> addMatch(@RequestBody NewMatchDto newMatchDto) {
        MatchDto matchDto = matchService.addMatch(newMatchDto);
        return ResponseEntity.created(URI.create("/matches/" + matchDto.getId())).body(matchDto);
    }
}
