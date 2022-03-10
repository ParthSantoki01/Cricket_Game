package com.company.service;

import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.TeamInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeamControllerService {
    List<PlayerInfo> getTeamPlayer(int teamId);
    List<TeamInfo> getAllTeam();
    TeamInfo getTeam(int teamId);
    String insertNewTeam(String[] teamDetails);
    String updateTeamName(@RequestBody String teamName, @PathVariable int teamId);
}
