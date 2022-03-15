package com.company.response;

public class TeamStatsResponse {
    private int teamId;
    private String teamName;
    private int runScore;
    private String playerOver;
    private int wickets;
    private int wideRuns;
    private int noBallRuns;
    private int fours;
    private int sixes;

    public TeamStatsResponse(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getRunScore() {
        return runScore;
    }
    public String getPlayerOver() {
        return playerOver;
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

    public void setPlayerOver(String playerOver) {
        this.playerOver = playerOver;
    }
    public void addRunScore(int runScore) {
        this.runScore += runScore;
    }
    public void addWickets() {
        this.wickets++;
    }
    public void addWideRuns() {
        this.wideRuns++;
        this.runScore++;
    }
    public void addNoBallRuns() {
        this.noBallRuns++;
        this.runScore++;
    }
    public void addFours() {
        this.fours++;
        this.runScore += 4;
    }
    public void addSixes() {
        this.sixes++;
        this.runScore += 6;
    }
}
