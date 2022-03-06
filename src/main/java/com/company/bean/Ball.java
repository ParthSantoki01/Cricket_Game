package com.company.bean;
import com.company.enums.PossibleOutputOfBall;

public class Ball {
    private final int ballId;
    private final int overId;
    private final int ballNumberInOver;
    private final Player batsman;
    private PossibleOutputOfBall ballOutcome;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

    public int getBallId() {
        return ballId;
    }
    public int getOverId() {
        return overId;
    }
    public int getBallNumberInOver() {
        return ballNumberInOver;
    }
    public Player getBatsman() {
        return batsman;
    }
    public PossibleOutputOfBall getBallOutcome() {
        return ballOutcome;
    }
    public long getCreatedTime() {
        return createdTime;
    }
    public long getModifiedTime() {
        return modifiedTime;
    }
    public boolean isDeleted() {
        return deleted;
    }

    public void setBallOutcome(PossibleOutputOfBall ballOutcome) {
        this.ballOutcome = ballOutcome;
    }

    public Ball(int ballId, int overId ,int ballNumberInOver, Player batsman, Long createdTime, Long modifiedTime, boolean deleted) {
        this.ballId = ballId;
        this.overId = overId;
        this.ballNumberInOver = ballNumberInOver;
        this.batsman = batsman;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }
}
