package com.company.request;

import java.util.List;

public class ReqObjNewTeam {
    private String teamName;
    private List<String> playerNameList;

    public ReqObjNewTeam(String teamName, List<String> playerNameList) {
        this.teamName = teamName;
        this.playerNameList = playerNameList;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getPlayerNameList() {
        return playerNameList;
    }
}
