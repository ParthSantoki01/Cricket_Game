package com.company;
import java.util.List;

public class CricketMatch {
    Player strikerBatsman;
    Player nonStrikerBatsman;
    Player strikeBowler;
    Team battingTeam;
    Team bowlingTeam;
    Team firstBattingTeam;
    Team secondBattingTeam;
    private void swapPlayer()
    {
        Player temp = strikerBatsman;
        strikerBatsman = nonStrikerBatsman;
        nonStrikerBatsman = temp;
    }

    private void swapTeam()
    {
        Team temp = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
    }

    private void printWicketCommentary()
    {
        System.out.println("Wicket gone!");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(strikerBatsman.getName() + "\t" + strikerBatsman.getRole() + "\nRun - " + strikerBatsman.getRunScore() + "(" + strikerBatsman.getNoOfBallsPlayed() +")");
        System.out.println("Team Score - " + battingTeam.getRun() + " \t Wickets - " + battingTeam.getWickets());
        System.out.println("--------------------------------------------------------------------------");
    }

    private void printCommentary(int run)
    {
        System.out.println(run + "\t Total Run - " + battingTeam.getRun());
    }

    private void updateScoreBoard(int run)
    {
        strikerBatsman.addRunScore(run);
        strikerBatsman.addNoOfBallsPlayed(1);
        battingTeam.addRun(run);
        strikeBowler.addGivenRun(run);
        if(run % 2 == 1)swapPlayer();
    }

    private void wicketGone()
    {
        strikerBatsman.setBattingStatus(PlayerBattingStatus.status3.getValue());
        strikerBatsman.addNoOfBallsPlayed(1);
        battingTeam.addWickets(1);
        strikeBowler.addNumberOfWicketsTaken(1);
        printWicketCommentary();

        if(battingTeam.getWickets() >= 10){
            return;
        }

        if(strikerBatsman.getId() > nonStrikerBatsman.getId())
        {
            strikerBatsman = battingTeam.getPlayerList().get(strikerBatsman.getId() + 1);
        }
        else
        {
            strikerBatsman = battingTeam.getPlayerList().get(nonStrikerBatsman.getId() + 1);
        }

        strikerBatsman.setBattingStatus(PlayerBattingStatus.status2.getValue());
    }

    private String randomGenerator()
    {
        String[] possibleOutput = {"0","1","2","3","4","5","6","Wide","NoBall","Wicket"};
        return possibleOutput[(int)(Math.random() * 10)];
    }

    public void tossOfMatch(List<Team> teams)
    {
        System.out.println("\n-----------------------------------------------------------------------------");
        int tossWinTeam = (int)(Math.random()*2);
        int choiceWinTeam = (int)(Math.random()*2);
        if(tossWinTeam == 0)
        {
            if(choiceWinTeam == 0)
            {
                battingTeam = teams.get(0);
                bowlingTeam = teams.get(1);
                System.out.println(battingTeam.getName() + " have win a choose to bat first.");
            }
            else
            {
                battingTeam = teams.get(1);
                bowlingTeam = teams.get(0);
                System.out.println(bowlingTeam.getName() + " have win a choose to bowl first.");
            }
        }
        else
        {
            if(choiceWinTeam == 0)
            {
                battingTeam = teams.get(1);
                bowlingTeam = teams.get(0);
                System.out.println(battingTeam.getName() + " have win a choose to bat first.");
            }
            else
            {
                battingTeam = teams.get(0);
                bowlingTeam = teams.get(1);
                System.out.println(bowlingTeam.getName() + " have win a choose to bowl first.");
            }
        }
        firstBattingTeam = battingTeam;
        secondBattingTeam = bowlingTeam;

        System.out.println("-----------------------------------------------------------------------------");
    }

    private void innings(int totalOver, int inning_no)
    {
        strikerBatsman = battingTeam.getPlayerList().get(0);
        nonStrikerBatsman = battingTeam.getPlayerList().get(1);
        strikerBatsman.setBattingStatus(PlayerBattingStatus.status2.getValue());
        nonStrikerBatsman.setBattingStatus(PlayerBattingStatus.status2.getValue());

        for(byte i = 1; i <= totalOver; i++)
        {
            strikeBowler = bowlingTeam.getPlayerList().get(10 - (i % 5));
            for(byte j = 1; j <= 6; j++)
            {
                System.out.print("Over - " + (i - 1) + "." + j + " --> ");

                String generatedOutput = randomGenerator();
                if(generatedOutput.equals("Wicket"))
                {
                    wicketGone();
                    if(battingTeam.getWickets() >= 10){
                        return;
                    }
                    battingTeam.addNumberOfBallPlayed(1);
                    strikeBowler.addNoOfBallsBowled(1);
                }
                else if((generatedOutput.equals("Wide")) || (generatedOutput.equals("5")))
                {
                    System.out.println("Wide");
                    if(generatedOutput.equals("Wide"))
                    {
                        battingTeam.addRun(1);
                        battingTeam.addWideRun(1);
                        strikeBowler.addGivenRun(1);
                    }
                    else
                    {
                        battingTeam.addRun(5);
                        battingTeam.addWideRun(5);
                        strikeBowler.addGivenRun(5);
                    }
                    j--;
                }
                else if(generatedOutput.equals("NoBall"))
                {
                    System.out.print("No Ball and ");
                    battingTeam.addRun(1);
                    battingTeam.addNoBallRun(1);
                    strikeBowler.addGivenRun(1);
                    int noBallRun = (int)(Math.random() * 6);
                    if(noBallRun == 5)noBallRun = 6; // No Ball And Wide + 4, Not happen.
                    updateScoreBoard(noBallRun);
                    printCommentary(noBallRun);
                    j--;
                }
                else
                {
                    int runCount = Integer.parseInt(generatedOutput);
                    updateScoreBoard(runCount);
                    printCommentary(runCount);
                    battingTeam.addNumberOfBallPlayed(1);
                    strikeBowler.addNoOfBallsBowled(1);
                }
                if((inning_no == 2) && (bowlingTeam.getRun() < battingTeam.getRun()))
                {
                    return;
                }
            }
            swapPlayer();
        }
        System.out.println("\n" + battingTeam.getName() +" Inning was over");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public void startGame(int totalOver)
    {
        System.out.println("New Game Start!");

        innings(totalOver,1);
        swapTeam();
        innings(totalOver,2);
    }

    public void winningTeam()
    {
        System.out.println("\n-----------------------------------------------------------------------------");

        if(firstBattingTeam.getRun() == secondBattingTeam.getRun())
        {
            System.out.println("Match Tie");
        }
        else if(firstBattingTeam.getRun() > secondBattingTeam.getRun())
        {
            int runDiff = firstBattingTeam.getRun() - secondBattingTeam.getRun();
            System.out.println(firstBattingTeam.getName() + " was win by " + runDiff + " run");
        }
        else
        {
            int leftWicket = 10 - secondBattingTeam.getWickets();
            System.out.println(secondBattingTeam.getName() + " was win by " + leftWicket + " Wickets");
        }
        System.out.println("-----------------------------------------------------------------------------\n");
    }
}
