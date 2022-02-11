package com.company;

import java.util.ArrayList;

public class Team {
    String teamName;
    ArrayList<Player> teamPlayer= new ArrayList<>();
    int iD;
    int teamRun = 0;
    float teamOver = 0.0F;
    int wickets = 0;
    int wideRun = 0;
    int noBallRun = 0;
    public void printTeam()
    {
        System.out.println("Team - " + teamName);
        System.out.println("\nTeam Players Details");
        for(byte i = 0; i < 11; i++)
        {
            System.out.print(teamPlayer.get(i).name + " \t\t ");
            System.out.print(teamPlayer.get(i).type + " ");
            System.out.println("\t\tJersey number " + teamPlayer.get(i).jerseyNumber + " ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------\n");
    }
    public void printScoreBoard()
    {
        System.out.println("\n----------------------------------");
        System.out.println("Team :- " +teamName);
        System.out.println("----------------------------------");
        System.out.println("Summary");
        System.out.println("Runs : \t\t" + teamRun);

        for(byte i = 0; i < 11; i++)
        {
            teamOver += teamPlayer.get(i).playerOver;
        }
        System.out.println("Over: \t\t" + teamOver);
        System.out.println("Wickets:\t" + wickets);
        System.out.println("Wide: \t\t" + wideRun);
        System.out.println("No Ball:\t" + noBallRun);
        System.out.println("-----------------\n");
        System.out.println("Score Board");
        System.out.println("-----------------");
        System.out.println("Batting Performance\n");
        for(byte i = 0; i < 11; i++)
        {
            // Finding Strike Rate of Batsman
            float strikeRate = 0;
            if(teamPlayer.get(i).playedBall != 0)   strikeRate = ((float) teamPlayer.get(i).run / (float) teamPlayer.get(i).playedBall) * 100;

            // Print
            System.out.println(teamPlayer.get(i).name + " \t Run -> " + teamPlayer.get(i).run + "\t Ball -> " + teamPlayer.get(i).playedBall + "\t Strike Rate -> " + String.format("%.2f", strikeRate) + "\t Out By -> " + teamPlayer.get(i).outBy);
        }

        System.out.println("\nBowling Performance");
        for(byte i = 6; i < 11; i++)
        {
            float economyRate = 0;
            if(teamPlayer.get(i).wickets != 0)
            {
                // Calculate Economy Rate Of Bowler.
                economyRate = (float) ((((int)teamPlayer.get(i).playerOver * 6) + (teamPlayer.get(i).playerOver - Math.floor(teamPlayer.get(i).playerOver)) * 10)  / teamPlayer.get(i).wickets);
            }
            System.out.println(teamPlayer.get(i).name + " \t Wickets -> " + teamPlayer.get(i).wickets + "\t over -> " + teamPlayer.get(i).playerOver + " \t Economy Rate -> " + String.format("%.2f", economyRate));
        }
    }
    public Team(String name, int id)
    {
        teamName = name;
        iD = id;
        for(byte i = 0; i < 11; i++)
        {
            if(i < 6) teamPlayer.add(new Player(name + " Player " + (i+1), "Batsman", i+1, i));
            else teamPlayer.add(new Player(name + " Player " + (i+1), "Bowler", i+1, i));
        }
    }
}