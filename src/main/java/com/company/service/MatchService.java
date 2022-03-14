package com.company.service;

import com.company.bean.*;
import java.util.List;
import java.util.Map;

public interface MatchService {
    void tossOfMatch(int team1Id, int team2Id, Matches matches);
    void startGame(Matches cricketMatch, Map<Integer,Teams> teams, Map<Integer,List<Players>> playersList, List<Overs> oversList, List<Balls> ballsList, int overId, int ballId);
}
