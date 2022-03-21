package com.company.service;

import com.company.bean.Players;
import com.company.bean.Teams;
import com.company.repository.PlayerRepo;
import com.company.repository.TeamRepo;
import com.company.request.ReqObjNewTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamControllerServiceImpl implements TeamControllerService {

    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PlayerRepo playerRepo;

    @Override
    public List<Players> getTeamPlayers(int teamId) {
        if(teamRepo.isTeamAvailable(teamId)) return playerRepo.getTeamPlayers(teamId);
        else return null;
    }

    @Override
    public List<Teams> getAllTeam() {
        int lastTeamId = teamRepo.getNewTeamId();
        List<Teams> teamList = new ArrayList<>();
        for(int i = 1; i <= lastTeamId; i++)
            if (teamRepo.isTeamAvailable(i)) teamList.add(teamRepo.getTeamDetails(i));
        return teamList;
    }

    @Override
    public Teams getTeam(int teamId) {
        if(teamRepo.isTeamAvailable(teamId)) return teamRepo.getTeamDetails(teamId);
        return null;
    }

    @Override
    public String insertNewTeam(ReqObjNewTeam newTeamObj) {
        if(newTeamObj.getTeamName().length() == 0) return "TeamName is not Valid.";
        if(newTeamObj.getPlayerNameList().size() != 11) return "The number of players in the team should be 11.";

        for(String playerName : newTeamObj.getPlayerNameList())
            if(playerName.length() == 0) return "PlayerName is not Valid.";

        int teamId = teamRepo.insertNewTeamDetails(newTeamObj);
        if(teamId == 0)return "DataBase error";
        if(!playerRepo.insertNewPlayersDetails(teamId,newTeamObj)) return "DataBase error";
        return "Team Insert Successfully With teamId = " + teamId;
    }

    @Override
    public String updateTeamName(String teamName, int teamId) {
        if(!teamRepo.isTeamAvailable(teamId))return "Team is not present in database.";
        if(teamName.length() == 0)return "TeamName is not Valid.";
        return teamRepo.updateTeamName(teamName,teamId);
    }
}
