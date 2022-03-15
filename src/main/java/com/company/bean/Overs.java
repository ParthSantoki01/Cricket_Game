package com.company.bean;

public class Overs {
    private int overId;
    private int matchId;
    private int battingTeamId;
    private int overNumber;
    private int bowlerId;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

    public Overs(int overId, int matchId, int battingTeamId, int overNumber, int bowlerId, long createdTime, long modifiedTime, boolean deleted) {
        this.overId = overId;
        this.matchId = matchId;
        this.battingTeamId = battingTeamId;
        this.overNumber = overNumber;
        this.bowlerId = bowlerId;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }

    public int getOverId() {
        return overId;
    }
    public int getMatchId() {
        return matchId;
    }
    public int getBattingTeamId() {
        return battingTeamId;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public int getBowlerId() {
        return bowlerId;
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
}
