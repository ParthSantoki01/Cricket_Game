package com.company.bean;
import com.company.enums.PlayerRole;

public class Player {
    private final int playerId;
    private final String name;
    private final int battingOrder;
    private final PlayerRole role;
    private final int teamId;

    public int getPlayerId() {
        return playerId;
    }
    public String getName() {
        return name;
    }
    public int getBattingOrder() {
        return battingOrder;
    }
    public PlayerRole getRole() {
        return role;
    }
    public int getTeamId() {
        return teamId;
    }

    public Player (int playerId, String name, int battingOrder, PlayerRole role, int teamId)
    {
        this.playerId = playerId;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.teamId = teamId;
    }
}