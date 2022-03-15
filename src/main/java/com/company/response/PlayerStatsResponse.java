package com.company.response;

import com.company.enums.PlayerRole;

public class PlayerStatsResponse {
    private int playerId;
    private String name;
    private int battingOrder;
    private PlayerRole role;
    private String teamName;
    private int runScore;
    private int ballPlayed;
    private int dotBallPlayed;
    private int oneRunsScored;
    private int twoRunsScored;
    private int threeRunsScore;
    private int fourRunsScore;
    private int sixRunsScore;
    private String wicketTakenByBowler;
    private int runGiven;
    private int wicketTaken;
    private int ballDelivered;
    private int dotBallsDelivered;
    private int oneRunBallsBowled;
    private int twoRunBallsBowled;
    private int threeRunBallsBowled;
    private int fourRunBallsBowled;
    private int sixRunBallsBowled;
    private int wideBallsDelivered;
    private int noBallsDelivered;

    public PlayerStatsResponse(int playerId, String name, int battingOrder, PlayerRole role, String teamName) {
        this.playerId = playerId;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.teamName = teamName;
    }

    public int getPlayerId() {
        return playerId;
    }
    public String getName() {
        return name;
    }
    public int getBattingOrder() {
        return battingOrder;
    }
    public PlayerRole getRole() {
        return role;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getRunScore() {
        return runScore;
    }
    public int getBallPlayed() {
        return ballPlayed;
    }
    public int getDotBallPlayed() {
        return dotBallPlayed;
    }
    public int getOneRunsScored() {
        return oneRunsScored;
    }
    public int getTwoRunsScored() {
        return twoRunsScored;
    }
    public int getThreeRunsScore() {
        return threeRunsScore;
    }
    public int getFourRunsScore() {
        return fourRunsScore;
    }
    public int getSixRunsScore() {
        return sixRunsScore;
    }
    public String getWicketTakenByBowler() {
        return wicketTakenByBowler;
    }
    public int getRunGiven() {
        return runGiven;
    }
    public int getWicketTaken() {
        return wicketTaken;
    }
    public int getBallDelivered() {
        return ballDelivered;
    }
    public int getDotBallsDelivered() {
        return dotBallsDelivered;
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
    public int getWideBallsDelivered() {
        return wideBallsDelivered;
    }
    public int getNoBallsDelivered() {
        return noBallsDelivered;
    }

    public void addBallPlayed() {
        this.ballPlayed++;
    }
    public void addDotBallPlayed() {
        this.dotBallPlayed++;
    }
    public void addOneRunsScored() {
        this.oneRunsScored++;
        this.runScore++;
    }
    public void addTwoRunsScored() {
        this.twoRunsScored++;
        this.runScore += 2;
    }
    public void addThreeRunsScore() {
        this.threeRunsScore++;
        this.runScore += 3;
    }
    public void addFourRunsScore() {
        this.fourRunsScore++;
        this.runScore += 4;
    }
    public void addSixRunsScore() {
        this.sixRunsScore++;
        this.runScore += 6;
    }
    public void setWicketTakenByBowler(String wicketTakenByBowler) {
        this.wicketTakenByBowler = wicketTakenByBowler;
    }
    public void addWicketTaken() {
        this.wicketTaken++;
    }
    public void addBallDelivered() {
        this.ballDelivered++;
    }
    public void addDotBallsDelivered() {
        this.dotBallsDelivered++;
    }
    public void addOneRunBallsBowled() {
        this.oneRunBallsBowled++;
        this.runGiven++;
    }
    public void addTwoRunBallsBowled() {
        this.twoRunBallsBowled++;
        this.runGiven += 2;
    }
    public void addThreeRunBallsBowled() {
        this.threeRunBallsBowled++;
        this.runGiven += 3;
    }
    public void addFourRunBallsBowled() {
        this.fourRunBallsBowled++;
        this.runGiven += 4;
    }
    public void addSixRunBallsBowled() {
        this.sixRunBallsBowled++;
        this.runGiven += 6;
    }
    public void addWideBallsDelivered() {
        this.wideBallsDelivered++;
    }
    public void addNoBallsDelivered() {
        this.noBallsDelivered++;
    }
}
