package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final int teamId;
    private final String name;
    private final List<Player> playerList= new ArrayList<>();
    private int wickets = 0;
    private int runScore = 0;

    public String getName() {
        return name;
    }
    public List<Player> getPlayerList() {
        return playerList;
    }
    public int getTeamId() {
        return teamId;
    }
    public int getWickets() {
        return wickets;
    }
    public int getRunScore() {
        return runScore;
    }

    public void addRunScore(int runScore) {
        this.runScore += runScore;
    }
    public void addWickets() {
        this.wickets++;
    }

    public Team(int teamId, String name)
    {
        this.teamId = teamId;
        this.name = name;
    }
}