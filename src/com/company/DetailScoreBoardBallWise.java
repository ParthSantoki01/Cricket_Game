package com.company;

import java.util.ArrayList;
import java.util.List;

public class DetailScoreBoardBallWise {
    private final int scoreBoardId;
    private final int matchOver;
    private Team tossWinningTeam;
    private String tossWinningTeamChoice;
    private Team firstBattingTeam;
    private Team secondBattingTeam;
    private String winningStatus = "Match Tie";
    private List<PerBallDetail> firstInningEveryBallDetails = new ArrayList<>();
    private List<PerBallDetail> secondInningEveryBallDetails = new ArrayList<>();

    public void setTeams(Team firstBattingTeam, Team secondBattingTeam) {
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
    }

    public void setTossWinningTeam(Team tossWinningTeam)
    {
        this.tossWinningTeam = tossWinningTeam;
    }

    public Team getTossWinningTeam() {
        return tossWinningTeam;
    }

    public void setTossWinningTeamChoice(String tossWinningTeamChoice)
    {
        this.tossWinningTeamChoice = tossWinningTeamChoice;
    }

    public String getTossWinningTeamChoice() {
        return tossWinningTeamChoice;
    }

    public String getWinningStatus() {
        return winningStatus;
    }

    public void updateStatesScoreBoard(int inning_no, String outComeStatus, Player batsman, Player bowler, int overNumber, int ballNumberInOver, int runScore)
    {
        if(inning_no == 1)
        {
            firstInningEveryBallDetails.add(new PerBallDetail(batsman.getId(),bowler.getId(),overNumber,ballNumberInOver,outComeStatus,runScore));
        }
        else
        {
            secondInningEveryBallDetails.add(new PerBallDetail(batsman.getId(),bowler.getId(),overNumber,ballNumberInOver,outComeStatus,runScore));
        }
    }

    public DetailScoreBoardBallWise(int scoreBoardId, int matchOver) {
        this.scoreBoardId = scoreBoardId;
        this.matchOver = matchOver;
    }

    public void selectWinningTeam()
    {
        if(secondBattingTeam.getRunScore() == firstBattingTeam.getRunScore())
        {
            System.out.println("Match Tie");
        }
        else if(firstBattingTeam.getRunScore() > secondBattingTeam.getRunScore())
        {
            int runDiff = firstBattingTeam.getRunScore() - secondBattingTeam.getRunScore();
            winningStatus = firstBattingTeam.getName() + " win by " + runDiff + " runs";
        }
        else
        {
            int leftWicket = 10 - secondBattingTeam.getWickets();
            winningStatus = secondBattingTeam.getName() + " win by " + leftWicket + " Wickets";
        }
    }
    public void printScoreBoardBallWise() {

        System.out.println(tossWinningTeam.getName() + " win the toss and choose the " + tossWinningTeamChoice + " first\n");

        System.out.println("\n First Inning \n");
        for(int i = 0; i < firstInningEveryBallDetails.size(); i++)
        {
            System.out.println(firstInningEveryBallDetails.get(i).getOverNumber() + "." +
                    firstInningEveryBallDetails.get(i).getBallNumberInOver() +
                    "\t Batsman-" + firstBattingTeam.getPlayerList().get(firstInningEveryBallDetails.get(i).getBatsmanId()).getName() +
                    "\t Bowler-" + secondBattingTeam.getPlayerList().get(firstInningEveryBallDetails.get(i).getBowlerId()).getName() +
                    "\t Outcome -> " + firstInningEveryBallDetails.get(i).getBallOutcome() +
                    "\t TeamScore -> " + firstInningEveryBallDetails.get(i).getTeamScoreUpToThisBall()
            );
        }

        System.out.println("\n Second Inning \n");
        for(int i = 0; i < secondInningEveryBallDetails.size(); i++)
        {
            System.out.println(secondInningEveryBallDetails.get(i).getOverNumber() + "." +
                    secondInningEveryBallDetails.get(i).getBallNumberInOver() +
                    "\t Batsman-" + secondBattingTeam.getPlayerList().get(secondInningEveryBallDetails.get(i).getBatsmanId()).getName() +
                    "\t Bowler-" + firstBattingTeam.getPlayerList().get(secondInningEveryBallDetails.get(i).getBowlerId()).getName() +
                    "\t Outcome -> " + secondInningEveryBallDetails.get(i).getBallOutcome() +
                    "\t TeamScore -> " + secondInningEveryBallDetails.get(i).getTeamScoreUpToThisBall()
            );
        }
    }
}
