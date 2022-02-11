package com.company;

import java.util.ArrayList;

public class Cricket_Game {
    public static void main(String[] args)
    {
        ArrayList<Team> teams = new ArrayList<Team>();

        teams.add(new Team("Gujarat Titans", 1));
        teams.add(new Team("Mumbai Indians", 2));

        // Print Team Member Details
        for (int i = 0; i < 2; i++) {
            teams.get(i).printTeam();
        }

        // Create Game Object for Start Game
        Game_Control newGame = new Game_Control();

        // Start Game
        newGame.startGame(teams,5);
        
        // Print ScoreBoard 
        for (int i = 0; i < 2; i++) {
            teams.get(i).printScoreBoard();
        }

        // Printing A Winner.
        newGame.winningTeam(teams);

    }
}
