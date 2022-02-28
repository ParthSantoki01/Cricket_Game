package com.company.been;

import com.company.enums.availableChoicesForTossWinningTeam;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private final int matchId;
    private Team tossWinningTeam;
    private availableChoicesForTossWinningTeam tossWinningTeamChoice;
    private Team firstBattingTeam;
    private Team secondBattingTeam;
    private Team winningTeam;
    private int runMargin = 0;
    private int wicketMargin = 0;
    private final int numberOfOversInAnInning;
    private final DetailScoreBoardBallWise detailScoreBoardBallWise;
    private final DetailScoreBoardOverWise detailScoreBoardOverWise;

    public int getMatchId() {
        return matchId;
    }
    public Team getTossWinningTeam() {
        return tossWinningTeam;
    }
    public String getTossWinningTeamChoice() {
        return String.valueOf(tossWinningTeamChoice);
    }
    public Team getFirstBattingTeam() {
        return firstBattingTeam;
    }
    public Team getSecondBattingTeam() {
        return secondBattingTeam;
    }
    public Team getWinningTeam() {
        return winningTeam;
    }
    public int getRunMargin() {
        return runMargin;
    }
    public int getWicketMargin() {
        return wicketMargin;
    }
    public int getNumberOfOversInAnInning() {
        return numberOfOversInAnInning;
    }


    public void setTeams(Team firstBattingTeam, Team secondBattingTeam) {
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
    }
    public void setTossWinningTeam(Team tossWinningTeam)
    {
        this.tossWinningTeam = tossWinningTeam;
    }
    public void setTossWinningTeamChoice(availableChoicesForTossWinningTeam tossWinningTeamChoice){
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
        System.out.println("No Ball:\t" + team.getNoBallRuns());
        System.out.println("-----------------\n");
        System.out.println("Score Board\n");
        for(byte i = 0; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            float strikeRate = 0;
            if(player.getBallsFaced() != 0)   strikeRate = ((float) player.getRunScore() / (float) player.getBallsFaced()) * 100;

            String wicketTakenByPlayerName;
            if(player.getWicketTakenBy() == null) wicketTakenByPlayerName = "Not Name";
            else wicketTakenByPlayerName = player.getWicketTakenBy().getName();
            System.out.println(player.getId() + " " + player.getRunScore() + " " + player.getRunsGiven() + " " +  player.getBallsFaced()
                    + " " + player.getBallsDelivered() + " " + player.getWicketsTaken() + " " + wicketTakenByPlayerName + " " +
                    player.getNoOfDotBall() + " " + player.getNoOf1sScore() + " " + player.getNoOf2sScore() + " " + player.getNoOf3sScore() + " " +
                    player.getNoOfFourScore() + " " + player.getNoOfSixScore() + " Given " + player.getNoOfDotBallGiven() + " " +
                    player.getNoOf1sGiven() + " " + player.getNoOf2sGiven() + " " + player.getNoOf3sGiven() + " " + player.getNoOfFourGiven() + " " +
                    player.getNoOfSixGiven() + " " + player.getWideBallDelivered() + " " + player.getNoBallDelivered()
            );
        }
        System.out.println("-----------------\n");
    }
    private void printEveryOverDetails(List<PerOverDetails> inningsPerOverDetail) {
        for(PerOverDetails perOverDetail : inningsPerOverDetail)
        {
            System.out.println(
                    perOverDetail.getOverNumber() + " " +
                    perOverDetail.getNoOfWickets() + " " +
                    perOverDetail.getRuns() + " " +
                    perOverDetail.getNoOfDotBall() + " " +
                    perOverDetail.getNoOf1s() + " " +
                    perOverDetail.getNoOf2s() + " " +
                    perOverDetail.getNoOf3s() + " " +
                    perOverDetail.getNoOfFour() + " " +
                    perOverDetail.getNoOfSix() + " " +
                    perOverDetail.getWideBall() + " " +
                    perOverDetail.getNoBall() + " " +
                    perOverDetail.getBowlerId()
            );
        }
    }
    private void printEveryBallDetails(List<PerBallDetails> inningsPerBallDetail) {
        for (PerBallDetails everyBallDetail : inningsPerBallDetail) {
            System.out.println(
                    everyBallDetail.getOverNumber() + "." +
                    everyBallDetail.getBallNumberInOver() + " " +
                    everyBallDetail.getBatsman().getName() + " " +
                    everyBallDetail.getBowler().getName() + " " +
                    everyBallDetail.getBallOutcome().getDisplayName() + " " +
                    everyBallDetail.getTeamScoreUpToThisBall() + " " +
                    everyBallDetail.getWicketsUpToThisBall()
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
        this.matchId = matchId;
        this.numberOfOversInAnInning = numberOfOversInAnInning;
        this.detailScoreBoardOverWise = new DetailScoreBoardOverWise(matchId,matchId);
        this.detailScoreBoardBallWise = new DetailScoreBoardBallWise(matchId,matchId);
    }
}
