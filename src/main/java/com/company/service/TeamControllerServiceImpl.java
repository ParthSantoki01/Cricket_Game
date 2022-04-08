package com.company.service;

import com.company.bean.Players;
import com.company.bean.Teams;
import com.company.repository.PlayerRepo;
import com.company.repository.TeamRepo;
import com.company.request.ReqObjNewTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(cacheNames = "teamPlayerCache", key = "#teamId")
    public List<Players> getTeamPlayers(int teamId) {
        System.out.println("Player comes from Database");
        if(teamRepo.isTeamAvailable(teamId)) return playerRepo.getTeamPlayers(teamId);
        else return null;
    }

    @Override
    @Cacheable(cacheNames = "teamCache")
    public List<Teams> getAllTeam() {
        System.out.println("Team comes from Database");
        int lastTeamId = teamRepo.getNewTeamId();
        List<Teams> teamList = new ArrayList<>();
        for(int i = 1; i <= lastTeamId; i++)
            if (teamRepo.isTeamAvailable(i)) teamList.add(teamRepo.getTeamDetails(i));
        return teamList;
    }

    @Override
    @Cacheable(cacheNames = "teamCache")
    public Teams getTeam(int teamId) {
        System.out.println("Team comes from Database");
        if(teamRepo.isTeamAvailable(teamId)) return teamRepo.getTeamDetails(teamId);
        return null;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "teamCache", allEntries=true), @CacheEvict(value = "playerCache", allEntries=true) })
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
