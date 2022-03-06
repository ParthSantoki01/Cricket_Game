package com.company.service;

import com.company.repository.PlayerRepo;
import com.company.repository.TeamRepo;
import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.TeamInfo;
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
    public List<PlayerInfo> getTeamPlayer(int teamId) {
        if(teamRepo.isTeamAvailable(teamId)) return teamRepo.getTeamPlayers(teamId);
        else return null;
    }

    @Override
    public List<TeamInfo> getAllTeam() {
        int lastTeamId = teamRepo.getNewTeamId();
        List<TeamInfo> teamList = new ArrayList<>();
        for(int i = 1; i <= lastTeamId; i++)
        {
            if(teamRepo.isTeamAvailable(i)) {
                List<String> teamInformation = teamRepo.getTeamInfo(i);
                teamList.add( new TeamInfo(teamInformation.get(0),teamInformation.get(1)));
            }
        }
        return teamList;
    }

    @Override
    public TeamInfo getTeam(int teamId) {
        if(teamRepo.isTeamAvailable(teamId)) {
            List<String> teamInformation = teamRepo.getTeamInfo(teamId);
            return new TeamInfo(teamInformation.get(0),teamInformation.get(1));
        }
        return null;
    }

    @Override
    public String insertNewTeam(String[] teamDetails) {
        int teamId = teamRepo.insertTeamData(teamDetails);
        if(teamId == 0)return "Error";
        playerRepo.addPlayerInTeam(teamId,teamDetails);
        return "Team Insert Successfully With teamId = " + teamId;
    }
}
