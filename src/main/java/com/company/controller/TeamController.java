package com.company.controller;

import com.company.bean.Players;
import com.company.bean.Teams;
import com.company.request.ReqObjNewTeam;
import com.company.request.ReqObjRenameTeam;
import com.company.service.TeamControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private TeamControllerService teamControllerService;

    @GetMapping("/team")
    public List<Teams> getAllTeam()
    {
        return teamControllerService.getAllTeam();
    }

    @GetMapping("/team/{teamId}")
    public Teams getTeamDetails(@PathVariable int teamId)
    {
        return teamControllerService.getTeam(teamId);
    }

    @GetMapping("/team/{teamId}/player")
    public List<Players> getPlayerInfo(@PathVariable int teamId) {
        return teamControllerService.getTeamPlayers(teamId);
    }

    @PostMapping("/team/new")
    public String insertNewTeam(@RequestBody ReqObjNewTeam newTeamObj) {
        return teamControllerService.insertNewTeam(newTeamObj);
    }

    @PutMapping("/team/update")
    public String updatePlayerName(@RequestBody ReqObjRenameTeam newNameObj) {
        return teamControllerService.updateTeamName(newNameObj.getTeamNewName(),newNameObj.getTeamId());
    }
}
