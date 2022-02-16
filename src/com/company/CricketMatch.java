package com.company;
import java.util.List;

import static java.lang.Math.max;

public class CricketMatch {
    private final int matchId;
    private final int matchOver;
    private Player strikerBatsman;
    private Player nonStrikerBatsman;
    private Player strikeBowler;
    private Team battingTeam;
    private Team bowlingTeam;

    public int getMatchId() {
        return matchId;
    }

    public int getMatchOver() {
        return matchOver;
    }

    private enum availableChoicesForTossWinningTeam {
        Batting,
        Bowling;
        public static availableChoicesForTossWinningTeam getRandomChoice() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
    private enum tossWinTeam {
        FirstTeam,
        SecondTeam;
        public static tossWinTeam getTossWinningTeam() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
    public void tossOfMatch(List<Team> teams)
    {
        System.out.println("\n-----------------------------------------------------------------------------");

        tossWinTeam tossWinningTeam = tossWinTeam.getTossWinningTeam();
        availableChoicesForTossWinningTeam tossWinningTeamChoice = availableChoicesForTossWinningTeam.getRandomChoice();
        if(tossWinningTeam == tossWinTeam.FirstTeam)
        {
            if(tossWinningTeamChoice == availableChoicesForTossWinningTeam.Batting)
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
            if(tossWinningTeamChoice == availableChoicesForTossWinningTeam.Bowling)
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
        System.out.println("-----------------------------------------------------------------------------");
    }


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
    private void nextStrikerBatsman()
    {
        strikerBatsman = battingTeam.getPlayerList().get(max(strikerBatsman.getBattingOrder(),nonStrikerBatsman.getBattingOrder()) + 1);
    }
    private void updateRunInScoreBoard(int run)
    {
//        strikerBatsman.addRunScore(run);
        strikerBatsman.addBallsFaced();
        strikerBatsman.updatePlayerRunStates(run);
        battingTeam.setRunScore(run);
        strikeBowler.addRunsGiven(run);
        if(run % 2 == 1)swapPlayer();
    }

    private void afterWicketGone()
    {
        strikerBatsman.addBallsFaced();
        strikerBatsman.setBatsmanDismissal(true);
        strikerBatsman.setWicketTakenBy(strikeBowler.getName());
        battingTeam.addWickets();
        strikeBowler.addWicketsTaken();
        PrintingClass.printWicketCommentary(battingTeam,strikerBatsman);

        if(battingTeam.getWickets() >= 10){
            return;
        }
        nextStrikerBatsman();
    }
    private void afterWide()
    {
        System.out.println("Wide");
        battingTeam.setRunScore(1);
        battingTeam.addWideRuns(1);
        strikeBowler.addRunsGiven(1);
        int extraByeRun = (int)(Math.random() * 5);
        updateRunInScoreBoard(extraByeRun + 1);
    }
    private void afterNoBall()
    {
        System.out.print("No Ball and ");
        battingTeam.setRunScore(1);
        battingTeam.addNoBalls();
        strikeBowler.addRunsGiven(1);
        int noBallRun = (int)(Math.random() * 6);
        if(noBallRun == 5)noBallRun = 6; // No Ball And Wide + 4, Not happen.
        updateRunInScoreBoard(noBallRun);
        PrintingClass.printRunCommentary(battingTeam ,noBallRun);
    }

    private String randomGenerator() {
        possibleOutputOfBall outcome = possibleOutputOfBall.getOutcomeOfBall();
        return outcome.getDisplayName();
    }

    private void innings(int inning_no)
    {
        strikerBatsman = battingTeam.getPlayerList().get(0);
        nonStrikerBatsman = battingTeam.getPlayerList().get(1);
        for(byte i = 0; i < matchOver; i++)
        {
            strikeBowler = bowlingTeam.getPlayerList().get(10 - (i % 5));
            for(byte j = 1; j <= 6; j++)
            {
                String generatedOutput = randomGenerator();
                if(generatedOutput.equals("Wicket"))
                {
                    afterWicketGone();
                    if(battingTeam.getWickets() >= 10){
                        return;
                    }
                    battingTeam.addBallsFaced();
                    strikeBowler.addBallsDelivered();
                }
                else if(generatedOutput.equals("Wide"))
                {
                    afterWide();
                    j--;
                }
                else if(generatedOutput.equals("No Ball"))
                {
                    afterNoBall();
                    j--;
                }
                else
                {
                    int runCount = Integer.parseInt(String.valueOf(generatedOutput.charAt(0)));
                    updateRunInScoreBoard(runCount);
                    PrintingClass.printRunCommentary(battingTeam, runCount);
                    battingTeam.addBallsFaced();
                    strikeBowler.addBallsDelivered();
                }
                if((inning_no == 2) && (bowlingTeam.getRunScore() < battingTeam.getRunScore()))
                {
                    return;
                }
            }
            swapPlayer();
        }
    }

    public void startGame()
    {
        System.out.println("New Game Start!");
        innings(1);
        System.out.println("\n" + battingTeam.getName() +" Inning was over");
        swapTeam();
        innings(2);
        PrintingClass.winningTeam(bowlingTeam,battingTeam);
    }
    public CricketMatch(int matchOver, int matchId)
    {
        this.matchOver = matchOver;
        this.matchId = matchId;
    }
}