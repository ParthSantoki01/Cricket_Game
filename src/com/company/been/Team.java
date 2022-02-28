package com.company.been;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> playerList= new ArrayList<>();
    private final int teamId;
    private int runScore = 0;
    private int ballsFaced = 0;
    private int wickets = 0;
    private int wideRuns = 0;
    private int noBallRuns = 0;

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

    public String getPlayedOvers()
    {
        return String.format("%d.%d",ballsFaced/6,ballsFaced%6);
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

    public int getNoBallRuns() {
        return noBallRuns;
    }
    public void addNoBallRuns() {
        this.noBallRuns++;
    }

    public Team(int teamId, String name)
    {
        this.teamId = teamId;
        this.name = name;
    }
}