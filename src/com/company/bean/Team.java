package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> playerList= new ArrayList<>();
    private final int teamId;
    private int runScore = 0;
    private int ballsFaced = 0;
    private int dotBallsPlayed = 0;
    private int oneRunsScored = 0;
    private int twoRunsScored = 0;
    private int threeRunsScored = 0;
    private int fourRunsScored = 0;
    private int sixRunsScored = 0;
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
    public int getBallsFaced() {
        return ballsFaced;
    }
    public int getDotBallsPlayed() {
        return dotBallsPlayed;
    }
    public int getOneRunsScored() {
        return oneRunsScored;
    }
    public int getTwoRunsScored() {
        return twoRunsScored;
    }
    public int getThreeRunsScored() {
        return threeRunsScored;
    }
    public int getFourRunsScored() {
        return fourRunsScored;
    }
    public int getSixRunsScored() {
        return sixRunsScored;
    }
    public int getWickets() {
        return wickets;
    }
    public int getWideRuns() {
        return wideRuns;
    }
    public int getNoBallRuns() {
        return noBallRuns;
    }
    public String getPlayedOvers()
    {
        return String.format("%d.%d",ballsFaced/6,ballsFaced%6);
    }

    public void setRunScore(int runScore) {
        this.runScore += runScore;
        if(runScore == 0) dotBallsPlayed++;
        else if(runScore == 1) oneRunsScored++;
        else if(runScore == 2) twoRunsScored++;
        else if(runScore == 3) threeRunsScored++;
        else if(runScore == 4) fourRunsScored++;
        else if(runScore == 6) sixRunsScored++;
    }
    public void addBallsFaced() {
        this.ballsFaced++;
    }
    public void addWickets() {
        this.wickets++;
    }
    public void addWideRuns(int wideRuns) {
        this.wideRuns += wideRuns;
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