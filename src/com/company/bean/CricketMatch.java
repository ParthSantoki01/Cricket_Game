package com.company.bean;

public class CricketMatch {
    private final int matchId;
    private final int oversInInning;
    private final ScoreBoard scoreBoard;

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
    public int getMatchId() {
        return matchId;
    }
    public int getOversInInning() {
        return oversInInning;
    }

    public CricketMatch(int oversInInning, int matchId)
    {
        this.oversInInning = oversInInning;
        this.matchId = matchId;
        this.scoreBoard = new ScoreBoard(matchId,oversInInning);
    }
}