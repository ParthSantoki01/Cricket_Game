package com.company.service;

import com.company.response.MatchResponse;
import com.company.response.TeamStatsResponse;

import java.util.List;

public interface MatchControllerService {
    String createNewMatch(int overInInning);
    MatchResponse getMatch(int matchId);
    List<MatchResponse> getMatch();
    List<TeamStatsResponse> getMatchTeamsStats(int matchId);
    String deleteMatchDetails(int matchId);
}
