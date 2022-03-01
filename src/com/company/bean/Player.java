package com.company.bean;
import com.company.enums.PlayerRole;

public class Player {
    private final int playerId;
    private final int teamId;
    private final int jerseyNumber;
    private final String name;
    private final PlayerRole role;
    private final int battingOrder;
    private int runScore;
    private int runsGiven;
    private int ballsFaced;
    private int ballsBowled;
    private int wicketsTaken;
    private Player wicketTakenByBowler = null;
    private int dotBallsPlayed = 0;
    private int oneRunsScored = 0;
    private int twoRunsScored = 0;
    private int threeRunsScored = 0;
    private int fourRunsScored = 0;
    private int sixRunsScored = 0;
    private int dotBallsBowled = 0;
    private int oneRunBallsBowled = 0;
    private int twoRunBallsBowled = 0;
    private int threeRunBallsBowled = 0;
    private int fourRunBallsBowled = 0;
    private int sixRunBallsBowled = 0;
    private int wideBallsBowled = 0;
    private int noBallsBowled = 0;

    public int getPlayerId() {
        return playerId;
    }
    public int getTeamId() {
        return teamId;
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
    public int getRunsGiven() {
        return runsGiven;
    }
    public int getBallsFaced() {
        return ballsFaced;
    }
    public int getBallsBowled() {
        return ballsBowled;
    }
    public int getWicketsTaken() {
        return wicketsTaken;
    }
    public Player getWicketTakenByBowler() {
        return wicketTakenByBowler;
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
    public int getDotBallsBowled() {
        return dotBallsBowled;
    }
    public int getOneRunBallsBowled() {
        return oneRunBallsBowled;
    }
    public int getTwoRunBallsBowled() {
        return twoRunBallsBowled;
    }
    public int getThreeRunBallsBowled() {
        return threeRunBallsBowled;
    }
    public int getFourRunBallsBowled() {
        return fourRunBallsBowled;
    }
    public int getSixRunBallsBowled() {
        return sixRunBallsBowled;
    }
    public int getWideBallsBowled() {
        return wideBallsBowled;
    }
    public int getNoBallsBowled() {
        return noBallsBowled;
    }
    public String getOversDelivered() {
        return String.format("%d.%d", ballsBowled /6, ballsBowled %6);
    }

    public void addRunsGiven(int runsGiven) {
        this.runsGiven += runsGiven;
    }
    public void addWicketsTaken() {
        this.wicketsTaken++;
    }
    public void addBallsFaced() {
        this.ballsFaced++;
    }
    public void addNoBallDelivered() {
        this.noBallsBowled++;
    }
    public void setWicketTakenByBowler(Player wicketTakenByBowler) {
        this.wicketTakenByBowler = wicketTakenByBowler;
    }
    public void updateBatsmanRunStates(int runScore)
    {
        this.runScore += runScore;
        if(runScore == 0) dotBallsPlayed++;
        else if(runScore == 1) oneRunsScored++;
        else if(runScore == 2) twoRunsScored++;
        else if(runScore == 3) threeRunsScored++;
        else if(runScore == 4) fourRunsScored++;
        else if(runScore == 6) sixRunsScored++;
    }
    public void updateBowlerRunStates(int runsGiven)
    {
        this.runsGiven += runsGiven;
        if(runsGiven == 0) dotBallsBowled++;
        else if(runsGiven == 1) oneRunBallsBowled++;
        else if(runsGiven == 2) twoRunBallsBowled++;
        else if(runsGiven == 3) threeRunBallsBowled++;
        else if(runsGiven == 4) fourRunBallsBowled++;
        else if(runsGiven == 6) sixRunBallsBowled++;
    }
    public void addBallsDelivered() {
        this.ballsBowled++;
    }
    public void addWideBallDelivered() {
        this.wideBallsBowled++;
    }

    public Player (int playerId, String name, int battingOrder, PlayerRole role, int jerseyNumber, int teamId)
    {
        this.playerId = playerId;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.jerseyNumber = jerseyNumber;
        this.teamId = teamId;
    }
}