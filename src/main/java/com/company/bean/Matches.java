package com.company.bean;

public class Matches {
    private int matchId;
    private int tossWinningTeamId;
    private int firstBattingTeamId;
    private int secondBattingTeamId;
    private int winningTeamId;
    private int runMargin = 0;
    private int wicketMargin = 0;
    private int oversInInning;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

    public Matches(int matchId, int oversInInning, long createdTime, long modifiedTime, boolean deleted) {
        this.matchId = matchId;
        this.oversInInning = oversInInning;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }

    public Matches(int matchId, int tossWinningTeamId, int firstBattingTeamId, int secondBattingTeamId, int winningTeamId, int runMargin, int wicketMargin, int oversInInning, long createdTime, long modifiedTime, boolean deleted) {
        this.matchId = matchId;
        this.tossWinningTeamId = tossWinningTeamId;
        this.firstBattingTeamId = firstBattingTeamId;
        this.secondBattingTeamId = secondBattingTeamId;
        this.winningTeamId = winningTeamId;
        this.runMargin = runMargin;
        this.wicketMargin = wicketMargin;
        this.oversInInning = oversInInning;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }

    public int getMatchId() {
        return matchId;
    }
    public int getTossWinningTeamId() {
        return tossWinningTeamId;
    }
    public int getFirstBattingTeamId() {
        return firstBattingTeamId;
    }
    public int getSecondBattingTeamId() {
        return secondBattingTeamId;
    }
    public int getWinningTeamId() {
        return winningTeamId;
    }
    public int getRunMargin() {
        return runMargin;
    }
    public int getWicketMargin() {
        return wicketMargin;
    }
    public int getOversInInning() {
        return oversInInning;
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

    public void setTossWinningTeamId(int tossWinningTeamId) {
        this.tossWinningTeamId = tossWinningTeamId;
    }
    public void setFirstBattingTeamId(int firstBattingTeamId) {
        this.firstBattingTeamId = firstBattingTeamId;
    }
    public void setSecondBattingTeamId(int secondBattingTeamId) {
        this.secondBattingTeamId = secondBattingTeamId;
    }
    public void setWinningTeamId(int winningTeamId) {
        this.winningTeamId = winningTeamId;
    }
    public void setRunMargin(int runMargin) {
        this.runMargin = runMargin;
    }
    public void setWicketMargin(int wicketMargin) {
        this.wicketMargin = wicketMargin;
    }
}