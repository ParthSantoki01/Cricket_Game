package com.company;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> playerList= new ArrayList<>();
    private final int teamId;
    private static final int NUMBER_OF_PLAYER_IN_TEAM = 11;
    private int runScore = 0;
    private int ballsFaced = 0;
    private int wickets = 0;
    private int wideRuns = 0;
    private int noBalls = 0;

    public String getName() {
        return name;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getRunScore() {
        return runScore;
    }

    public void setRunScore(int runScore) {
        this.runScore += runScore;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void addBallsFaced() {
        this.ballsFaced++;
    }

    public int getWickets() {
        return wickets;
    }

    public void addWickets() {
        this.wickets++;
    }

    public int getWideRuns() {
        return wideRuns;
    }

    public void addWideRuns(int wideRuns) {
        this.wideRuns += wideRuns;
    }

    public int getNoBalls() {
        return noBalls;
    }

    public void addNoBalls() {
        this.noBalls++;
    }

    public Team(String name, int teamId)
    {
        this.name = name;
        this.teamId = teamId;
        for(int i = 0; i < NUMBER_OF_PLAYER_IN_TEAM; i++)
        {
            if(i < 6) playerList.add(new Player(name + (i+1), "Batsman", i+1, i, i));
            else playerList.add(new Player(name + (i+1), "Bowler", i+1, i, i));
        }
    }
}