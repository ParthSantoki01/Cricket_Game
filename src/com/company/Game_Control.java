package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Game_Control {
    int strikerBatsman = 0;             // Striker Batsman
    int nonStrikerBatsman = 1;          // Non Striker Batsman
    boolean inning_over = false;        // For checking 10 wickets Gone or not
    int strikeBowler = 1;

    private static final DecimalFormat dfSharp = new DecimalFormat("0.0");

    // Finding Winning Team and Printing.
    public void winningTeam(ArrayList<Team> teams)
    {
        System.out.println("\n-----------------------------------------------------------------------------");

        // Match Tie Condition.
        if(teams.get(0).teamRun == teams.get(1).teamRun)
        {
            System.out.println("Match Tie");
        }
        // First Team Win A Match
        else if(teams.get(0).teamRun > teams.get(1).teamRun)
        {
            int runDiff = teams.get(0).teamRun - teams.get(1).teamRun;
            System.out.println(teams.get(0).teamName + " was win by " + runDiff + " run");
        }
        // Second Team Win A Match
        else
        {
            int runDiff = teams.get(1).teamRun - teams.get(0).teamRun;
            System.out.println(teams.get(1).teamName + " was win by " + runDiff + " run");
        }

        System.out.println("-----------------------------------------------------------------------------\n");
    }

    // For Random Run
    // Return 1 For Wicket
    // Return 2 For Wide and No Ball, Where Ball Was Not Count.
    private int randomRun(ArrayList<Team> teams, int team_no)
    {
        int run = (int)(Math.random() * 10);
        //  0 to 6 Runs.
        //  7 for Wickets
        //  8 for Wide
        //  9 for No Ball

        if(run == 7)    // For wicket
        {
            // For Printing Commentary
            System.out.println("Wicket");

            // For Which Bowler Out A Batsman.
            teams.get(team_no).teamPlayer.get(strikerBatsman).outBy = teams.get(1 - team_no).teamPlayer.get(strikeBowler).name;

            // Add Played Ball in current Player.
            teams.get(team_no).teamPlayer.get(strikerBatsman).playedBall++;

            // Add 1 Ball To Bowler And  Format Over
            teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver += 0.1;
            dfSharp.format(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver);

            // Check Which Player Is Next To Bat.
            if(strikerBatsman > nonStrikerBatsman)strikerBatsman++;
            else strikerBatsman = nonStrikerBatsman + 1;

            // Team Wickets Was Increase.
            teams.get(team_no).wickets++;

            // Add Wicket And One Ball In Bowler
            teams.get(1 - team_no).teamPlayer.get(strikeBowler).wickets++;

            // Check Team Was All Out Or Not.
            if(teams.get(team_no).wickets == 10){
                return 1;
            }

            // Set New Batsman Out by status DidNotBat To NotOut
            teams.get(team_no).teamPlayer.get(strikerBatsman).outBy = "Not Out";
        }
        else if(run == 8)   // For Wide Ball
        {
            // For Printing Commentary
            System.out.println("Wide");

            // Add 1 In Team Run Counter And Add 1 Wide Run Counter.
            teams.get(team_no).teamRun++;
            teams.get(team_no).wideRun++;
            return 2;
        }
        else if(run == 9)   // For No Ball
        {
            // In No Ball only 0-4 and 6 possible
            int noBallRun = (int)(Math.random() * 6);
            if(noBallRun == 5)noBallRun = 6;        // Because 5 is not possible so if random run change to 6.

            // Add Run And Played Ball in current Player.
            teams.get(team_no).teamPlayer.get(strikerBatsman).run += noBallRun;
            teams.get(team_no).teamPlayer.get(strikerBatsman).playedBall++;

            // For Printing Commentary
            if(noBallRun == 0)
            {
                System.out.println("No Run");
            }
            else if(noBallRun == 1)
            {
                System.out.println("No Ball and " + teams.get(team_no).teamPlayer.get(strikerBatsman).name + " got 1 Runs");
            }
            else if(noBallRun == 2)
            {
                System.out.println("No Ball and " + teams.get(team_no).teamPlayer.get(strikerBatsman).name + " got 2 Runs");
            }
            else if(noBallRun == 3)
            {
                System.out.println("No Ball and " + teams.get(team_no).teamPlayer.get(strikerBatsman).name + " got 3 Runs.");
            }
            else if(noBallRun == 4)
            {
                System.out.println("No Ball and " + teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Hit Four!");
            }
            else if(noBallRun == 6)
            {
                System.out.println("No Ball and " + teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Hit Six!!");
            }

            noBallRun++;                                          // 1 Extra run on No ball.
            teams.get(team_no).teamRun += noBallRun;              // Add Runs in Total Score in Team.
            teams.get(team_no).noBallRun++;                       // Add 1 No Ball Run Counter.

            return 2;
        }
        // Wide + Four is only option for 5 runs.
        else if(run == 5)
        {
            // For Printing Commentary
            System.out.println("Wide Ball And Four");

            teams.get(team_no).teamRun+=5;        // Add 5 In Team Run Counter.
            teams.get(team_no).wideRun+=5;        // Add 5 In Wide Run Counter.
            return 2;
        }
        // All Even Runs Where Strike was Not Changed.
        else if(run % 2 == 0)
        {
            // Add Run And Played Ball in current Player.
            teams.get(team_no).teamPlayer.get(strikerBatsman).run += run;
            teams.get(team_no).teamPlayer.get(strikerBatsman).playedBall++;

            // Add Runs in Total Score in Team.
            teams.get(team_no).teamRun += run;

            // And One Ball In Bowler
            teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver += 0.1;

            // Format over.
            dfSharp.format(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver);

            // For Printing Commentary
            if(run == 0)
            {
                System.out.println("No Run");
            }
            else if(run == 2)
            {
                System.out.println(teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Got 2 Runs");
            }
            else if(run == 4)
            {
                System.out.println(teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Hit Four!");
            }
            else
            {
                System.out.println(teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Hit Six!!");
            }
        }
        // All Odd Runs Where Strike Was Changed.
        else
        {
            // Add Run And Played Ball in current Player.
            teams.get(team_no).teamPlayer.get(strikerBatsman).run += run;
            teams.get(team_no).teamPlayer.get(strikerBatsman).playedBall++;

            // Add Runs in Total Score in Team.
            teams.get(team_no).teamRun += run;

            // And One Ball In Bowler
            teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver += 0.1;

            // Format Over
            dfSharp.format(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver);

            // For Printing Commentary
            if(run == 1)
            {
                System.out.println(teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Got 1 Runs");
            }
            else
            {
                System.out.println(teams.get(team_no).teamPlayer.get(strikerBatsman).name + " Got 3 Runs");
            }

            // Every 1,3 run Strike Was Changed, So Swap Current Batsman.
            int temp = strikerBatsman;
            strikerBatsman = nonStrikerBatsman;
            nonStrikerBatsman = temp;
        }
        return 0;
    }

    // Stating a Game
    public void startGame(ArrayList<Team> teams, int totalOver)
    {
        System.out.println("New Game Start! \n\nCommentary");

        // Iterate Over Both Team
        for(byte team_no = 0; team_no < 2; team_no++)
        {
            System.out.println("\n" + teams.get(team_no).teamName +" Inning Start");

            // Set Striker Batsman to 0 and non Striker Batsman to 1
            strikerBatsman = 0;
            nonStrikerBatsman = 1;

            // Set Not Out of Striker and Non Striker Batsman
            teams.get(team_no).teamPlayer.get(strikerBatsman).outBy = "Not Out";
            teams.get(team_no).teamPlayer.get(nonStrikerBatsman).outBy = "Not Out";

            // Set Inning Over to False For New Team.
            inning_over = false;

            // Iterate Over Total Overs
            for(byte i = 1; i <= totalOver; i++)
            {
                // Select Strike Bowler.
                strikeBowler = 10 - (i % 5);

                // For One Over
                for(byte j = 1; j <= 6; j++)
                {
                    // For Printing Commentary
                    System.out.println("Over - " + (i - 1) + "." + j);
                    System.out.print(teams.get(1 - team_no).teamPlayer.get(strikeBowler).name + " to " + teams.get(team_no).teamPlayer.get(strikerBatsman).name + ": ");

                    // State is 0 , 1, 2
                    int state = randomRun(teams,team_no);
                    if(state == 1) {                            // For Check if All 10 wickets Gone or Not
                        inning_over = true;
                        break;
                    }
                    else if(state == 2) j--;                    // For Wide Or No Ball.
                }

                // Check Inning Over Or Not
                if(inning_over)
                {
                    teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver = Float.parseFloat(dfSharp.format(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver));
                    if(Math.round(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver) != Math.floor(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver))
                    {
                        teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver = Math.round(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver);
                    }
                    break;
                }

                // Add Over in Bowler.
                teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver = Math.round(teams.get(1 - team_no).teamPlayer.get(strikeBowler).playerOver);


                // Swap Current Batsman After Finish A Over.
                int temp = strikerBatsman;
                strikerBatsman = nonStrikerBatsman;
                nonStrikerBatsman = temp;
            }

            System.out.println("\n" + teams.get(team_no).teamName +" Inning was over");
            System.out.println("------------------------------------------------------------------------------------------------------");
        }
    }
}
