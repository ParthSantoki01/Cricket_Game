package com.company.ScoreBoards;

public class PerOverDetails {
    private final int overNumber;
    private int runGiven;
    private int noOfDotBall = 0;
    private int noOf1s = 0;
    private int noOf2s = 0;
    private int noOf3s = 0;
    private int noOfFour = 0;
    private int noOfSix = 0;
    private int wideBall;
    private int noBall;
    private int noOfWickets = 0;
    private final int bowlerId;

    public int getOverNumber() {
        return overNumber;
    }

    public int getRunGiven() {
        return runGiven;
    }

    public void updateOverRunStates(int runScore)
    {
        this.runGiven += runScore;
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

    public int getWideBall() {
        return wideBall;
    }
    public void addWideBall() {
        this.wideBall++;
    }

    public int getNoBall() {
        return noBall;
    }
    public void addNoBall() {
        this.noBall++;
    }

    public int getNoOfWickets() {
        return noOfWickets;
    }
    public void addNoOfWickets() {
        this.noOfWickets++;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public PerOverDetails(int overNumber, int bowlerId)
    {
        this.overNumber = overNumber;
        this.bowlerId = bowlerId;
    }
}
