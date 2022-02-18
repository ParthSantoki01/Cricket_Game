package com.company;

public class PerBallDetail {
    private final int batsmanId;
    private final int bowlerId;
    private final int overNumber;
    private final int ballNumberInOver;
    private final String ballOutcome;
    private final int teamScoreUpToThisBall;

    public int getBatsmanId() {
        return batsmanId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public int getBallNumberInOver() {
        return ballNumberInOver;
    }

    public String getBallOutcome() {
        return ballOutcome;
    }

    public int getTeamScoreUpToThisBall() {
        return teamScoreUpToThisBall;
    }

    public PerBallDetail(int batsmanId, int bowlerId, int overNumber, int ballNumberInOver, String ballOutcome, int teamScoreUpToThisBall) {
        this.batsmanId = batsmanId;
        this.bowlerId = bowlerId;
        this.overNumber = overNumber;
        this.ballNumberInOver = ballNumberInOver;
        this.ballOutcome = ballOutcome;
        this.teamScoreUpToThisBall = teamScoreUpToThisBall;
    }
}
