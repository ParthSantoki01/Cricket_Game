package com.company;

import com.company.Utility.PlayerRole;

public class Player {
    private final int id;
    private final int teamId;
    private final int jerseyNumber;
    private final String name;
    private final PlayerRole role;
    private final int battingOrder;
    private int runScore;
    private int runsGiven;
    private int wicketsTaken;
    private int ballsFaced;
    private int ballsDelivered;
    private String wicketTakenBy;
    private boolean batsmanDismissal = false;
    private int noOfDotBall = 0;
    private int noOf1s = 0;
    private int noOf2s = 0;
    private int noOf3s = 0;
    private int noOfFour = 0;
    private int noOfSix = 0;

    public int getId() {
        return id;
    }
    public int getJerseyNumber() {
        return jerseyNumber;
    }
    public String getName() {
        return name;
    }
    public PlayerRole getRole() {
        return role;
    }
    public int getBattingOrder() {
        return battingOrder;
    }

    public int getRunScore() {
        return runScore;
    }
    public void addRunScore(int runScore) {
        this.runScore += runScore;
    }

    public int getRunsGiven() {
        return runsGiven;
    }
    public void addRunsGiven(int runsGiven) {
        this.runsGiven += runsGiven;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }
    public void addWicketsTaken() {
        this.wicketsTaken++;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }
    public void addBallsFaced() {
        this.ballsFaced++;
    }

    public int getBallsDelivered() {
        return ballsDelivered;
    }
    public void addBallsDelivered() {
        this.ballsDelivered++;
    }

    public String getWicketTakenBy() {
        return wicketTakenBy;
    }
    public void setWicketTakenBy(String wicketTakenBy) {
        this.wicketTakenBy = wicketTakenBy;
    }

    public String getOversDelivered() {
        return String.format("%d.%d",ballsDelivered/6,ballsDelivered%6);
    }

    public boolean isBatsmanDismissal() {
        return batsmanDismissal;
    }
    public void setBatsmanDismissal(boolean batsmanDismissal) {
        this.batsmanDismissal = batsmanDismissal;
    }

    public void updatePlayerRunStates(int runScore)
    {
        this.runScore += runScore;
        if(runScore == 0)noOfDotBall++;
        else if(runScore == 1)noOf1s++;
        else if(runScore == 2)noOf2s++;
        else if(runScore == 3)noOf3s++;
        else if(runScore == 4)noOfFour++;
        else if(runScore == 6)noOfSix++;
    }

    public int getNoOfDotBall() {
        return noOfDotBall;
    }
    public int getNoOf1s() {
        return noOf1s;
    }
    public int getNoOf2s() {
        return noOf2s;
    }
    public int getNoOf3s() {
        return noOf3s;
    }
    public int getNoOfFour() {
        return noOfFour;
    }
    public int getNoOfSix() {
        return noOfSix;
    }

    public Player (String name, PlayerRole role, int jerseyNumber, int id, int battingOrder, int teamId)
    {
        this.name = name;
        this.role = role;
        this.jerseyNumber = jerseyNumber;
        this.id = id;
        this.battingOrder = battingOrder;
        this.teamId = teamId;
    }
}