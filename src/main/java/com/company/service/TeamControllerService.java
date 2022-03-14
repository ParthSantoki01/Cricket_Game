package com.company.service;

import com.company.bean.Players;
import com.company.bean.Teams;
import com.company.request.ReqObjNewTeam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeamControllerService {
    List<Players> getTeamPlayers(int teamId);
    List<Teams> getAllTeam();
    Teams getTeam(int teamId);
    String insertNewTeam(ReqObjNewTeam newTeamObj);
    String updateTeamName(@RequestBody String teamName, @PathVariable int teamId);
}
