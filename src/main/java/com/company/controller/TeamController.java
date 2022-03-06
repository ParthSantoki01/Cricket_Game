package com.company.controller;

import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.TeamInfo;
import com.company.service.TeamControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamControllerServiceImpl teamControllerService;

    @GetMapping("/")
    public List<TeamInfo> getAllTeam()
    {
        return teamControllerService.getAllTeam();
    }

    @GetMapping("/{teamId}")
    public TeamInfo getTeamDetails(@PathVariable int teamId)
    {
        return teamControllerService.getTeam(teamId);
    }

    @GetMapping("/{teamId}/player")
    public List<PlayerInfo> getPlayerInfo(@PathVariable int teamId)
    {
        return teamControllerService.getTeamPlayer(teamId);
    }
    @PostMapping("/new")
    public String insertNewTeam(@RequestBody String[] teamDetails)
    {
        return teamControllerService.insertNewTeam(teamDetails);
//        return teamDetails[5];
    }
}