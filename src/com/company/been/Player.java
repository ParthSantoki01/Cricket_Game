package com.company.been;
import com.company.enums.PlayerRole;

public class Player {
    private final int id;
    private final int teamId;
    private final int jerseyNumber;
    private final String name;
    private final PlayerRole role;
    private final int battingOrder;
    private int runScore;
    private int runsGiven;
    private int ballsFaced;
    private int ballsDelivered;
    private int wicketsTaken;
    private Player wicketTakenBy = null;
    private boolean batsmanDismissal = false;
    private int noOfDotBall = 0;
    private int noOf1sScore = 0;
    private int noOf2sScore = 0;
    private int noOf3sScore = 0;
    private int noOfFourScore = 0;
    private int noOfSixScore = 0;
    private int noOfDotBallGiven = 0;
    private int noOf1sGiven = 0;
    private int noOf2sGiven = 0;
    private int noOf3sGiven = 0;
    private int noOfFourGiven = 0;
    private int noOfSixGiven = 0;
    private int wideBallDelivered = 0;
    private int noBallDelivered = 0;

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

    public int getNoBallDelivered() {
        return noBallDelivered;
    }
    public void addNoBallDelivered() {
        this.noBallDelivered++;
    }

    public Player getWicketTakenBy() {
        return wicketTakenBy;
    }
    public void setWicketTakenBy(Player wicketTakenBy) {
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

    public void updateBatsmanRunStates(int runScore)
    {
        this.runScore += runScore;
        if(runScore == 0)noOfDotBall++;
        else if(runScore == 1) noOf1sScore++;
        else if(runScore == 2) noOf2sScore++;
        else if(runScore == 3) noOf3sScore++;
        else if(runScore == 4) noOfFourScore++;
        else if(runScore == 6) noOfSixScore++;
    }

    public void updateBowlerRunStates(int runsGiven)
    {
        this.runsGiven += runsGiven;
        if(runsGiven == 0) noOfDotBallGiven++;
        else if(runsGiven == 1) noOf1sGiven++;
        else if(runsGiven == 2) noOf2sGiven++;
        else if(runsGiven == 3) noOf3sGiven++;
        else if(runsGiven == 4) noOfFourGiven++;
        else if(runsGiven == 6) noOfSixGiven++;
    }
    public int getBallsDelivered() {
        return ballsDelivered;
    }
    public void addBallsDelivered() {
        this.ballsDelivered++;
    }
    public int getWideBallDelivered() {
        return wideBallDelivered;
    }
    public void addWideBallDelivered() {
        this.wideBallDelivered++;
    }

    public int getNoOfDotBall() {
        return noOfDotBall;
    }
    public int getNoOf1sScore() {
        return noOf1sScore;
    }
    public int getNoOf2sScore() {
        return noOf2sScore;
    }
    public int getNoOf3sScore() {
        return noOf3sScore;
    }
    public int getNoOfFourScore() {
        return noOfFourScore;
    }
    public int getNoOfSixScore() {
        return noOfSixScore;
    }

    public int getNoOfDotBallGiven() {
        return noOfDotBallGiven;
    }
    public int getNoOf1sGiven() {
        return noOf1sGiven;
    }
    public int getNoOf2sGiven() {
        return noOf2sGiven;
    }
    public int getNoOf3sGiven() {
        return noOf3sGiven;
    }
    public int getNoOfFourGiven() {
        return noOfFourGiven;
    }
    public int getNoOfSixGiven() {
        return noOfSixGiven;
    }

    public Player (int id, String name, int battingOrder, PlayerRole role, int jerseyNumber, int teamId)
    {
        this.id = id;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.jerseyNumber = jerseyNumber;
        this.teamId = teamId;
    }
}