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

    @GetMapping("/matches/{username}")
    public ResponseEntity<List<MatchDto>> getMatchesByUsername(@PathVariable String username) {
        List<MatchDto> matches = matchService.getAllMatchesByUsername(username);
        return ResponseEntity.ok(matches);
    }

    @PostMapping("/add_match")
    public ResponseEntity<MatchDto> addMatch(@RequestBody NewMatchDto newMatchDto) {
        MatchDto matchDto = matchService.addMatch(newMatchDto);
        return ResponseEntity.created(URI.create("/matches/" + matchDto.getId())).body(matchDto);
    }
}
