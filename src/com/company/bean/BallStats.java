package com.company.bean;
import com.company.enums.PossibleOutputOfBall;

public class BallStats {
    private int teamId;
    private int overNumber;
    private int ballNumberInOver;
    private final Player batsman;
    private final Player bowler;
    private PossibleOutputOfBall ballOutcome;
    private int teamScore;
    private int teamWickets;

    public int getTeamId() {
        return teamId;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public int getBallNumberInOver() {
        return ballNumberInOver;
    }
    public Player getBatsman() {
        return batsman;
    }
    public Player getBowler() {
        return bowler;
    }
    public PossibleOutputOfBall getBallOutcome() {
        return ballOutcome;
    }
    public int getTeamScore() {
        return teamScore;
    }
    public int getTeamWickets() {
        return teamWickets;
    }

    public void setBallOutcome(PossibleOutputOfBall ballOutcome) {
        this.ballOutcome = ballOutcome;
    }
    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }
    public void setTeamWickets(int teamWickets) {
        this.teamWickets = teamWickets;
    }

    public BallStats(int teamId, int overNumber, int ballNumberInOver, Player batsman, Player bowler) {
        this.teamId = teamId;
        this.overNumber = overNumber;
        this.ballNumberInOver = ballNumberInOver;
        this.batsman = batsman;
        this.bowler = bowler;
    }
}
