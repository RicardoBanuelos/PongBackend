package com.pong.controllers;

import com.pong.dtos.MatchLogDto;
import com.pong.services.MatchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchLogController {
    private final MatchLogService matchLogService;

    @GetMapping("/matches/logs/{match_id}")
    public ResponseEntity<List<MatchLogDto>> getAllByMatchId(@PathVariable Long match_id) {
        List<MatchLogDto> matchLogsDto = matchLogService.getAllByMatchId(match_id);
        return ResponseEntity.ok(matchLogsDto);
    }

    @PostMapping("/matches/logs/add_logs")
    public ResponseEntity<String> addMatchLogs(@RequestBody List<MatchLogDto> matchLogsDto) {
        matchLogService.addMatchLogs(matchLogsDto);
        return ResponseEntity.ok("Match Logs Added Successfully");
    }
}
