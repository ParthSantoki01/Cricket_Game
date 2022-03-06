package com.company.repository.entity;

public class TeamStats {
    private int teamId;
    private String teamName;
    private int runScore;
    private int wickets;
    private int wideRuns;
    private int noBallRuns;
    private int fours;
    private int sixes;

    public int getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getRunScore() {
        return runScore;
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
    public int getFours() {
        return fours;
    }
    public int getSixes() {
        return sixes;
    }

    public void addRunScore(int runScore) {
        this.runScore += runScore;
    }
    public void addWickets() {
        this.wickets++;
    }
    public void addWideRuns() {
        this.wideRuns++;
    }
    public void addNoBallRuns() {
        this.noBallRuns++;
    }
    public void addFours() {
        this.fours++;
    }
    public void addSixes() {
        this.sixes++;
    }

    public TeamStats(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public TeamStats(int teamId, String teamName, int runScore, int wickets, int wideRuns, int noBallRuns, int fours, int sixes) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.runScore = runScore;
        this.wickets = wickets;
        this.wideRuns = wideRuns;
        this.noBallRuns = noBallRuns;
        this.fours = fours;
        this.sixes = sixes;
    }
}
