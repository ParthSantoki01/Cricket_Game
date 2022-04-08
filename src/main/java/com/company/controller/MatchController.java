package com.company.controller;

import com.company.response.MatchResponse;
import com.company.response.TeamStatsResponse;
import com.company.service.MatchControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchControllerService matchControllerService;

    @GetMapping("/match")
    public List<MatchResponse> getAllMatch()
    {
        return matchControllerService.getMatch();
    }

    @GetMapping("/match/{matchId}")
    public MatchResponse getMatch(@PathVariable int matchId)
    {
        return matchControllerService.getMatch(matchId);
    }

    @PostMapping("/match/new/{overInInning}")
    public String createNewMatch(@PathVariable int overInInning) {
        return matchControllerService.createNewMatch(overInInning);
    }

    @GetMapping("/match/{matchId}/teams")
    public List<TeamStatsResponse> getTeamsStats(@PathVariable int matchId) {
        return matchControllerService.getMatchTeamsStats(matchId);
    }

    @DeleteMapping("/match/{matchId}")
    public String deleteMatchDetails(@PathVariable int matchId) {
        return matchControllerService.deleteMatchDetails(matchId);
    }
}