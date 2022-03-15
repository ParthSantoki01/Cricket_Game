package com.company.request;

public class ReqObjRenameTeam {
    private int teamId;
    private String teamNewName;

    public ReqObjRenameTeam(int teamId, String teamNewName) {
        this.teamId = teamId;
        this.teamNewName = teamNewName;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamNewName() {
        return teamNewName;
    }
}
