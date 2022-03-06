package com.company.repository.entity;

public class TeamInfo {
    private String teamId;
    private String teamName;

    public String getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
    }

    public TeamInfo(String teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
