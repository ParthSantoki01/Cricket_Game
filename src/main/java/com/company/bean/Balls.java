package com.company.bean;
import com.company.enums.PossibleOutputOfBall;

public class Balls {
    private int ballId;
    private int overId;
    private int ballNumberInOver;
    private int batsmanId;
    private PossibleOutputOfBall ballOutcome;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

    public Balls(int ballId, int overId, int ballNumberInOver, int batsmanId, long createdTime, long modifiedTime, boolean deleted) {
        this.ballId = ballId;
        this.overId = overId;
        this.ballNumberInOver = ballNumberInOver;
        this.batsmanId = batsmanId;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }

    public int getBallId() {
        return ballId;
    }
    public int getOverId() {
        return overId;
    }
    public int getBallNumberInOver() {
        return ballNumberInOver;
    }
    public int getBatsmanId() {
        return batsmanId;
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
}
