package com.company.response;

public class OverStatsResponse {
    private String battingTeam;
    private String bowler;
    private int overNumber;
    private int runs;
    private int wickets;
    private int wide;
    private int noBall;

    public OverStatsResponse(String battingTeam, String bowler, int overNumber) {
        this.battingTeam = battingTeam;
        this.bowler = bowler;
        this.overNumber = overNumber;
    }

    public String getBattingTeam() {
        return battingTeam;
    }
    public String getBowler() {
        return bowler;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public int getRuns() {
        return runs;
    }
    public int getWickets() {
        return wickets;
    }
    public int getWide() {
        return wide;
    }
    public int getNoBall() {
        return noBall;
    }

    public void addRuns(int runs) {
        this.runs += runs;
    }
    public void addWickets() {
        this.wickets++;
    }
    public void addWide() {
        this.wide++;
        this.runs++;
    }
    public void addNoBall() {
        this.noBall++;
        this.runs++;
    }
}
