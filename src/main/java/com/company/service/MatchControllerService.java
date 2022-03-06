package com.company.service;

import com.company.repository.entity.MatchInfo;
import com.company.repository.entity.TeamStats;

import java.util.List;

public interface MatchControllerService {
    int startNewMatch(int overInInning);
    MatchInfo getMatch(int matchId);
    List<MatchInfo> getMatch();
    List<TeamStats> getMatchTeamStats(int matchId);
}
