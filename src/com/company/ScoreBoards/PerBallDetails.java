package com.company.ScoreBoards;

import com.company.Player;
import com.company.Utility.PossibleOutputOfBall;

public class PerBallDetails {
    private final Player batsman;
    private final Player bowler;
    private int overNumber;
    private int ballNumberInOver;
    private PossibleOutputOfBall ballOutcome;
    private int teamScoreUpToThisBall;
    private int wicketsUpToThisBall;

    public Player getBatsman() {
        return batsman;
    }
    public Player getBowler() {
        return bowler;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public int getBallNumberInOver() {
        return ballNumberInOver;
    }
    public int getWicketsUpToThisBall() {
        return wicketsUpToThisBall;
    }
    public int getTeamScoreUpToThisBall() {
        return teamScoreUpToThisBall;
    }
    public PossibleOutputOfBall getBallOutcome() {
        return ballOutcome;
    }

    public void setBallOutcome(PossibleOutputOfBall ballOutcome) {
        this.ballOutcome = ballOutcome;
    }
    public void setTeamScoreUpToThisBall(int teamScoreUpToThisBall) {
        this.teamScoreUpToThisBall = teamScoreUpToThisBall;
    }
    public void setWicketsUpToThisBall(int wicketsUpToThisBall) {
        this.wicketsUpToThisBall = wicketsUpToThisBall;
    }

    public PerBallDetails(Player batsman, Player bowler, int overNumber, int ballNumberInOver) {
        this.batsman = batsman;
        this.bowler = bowler;
        this.overNumber = overNumber;
        this.ballNumberInOver = ballNumberInOver;
    }
}
