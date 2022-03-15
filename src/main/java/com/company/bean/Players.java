package com.company.bean;

import com.company.enums.PlayerRole;

public class Players {
    private int playerId;
    private String playerName;
    private int battingOrder;
    private String playerRole;
    private int teamId;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

    public Players(int playerId, String playerName, int battingOrder, String playerRole, int teamId, long createdTime, long modifiedTime, boolean deleted) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.battingOrder = battingOrder;
        this.playerRole = playerRole;
        this.teamId = teamId;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBattingOrder() {
        return battingOrder;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public int getTeamId() {
        return teamId;
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