package com.company;
import com.company.Utility.PlayerRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
    private final String name;
    private final List<Player> playerList= new ArrayList<>();
    private final int teamId;
    private static final int NUMBER_OF_PLAYER_IN_TEAM = 11;
    private int runScore = 0;
    private int ballsFaced = 0;
    private int wickets = 0;
    private int wideRuns = 0;
    private int noBalls = 0;

    public String getName() {
        return name;
    }
    public List<Player> getPlayerList() {
        return playerList;
    }
    public int getTeamId() {
        return teamId;
    }

    public int getRunScore() {
        return runScore;
    }
    public void setRunScore(int runScore) {
        this.runScore += runScore;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }
    public void addBallsFaced() {
        this.ballsFaced++;
    }

    public String getPlayedOvers()
    {
        return String.format("%d.%d",ballsFaced/6,ballsFaced%6);
    }

    public int getWickets() {
        return wickets;
    }
    public void addWickets() {
        this.wickets++;
    }

    public int getWideRuns() {
        return wideRuns;
    }
    public void addWideRuns(int wideRuns) {
        this.wideRuns += wideRuns;
    }

    public int getNoBalls() {
        return noBalls;
    }
    public void addNoBalls() {
        this.noBalls++;
    }

    Scanner sc= new Scanner(System.in);
    public Team(String name, int teamId)
    {
        this.name = name;
        this.teamId = teamId;
        for(int i = 0; i < NUMBER_OF_PLAYER_IN_TEAM; i++)
        {
            System.out.print("Enter Player " + (i+1) + " Name : ");
            String playerName = sc.nextLine();
            while(playerName.length() == 0){
                System.out.print("Enter Player " + (i+1) + " Name : ");
                playerName = sc.nextLine();
            }
            if(i < 5) playerList.add(new Player(playerName, PlayerRole.Batsman, i+1, i, i,teamId));
            else if(i == 5)playerList.add(new Player(playerName, PlayerRole.Wicketkeeper, i+1, i, i,teamId));
            else playerList.add(new Player(playerName, PlayerRole.Bowler, i+1, i, i,teamId));
        }
    }
}