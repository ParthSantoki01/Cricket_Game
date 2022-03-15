package com.company.bean;

public class Teams {
    private int teamId;
    private String teamName;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

    public int getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
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

    public Teams(int teamId, String teamName, long createdTime, long modifiedTime, boolean deleted) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }
}