package com.company.repository.entity;

public class PlayerStats {
    private String playerId;
    private String name;
    private String battingOrder;
    private String role;
    private String teamName;
    private int runScore;
    private int ballPlayed;
    private int dotBallPlayed;
    private int oneRunsScored;
    private int twoRunsScored;
    private int threeRunsScore;
    private int fourRunsScore;
    private int sixRunsScore;
    private String wicketTakenByBowler = "Not Out";
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

    public PlayerStats(String playerId, String name, String battingOrder, String role, String teamName) {
        this.playerId = playerId;
        this.name = name;
        this.battingOrder = battingOrder;
        this.role = role;
        this.teamName = teamName;
    }

    public String getPlayerId() {
        return playerId;
    }
    public String getName() {
        return name;
    }
    public String getBattingOrder() {
        return battingOrder;
    }
    public String getRole() {
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

    public void addRunScore(int runScore) {
        this.runScore += runScore;
    }
    public void addBallPlayed() {
        this.ballPlayed++;
    }
    public void addDotBallPlayed() {
        this.dotBallPlayed++;
    }
    public void addOneRunsScored() {
        this.oneRunsScored++;
    }
    public void addTwoRunsScored() {
        this.twoRunsScored++;
    }
    public void addThreeRunsScore() {
        this.threeRunsScore++;
    }
    public void addFourRunsScore() {
        this.fourRunsScore++;
    }
    public void addSixRunsScore() {
        this.sixRunsScore++;
    }
    public void setWicketTakenByBowler(String wicketTakenByBowler) {
        this.wicketTakenByBowler = wicketTakenByBowler;
    }
    public void addRunGiven(int runGiven) {
        this.runGiven += runGiven;
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
    }
    public void addTwoRunBallsBowled() {
        this.twoRunBallsBowled++;
    }
    public void addThreeRunBallsBowled() {
        this.threeRunBallsBowled++;
    }
    public void addFourRunBallsBowled() {
        this.fourRunBallsBowled++;
    }
    public void addSixRunBallsBowled() {
        this.sixRunBallsBowled++;
    }
    public void addWideBallsDelivered() {
        this.wideBallsDelivered++;
    }
    public void addNoBallsDelivered() {
        this.noBallsDelivered++;
    }
}
