package com.company.ScoreBoards;

import com.company.Player;
import com.company.Utility.PossibleOutputOfBall;
import com.company.Team;

public class ScoreBoard {
    private final int scoreBoardId;
    private Team tossWinningTeam;
    private String tossWinningTeamChoice;
    private Team firstBattingTeam;
    private Team secondBattingTeam;
    private Team winningTeam;
    private int runMargin = 0;
    private int wicketMargin = 0;
    private final int numberOfOversInAnInning;
    private final DetailScoreBoardBallWise detailScoreBoardBallWise;
    private final DetailScoreBoardOverWise detailScoreBoardOverWise;

    public void setTeams(Team firstBattingTeam, Team secondBattingTeam) {
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
    }
    public void setTossWinningTeam(Team tossWinningTeam)
    {
        this.tossWinningTeam = tossWinningTeam;
    }
    public void setTossWinningTeamChoice(String tossWinningTeamChoice)
    {
        this.tossWinningTeamChoice = tossWinningTeamChoice;
    }

    public void selectWinningTeam()
    {
        if(firstBattingTeam.getRunScore() > secondBattingTeam.getRunScore())
        {
            winningTeam = firstBattingTeam;
            runMargin = firstBattingTeam.getRunScore() - secondBattingTeam.getRunScore();
        }
        else if(firstBattingTeam.getRunScore() < secondBattingTeam.getRunScore())
        {
            winningTeam = secondBattingTeam;
            wicketMargin = 10 - secondBattingTeam.getWickets();
        }
    }

    public void printWinningTeam()
    {
        selectWinningTeam();
        System.out.print("Winning Team -> " + winningTeam.getName() + " \t");
        System.out.print("Run Margin -> " + runMargin + " \t");
        System.out.print("Wicket Margin -> " + wicketMargin);
    }
    public void updateDetailsScoreBoardBallWise(PerBallDetails ballDetails, int inningNo)
    {
        detailScoreBoardBallWise.updateDetailsScoreBoardBallWise(ballDetails, inningNo);
    }
    public void updateDetailsScoreBoardOverWise(PerOverDetails overDetails,int inningNo)
    {
        detailScoreBoardOverWise.updateDetailsScoreBoardOverWise(overDetails, inningNo);
    }

    public void printEveryOverDetails()
    {
        System.out.println("\n First Inning \n");
        for(PerOverDetails perOverDetail : detailScoreBoardOverWise.getFirstInningOvers())
        {
            System.out.println("Over -> " + perOverDetail.getOverNumber() +  "\t Wickets -> " + perOverDetail.getNoOfWickets() + "\t Runs -> " + perOverDetail.getRunGiven());
        }

        System.out.println("\n Second Inning \n");
        for(PerOverDetails perOverDetail : detailScoreBoardOverWise.getSecondInningOvers())
        {
            System.out.println("Over -> " + perOverDetail.getOverNumber() +  "\t Wickets -> " + perOverDetail.getNoOfWickets() + "\t Runs -> " + perOverDetail.getRunGiven());
        }
    }
    public void printEveryBallDetails() {
        System.out.println("\n First Inning \n");
        for (PerBallDetails everyBallDetail : detailScoreBoardBallWise.getFirstInningEveryBallDetails()) {
            System.out.println(everyBallDetail.getOverNumber() + "." +
                    everyBallDetail.getBallNumberInOver() +
                    "\t Batsman->" + everyBallDetail.getBatsman().getName() +
                    "\t Bowler->" + everyBallDetail.getBowler().getName() +
                    "\t Outcome -> " + everyBallDetail.getBallOutcome().getDisplayName() +
                    "\t TeamScore -> " + everyBallDetail.getTeamScoreUpToThisBall()
            );
        }

        System.out.println("\n Second Inning \n");
        for (PerBallDetails everyBallDetail : detailScoreBoardBallWise.getSecondInningEveryBallDetails()) {
            System.out.println(everyBallDetail.getOverNumber() + "." +
                    everyBallDetail.getBallNumberInOver() +
                    "\t Batsman->" + everyBallDetail.getBatsman().getName() +
                    "\t Bowler->" + everyBallDetail.getBowler().getName() +
                    "\t Outcome -> " + everyBallDetail.getBallOutcome().getDisplayName() +
                    "\t TeamScore -> " + everyBallDetail.getTeamScoreUpToThisBall()
            );
        }
    }

    public ScoreBoard(int matchId, int numberOfOversInAnInning)
    {
        this.scoreBoardId = matchId;
        this.numberOfOversInAnInning = numberOfOversInAnInning;
        this.detailScoreBoardOverWise = new DetailScoreBoardOverWise(matchId,scoreBoardId);
        this.detailScoreBoardBallWise = new DetailScoreBoardBallWise(matchId,scoreBoardId);
    }
}
