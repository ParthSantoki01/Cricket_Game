package com.company.repository.entity;

public class PlayerInfo {
    private String playerId;
    private String name;
    private String battingOrder;
    private String role;
    private String teamId;

    public String getPlayerId() {
        return playerId;
    }
    public String getName() {
        return name;
    }
    public String getBattingOrder() {
        return battingOrder;
    }
    public String getRole() {
        return role;
    }
    public String getTeamId() {
        return teamId;
    }

    public PlayerInfo(String playerId, String name, String battingOrder, String role, String teamId)
    {
        this.playerId = playerId;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.teamId = teamId;
    }

}
