package com.pong.controllers;

import com.pong.dtos.MatchLogDto;
import com.pong.services.MatchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchLogController {
    private final MatchLogService matchLogService;

    @GetMapping("/matches/logs")
    public ResponseEntity<Page<MatchLogDto>> getAllMatchLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        Page<MatchLogDto> matchLogDtos = matchLogService.getMatchLogs(page, size);
        return ResponseEntity.ok(matchLogDtos);
    }

    @PostMapping("/matches/logs")
    public ResponseEntity<String> addMatchLogs(@RequestBody List<MatchLogDto> matchLogsDto) {
        matchLogService.addMatchLogs(matchLogsDto);
        return ResponseEntity.ok("Match Logs Added Successfully");
    }

    @GetMapping("/matches/logs/{matchId}")
    public ResponseEntity<List<MatchLogDto>> getMatchLogsByMatchId(
            @PathVariable Long matchId
    ) {
        return ResponseEntity.ok(matchLogService.getAllByMatchId(matchId));
    }
}
