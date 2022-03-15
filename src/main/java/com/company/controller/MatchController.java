package com.company.controller;

import com.company.response.MatchResponse;
import com.company.response.TeamStatsResponse;
import com.company.service.MatchControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchControllerService matchControllerService;

    @GetMapping("/")
    public List<MatchResponse> getAllMatch()
    {
        return matchControllerService.getMatch();
    }

    @GetMapping("/{matchId}")
    public MatchResponse getMatch(@PathVariable int matchId)
    {
        return matchControllerService.getMatch(matchId);
    }

    @PostMapping("/new/{overInInning}")
    public String createNewMatch(@PathVariable int overInInning) {
        return matchControllerService.createNewMatch(overInInning);
    }

    @GetMapping("/{matchId}/teams")
    public List<TeamStatsResponse> getTeamsStats(@PathVariable int matchId) {
        return matchControllerService.getMatchTeamsStats(matchId);
    }

    @DeleteMapping("/{matchId}")
    public String deleteMatchDetails(@PathVariable int matchId) {
        return matchControllerService.deleteMatchDetails(matchId);
    }
}