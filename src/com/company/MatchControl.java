package com.company;

import java.util.ArrayList;
import java.util.List;

public class MatchControl {
    public static void main(String[] args)
    {
        List<Team> teamList = new ArrayList<>();

        teamList.add(new Team("India", 1));
        teamList.add(new Team("England", 2));

        for (int i = 0; i < 2; i++) {
            teamList.get(i).printTeam();
        }

        CricketMatch newGame = new CricketMatch();

        newGame.tossOfMatch(teamList);
        newGame.startGame(20);

        for (int i = 0; i < 2; i++)
        {
            teamList.get(i).printScoreBoard();
        }

        newGame.winningTeam();
    }
}
