package com.company.service;

import com.company.enums.PossibleOutputOfBall;
import com.company.repository.MatchRepo;
import com.company.repository.OverRepo;
import com.company.repository.PlayerRepo;
import com.company.repository.TeamRepo;
import com.company.repository.entity.OverStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OverControllerServiceImpl implements OverControllerService {
    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private OverRepo overRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PlayerRepo playerRepo;

    @Override
    public List<OverStats> getOverDetails(int matchId) {
        if(!matchRepo.isMatchAvailable(matchId))return null;
        int overInInning = matchRepo.getOverInInning(matchId);
        List<Integer> teamsIdInMatch = new ArrayList<>();
        teamRepo.getTeamsIdInMatch(teamsIdInMatch,matchId);

        List<OverStats> overStatsList = new ArrayList<>();
        for(int teamId : teamsIdInMatch) {
            for(int i = 1; i <= overInInning; i++) {
                if(!overRepo.isOverAvailableInMatch(matchId,teamId,i)) break;
                List<String> overOutcome = overRepo.getOverDetailInMatch(matchId,teamId,i);
                String bowlerName = playerRepo.getBowlerInOver(matchId,teamId,i);
                OverStats overStats = new OverStats(teamRepo.getTeamName(teamId),bowlerName,i);
                for(String ballOutcome : overOutcome)
                {
                    switch (PossibleOutputOfBall.valueOf(ballOutcome)) {
                        case WICKET:
                            overStats.addWickets();
                            break;
                        case WIDE:
                            overStats.addWide();
                            break;
                        case NO_BALL:
                            overStats.addNoBall();
                            break;
                        default:
                            int run = Integer.parseInt(String.valueOf(ballOutcome.charAt(4)));
                            overStats.addRuns(run);
                            break;
                    }
                }
                overStatsList.add(overStats);
            }
        }
        return overStatsList;
    }
}
