package com.company;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> playerList= new ArrayList<>();
    private final int teamId;
    private int runScore = 0;
    private int numberOfBallPlayed = 0;
    private int wickets = 0;
    private int wideRun = 0;
    private int noBallRun = 0;

    public String getName() {
        return this.name;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getTeamID() {
        return this.teamId;
    }

    public void addRun(int runScore) {
        this.runScore = this.runScore + runScore;
    }
    public int getRun() {
        return this.runScore;
    }

    public void addNumberOfBallPlayed(int  numberOfBallPlayed) {
        this.numberOfBallPlayed = this.numberOfBallPlayed + numberOfBallPlayed;
    }
    public int getNumberOfBallPlayed() {
        return this.numberOfBallPlayed;
    }

    public void addWickets(int wickets) {
        this.wickets = this.wickets + wickets;
    }
    public int getWickets()
    {
        return this.wickets;
    }

    public void addWideRun(int wideRun) {
        this.wideRun = this.wideRun + wideRun;
    }
    public int getWideRun() {
        return this.wideRun;
    }

    public void addNoBallRun(int noBallRun) {
        this.noBallRun = this.noBallRun + noBallRun;
    }
    public int getNoBallRun() {
        return this.noBallRun;
    }

    public void printTeam()
    {
        System.out.println("Team - " + name);
        System.out.println("\nTeam Players Details");
        for(byte i = 0; i < 11; i++)
        {
            System.out.print(playerList.get(i).getName() + " \t\t ");
            System.out.print(playerList.get(i).getRole() + " ");
            System.out.println("\t\tJersey number " + playerList.get(i).getJerseyNumber() + " ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------\n");
    }

    public void printScoreBoard()
    {
        System.out.println("\n----------------------------------");
        System.out.println("Team :- " +name);
        System.out.println("----------------------------------");
        System.out.println("Summary");
        System.out.println("Runs : \t\t" + runScore);
        System.out.println("Over: \t\t" + numberOfBallPlayed/6 + "." + numberOfBallPlayed%6);
        System.out.println("Wickets:\t" + wickets);
        System.out.println("Wide: \t\t" + wideRun);
        System.out.println("No Ball:\t" + noBallRun);
        System.out.println("-----------------\n");
        System.out.println("Score Board");
        System.out.println("-----------------");
        System.out.println("Batting Performance");
        for(byte i = 0; i < 11; i++)
        {
            float strikeRate = 0;
            if(playerList.get(i).getNoOfBallsPlayed() != 0)   strikeRate = ((float) playerList.get(i).getRunScore() / (float) playerList.get(i).getNoOfBallsPlayed()) * 100;

            System.out.println(playerList.get(i).getName() + " \t Run -> " + playerList.get(i).getRunScore() + "\t Ball -> " + playerList.get(i).getNoOfBallsPlayed() + "\t Strike Rate -> " + String.format("%.2f", strikeRate) + "\t Batting Status -> " + playerList.get(i).getBattingStatus());
        }

        System.out.println("\nBowling Performance");
        for(byte i = 6; i < 11; i++)
        {
            float economyRate = 0;
            if(playerList.get(i).getNumberOfWicketsTaken() != 0)
            {
                economyRate = (float) ((playerList.get(i).getPlayedOverInBall())  / playerList.get(i).getNumberOfWicketsTaken());
            }
            System.out.println(playerList.get(i).getName() + " \t Wickets -> " + playerList.get(i).getNumberOfWicketsTaken() + "\t over -> " + playerList.get(i).getBowledOver() + " \t Economy Rate -> " + String.format("%.2f", economyRate) + " \t Given Run -> " + playerList.get(i).getGivenRun());
        }
    }

    public Team(String name, int teamId)
    {
        this.name = name;
        this.teamId = teamId;
        for(byte i = 0; i < 11; i++)
        {
            if(i < 6) playerList.add(new Player(name + " Player " + (i+1), "Batsman", i+1, i));
            else playerList.add(new Player(name + " Player " + (i+1), "Bowler", i+1, i));
        }
    }
}