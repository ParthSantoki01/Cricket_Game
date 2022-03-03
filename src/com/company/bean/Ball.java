package com.company.bean;
import com.company.enums.PossibleOutputOfBall;

public class Ball {
    private final int ballNumberInOver;
    private final Player batsman;
    private PossibleOutputOfBall ballOutcome;

    public int getBallNumberInOver() {
        return ballNumberInOver;
    }
    public Player getBatsman() {
        return batsman;
    }
    public PossibleOutputOfBall getBallOutcome() {
        return ballOutcome;
    }

    public void setBallOutcome(PossibleOutputOfBall ballOutcome) {
        this.ballOutcome = ballOutcome;
    }

    public Ball(int ballNumberInOver, Player batsman) {
        this.ballNumberInOver = ballNumberInOver;
        this.batsman = batsman;
    }
}
