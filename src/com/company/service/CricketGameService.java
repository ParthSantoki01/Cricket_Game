package com.company.service;

import com.company.bean.CricketMatch;
import com.company.bean.ScoreBoard;
import com.company.bean.Team;

import java.util.List;

public interface CricketGameService {
    void tossOfMatch(List<Team> teams, ScoreBoard scoreBoard);
    void startGame(CricketMatch cricketMatch, List<Team> teamList);
}
