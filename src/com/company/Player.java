package com.company;

public class Player {
    private final int id;
    private final int jerseyNumber;
    private final String name;
    private final String role;
    private String battingStatus = PlayerBattingStatus.status1.getValue();
    private int runScore = 0;
    private int givenRun = 0;
    private int numberOfWicketsTaken = 0;
    private int noOfBallsPlayed = 0;
    private int noOfBallsBowled = 0;

    public int getId() {
        return this.id;
    }

    public int getJerseyNumber() {
        return this.jerseyNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public String getBattingStatus() {
        return this.battingStatus;
    }
    public void setBattingStatus(String battingStatus) {
        this.battingStatus = battingStatus;
    }

    public void addRunScore(int runScore) {
        this.runScore = this.runScore + runScore;
    }
    public int getRunScore() {
        return this.runScore;
    }

    public void addGivenRun(int givenRun) {
        this.givenRun = this.givenRun + givenRun;
    }
    public int getGivenRun() {
        return givenRun;
    }

    public void addNumberOfWicketsTaken(int numberOfWicketsTaken) {
        this.numberOfWicketsTaken = this.numberOfWicketsTaken + numberOfWicketsTaken;
    }
    public int getNumberOfWicketsTaken() {
        return this.numberOfWicketsTaken;
    }

    public void addNoOfBallsPlayed(int noOfBallsPlayed) {
        this.noOfBallsPlayed = this.noOfBallsPlayed + noOfBallsPlayed;
    }
    public int getNoOfBallsPlayed() {
        return noOfBallsPlayed;
    }
    public void addNoOfBallsBowled(int noOfBallsBowled) {
        this.noOfBallsBowled = this.noOfBallsBowled + noOfBallsBowled;
    }
    public int getPlayedOverInBall()
    {
        return this.noOfBallsBowled;
    }
    public String getBowledOver() {
        return String.format("%d.%d",noOfBallsBowled/6,noOfBallsBowled%6);
    }

    public Player (String name, String role, int jerseyNumber, int id)
    {
        this.name = name;
        this.role = role;
        this.jerseyNumber = jerseyNumber;
        this.id = id;
    }
}

