package com.company.ScoreBoards;

import com.company.Player;
import com.company.Team;
import java.util.ArrayList;
import java.util.List;

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
    public void setTossWinningTeamChoice(String tossWinningTeamChoice){
        this.tossWinningTeamChoice = tossWinningTeamChoice;
    }

    public void updateDetailsScoreBoardBallWise(PerBallDetails ballDetails, int inningNo) {
        detailScoreBoardBallWise.updateDetailsScoreBoardBallWise(ballDetails, inningNo);
    }
    public void updateDetailsScoreBoardOverWise(PerOverDetails overDetails,int inningNo) {
        detailScoreBoardOverWise.updateDetailsScoreBoardOverWise(overDetails, inningNo);
    }
    public void selectWinningTeam() {
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

    private void printTeam(Team team) {
        System.out.println("Team - " + team.getName());
        System.out.println("\nTeam Players Details");
        for(byte i = 0; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            System.out.print(player.getName() + " \t\t ");
            System.out.print(player.getRole() + " ");
            System.out.println("\t\tJersey number " + player.getJerseyNumber() + " ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------\n");
    }
    private void printSummery(Team team) {
        System.out.println("\n----------------------------------");
        System.out.println("Team :- " + team.getName());
        System.out.println("----------------------------------");
        System.out.println("Summary");
        System.out.println("Runs : \t\t" + team.getRunScore());
        System.out.println("Over: \t\t" + team.getPlayedOvers());
        System.out.println("Wickets:\t" + team.getWickets());
        System.out.println("Wide: \t\t" + team.getWideRuns());
        System.out.println("No Ball:\t" + team.getNoBalls());
        System.out.println("-----------------\n");
        System.out.println("Score Board");
        System.out.println("-----------------");
        System.out.println("Batting Performance");
        for(byte i = 0; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            float strikeRate = 0;
            if(player.getBallsFaced() != 0)   strikeRate = ((float) player.getRunScore() / (float) player.getBallsFaced()) * 100;

            System.out.print(player.getName() + " \t Run -> " + player.getRunScore() + "\t Ball -> " + player.getBallsFaced() + "\t Strike Rate -> " + String.format("%.2f", strikeRate));

            System.out.println("\t Batting Status -> " + ((player.getBallsFaced() == 0)?"Did Not Bat":(player.isBatsmanDismissal()?"Out":"Not Out")));
        }

        System.out.println("\nBowling Performance");
        for(byte i = 6; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            float economyRate = 0;
            if(player.getWicketsTaken() != 0)
            {
                economyRate = (float) ((player.getBallsDelivered())  / player.getWicketsTaken());
            }
            System.out.println(player.getName() + " \t Wickets -> " + player.getWicketsTaken() + "\t over -> " + player.getOversDelivered() + " \t Economy Rate -> " + String.format("%.2f", economyRate) + " \t Given Run -> " + player.getRunsGiven());
        }
    }
    private void printEveryOverDetails(List<PerOverDetails> inningsPerOverDetail) {
        for(PerOverDetails perOverDetail : inningsPerOverDetail)
        {
            System.out.println("Over -> " + perOverDetail.getOverNumber() +  "\t Wickets -> " + perOverDetail.getNoOfWickets() + "\t Runs -> " + perOverDetail.getRunGiven());
        }
    }
    private void printEveryBallDetails(List<PerBallDetails> inningsPerBallDetail) {
        for (PerBallDetails everyBallDetail : inningsPerBallDetail) {
            System.out.println(everyBallDetail.getOverNumber() + "." +
                    everyBallDetail.getBallNumberInOver() +
                    "\t Batsman->" + everyBallDetail.getBatsman().getName() +
                    "\t Bowler->" + everyBallDetail.getBowler().getName() +
                    "\t Outcome -> " + everyBallDetail.getBallOutcome().getDisplayName() +
                    "\t TeamScore -> " + everyBallDetail.getTeamScoreUpToThisBall()
            );
        }
    }
    public void printWinningTeam() {
        selectWinningTeam();
        if(runMargin == 0 && wicketMargin == 0)
        {
            System.out.println("Match Tie");
        }
        else
        {
            System.out.print("Winning Team -> " + winningTeam.getName() + " \t");
            System.out.print("Run Margin -> " + runMargin + " \t");
            System.out.print("Wicket Margin -> " + wicketMargin);
        }
    }
    public void printScoreBoard() {
        List<Team> teamList = new ArrayList<>();
        teamList.add(firstBattingTeam);
        teamList.add(secondBattingTeam);

        List<List<PerBallDetails>> inningsPerBallDetails = new ArrayList<>();
        inningsPerBallDetails.add(detailScoreBoardBallWise.getFirstInningEveryBallDetails());
        inningsPerBallDetails.add(detailScoreBoardBallWise.getSecondInningEveryBallDetails());

        List<List<PerOverDetails>> inningsPerOverDetails = new ArrayList<>();
        inningsPerOverDetails.add(detailScoreBoardOverWise.getFirstInningOvers());
        inningsPerOverDetails.add(detailScoreBoardOverWise.getSecondInningOvers());

        System.out.println(" Toss Win - "+tossWinningTeam.getName() +  " \t Choice - " + tossWinningTeamChoice);
        for(Team team: teamList) printTeam(team);
        for(Team team:teamList) printSummery(team);
        for (List<PerOverDetails> inningsPerOverDetail : inningsPerOverDetails)printEveryOverDetails(inningsPerOverDetail);
        for (List<PerBallDetails> inningsPerBallDetail : inningsPerBallDetails)printEveryBallDetails(inningsPerBallDetail);
        printWinningTeam();
    }

    public ScoreBoard(int matchId, int numberOfOversInAnInning)
    {
        this.scoreBoardId = matchId;
        this.numberOfOversInAnInning = numberOfOversInAnInning;
        this.detailScoreBoardOverWise = new DetailScoreBoardOverWise(matchId,scoreBoardId);
        this.detailScoreBoardBallWise = new DetailScoreBoardBallWise(matchId,scoreBoardId);
    }
}
