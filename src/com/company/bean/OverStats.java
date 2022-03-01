package com.company.bean;

public class OverStats {
    private int teamId;
    private int overNumber;
    private int bowlerId;
    private int runs;
    private int playedDotBalls;
    private int oneRunsScored;
    private int twoRunsScored;
    private int threeRunsScored;
    private int fourRunsScored;
    private int sixRunsScored;
    private int wideBalls;
    private int noBalls;
    private int wickets;

    public int getTeamId() {
        return teamId;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public int getBowlerId() {
        return bowlerId;
    }
    public int getRuns() {
        return runs;
    }
    public int getPlayedDotBalls() {
        return playedDotBalls;
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
    public int getWideBalls() {
        return wideBalls;
    }
    public int getNoBalls() {
        return noBalls;
    }
    public int getWickets() {
        return wickets;
    }

    public void updateOverRunStates(int runs)
    {
        this.runs += runs;
        if(runs == 0) playedDotBalls++;
        else if(runs == 1) oneRunsScored++;
        else if(runs == 2) twoRunsScored++;
        else if(runs == 3) threeRunsScored++;
        else if(runs == 4) fourRunsScored++;
        else if(runs == 6) sixRunsScored++;
    }
    public void addWideBalls() {
        this.wideBalls++;
    }
    public void addNoBalls() {
        this.noBalls++;
    }
    public void addWickets() {
        this.wickets++;
    }

    public OverStats(int teamId, int overNumber, int bowlerId)
    {
        this.teamId = teamId;
        this.overNumber = overNumber;
        this.bowlerId = bowlerId;
    }
}
