package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchControl {
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        List<Team> teamList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            System.out.print("Enter Name of Team " + (i+1) + " : ");
            String teamName = sc.nextLine();
            teamList.add(new Team(teamName, i));
        }

        for (int i = 0; i < 2; i++) {
            PrintingClass.printTeam(teamList.get(i));
        }

        System.out.print("Enter Number of Match Over : ");
        int totalOver = sc.nextInt();

        CricketMatch newGame = new CricketMatch(totalOver, 1);
        newGame.tossOfMatch(teamList);
        newGame.startGame();

        for (int i = 0; i < 2; i++)
        {
            PrintingClass.printScoreBoard(teamList.get(i));
        }
    }
}
