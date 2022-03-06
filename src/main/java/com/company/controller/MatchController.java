package com.company.controller;

import com.company.repository.entity.MatchInfo;
import com.company.repository.entity.TeamStats;
import com.company.service.MatchControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchControllerServiceImpl matchControllerService;

    @GetMapping("/")
    public List<MatchInfo> getAllMatch()
    {
        return matchControllerService.getMatch();
    }

    @GetMapping("/{matchId}")
    public MatchInfo getMatch(@PathVariable int matchId)
    {
        return matchControllerService.getMatch(matchId);
    }

    @PostMapping("/new")
    public String startMatch(@RequestBody int overInInning) {
        int matchId =  matchControllerService.startNewMatch(overInInning);
        switch (matchId) {
            case -1:
                return "Error, Match not created";
            case 0:
                return "DataBase Error";
            default:
                return "New Match Created With matchId = " + matchId;
        }
    }

    @GetMapping("/{matchId}/teams")
    public List<TeamStats> getTeamsStats(@PathVariable int matchId) {
        return matchControllerService.getMatchTeamStats(matchId);
    }
}
