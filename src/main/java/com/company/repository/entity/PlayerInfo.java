package com.company.repository.entity;

public class PlayerInfo {
    private String playerId;
    private String name;
    private String battingOrder;
    private String role;
    private String teamName;

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
    public String getTeamName() {
        return teamName;
    }

    public PlayerInfo(String playerId, String name, String battingOrder, String role, String teamName)
    {
        this.playerId = playerId;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.teamName = teamName;
    }

}
