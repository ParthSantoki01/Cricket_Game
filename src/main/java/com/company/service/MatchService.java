package com.company.service;

import com.company.bean.Match;
import com.company.bean.Team;

import java.util.List;

public interface MatchService {
    void tossOfMatch(List<Team> teams, Match match);
    void startGame(Match match, List<Team> teamList, int overId, int ballId);
}
