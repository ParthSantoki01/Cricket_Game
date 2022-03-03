package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class Over {
    private final int matchId;
    private final int battingTeamId;
    private final int overNumber;
    private final Player bowler;
    private final List<Ball> ballList = new ArrayList<>();

    public int getMatchId() {
        return matchId;
    }
    public int getBattingTeamId() {
        return battingTeamId;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public Player getBowler() {
        return bowler;
    }
    public List<Ball> getBallList() {
        return ballList;
    }

    public Over(int matchId, int battingTeamId, int overNumber, Player bowler)
    {
        this.matchId = matchId;
        this.battingTeamId = battingTeamId;
        this.overNumber = overNumber;
        this.bowler = bowler;
    }
}
