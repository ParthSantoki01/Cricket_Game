package com.company;

public class ScoreBoard {
    private final int scoreBoardId;
    private final int matchOver;
    private final Team team1;
    private final Team team2;



    public ScoreBoard(int scoreBoardId, int matchOver, Team team1, Team team2) {
        this.scoreBoardId = scoreBoardId;
        this.matchOver = matchOver;
        this.team1 = team1;
        this.team2 = team2;
    }
}
