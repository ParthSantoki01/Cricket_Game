package com.company.service;

import com.company.bean.Match;
import com.company.bean.Team;
import com.company.enums.PossibleOutputOfBall;
import com.company.repository.*;
import com.company.repository.entity.MatchInfo;
import com.company.repository.entity.TeamStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchControllerServiceImpl implements MatchControllerService {

    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private OverRepo overRepo;
    @Autowired
    private BallRepo ballRepo;

    @Override
    public int startNewMatch(int overInInning) {
        int matchId = matchRepo.getNewMatchId();
        if(matchId == -1)return -1;

        List<Team> teamList = new ArrayList<>();
        if(!teamRepo.getTeams(teamList)) return -1;
        if(!playerRepo.setPlayerInTeam(teamList))return -1;

        int newOverId = overRepo.getNoOfOverInDatabase();
        int newBallId = ballRepo.getNoOfBallInDatabase();

        Match newMatch = new Match(matchId, overInInning);
        MatchServiceImpl matchService = new MatchServiceImpl();
        matchService.startGame(newMatch, teamList, newOverId, newBallId);
        String matchStatus = matchRepo.insertMatchDetails(newMatch);
        String overStatus = overRepo.insertOversDetails(newMatch.getOverList());
        String ballStatus = ballRepo.insertBallsDetails(newMatch.getOverList());

        if(matchStatus.equals("Error") || overStatus.equals("Error") || ballStatus.equals("Error"))
        {
            return 0;
        }
        return matchId;
    }

    @Override
    public MatchInfo getMatch(int matchId) {
        if(!matchRepo.isMatchAvailable(matchId)) return null;
        else return matchRepo.getMatch(matchId);
    }

    @Override
    public List<MatchInfo> getMatch() {
        List<MatchInfo> matchList = new ArrayList<>();
        int lastMatchId = matchRepo.getNewMatchId();
        for(int i = 1; i < lastMatchId; i++)
        {
            matchList.add(matchRepo.getMatch(i));
        }
        return matchList;
    }

    @Override
    public List<TeamStats> getMatchTeamStats(int matchId) {
        if(!matchRepo.isMatchAvailable(matchId)) return null;
        List<Integer> teamIdList = new ArrayList<>();
        teamRepo.getTeamsIdInMatch(teamIdList,matchId);
        List<TeamStats> teamStatsList = new ArrayList<>();
        List<List<String>> teamOutcomes = new ArrayList<>();
        teamOutcomes.add(teamRepo.getTeamOutcomes(teamIdList.get(0),matchId));
        teamOutcomes.add(teamRepo.getTeamOutcomes(teamIdList.get(1),matchId));

        int i = 0;
        for (List<String> teamOutcome : teamOutcomes)
        {
            TeamStats team = new TeamStats(teamIdList.get(i),teamRepo.getTeamName(teamIdList.get(i)));
            for (String stats : teamOutcome)
            {
                switch (PossibleOutputOfBall.valueOf(stats)) {
                    case RUN_1:
                        team.addRunScore(1);
                        break;
                    case RUN_2:
                        team.addRunScore(2);
                        break;
                    case RUN_3:
                        team.addRunScore(3);
                        break;
                    case RUN_4:
                        team.addRunScore(4);
                        team.addFours();
                        break;
                    case RUN_6:
                        team.addRunScore(6);
                        team.addSixes();
                        break;
                    case WICKET:
                        team.addWickets();
                        break;
                    case WIDE:
                        team.addRunScore(1);
                        team.addWideRuns();
                        break;
                    case NO_BALL:
                        team.addRunScore(1);
                        team.addNoBallRuns();
                        break;
                }
            }
            teamStatsList.add(team);
            i++;
        }

        return teamStatsList;
    }
}
