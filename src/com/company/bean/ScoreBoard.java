package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private final int matchId;
    private Team tossWinningTeam;
    private Team firstBattingTeam;
    private Team secondBattingTeam;
    private Team winningTeam;
    private int runMargin = 0;
    private int wicketMargin = 0;
    private final int oversInInning;
    private final BallWiseScoreBoard ballWiseScoreBoard;
    private final OverWiseScoreBoard overWiseScoreBoard;

    public int getMatchId() {
        return matchId;
    }
    public Team getTossWinningTeam() {
        return tossWinningTeam;
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
    public int getOversInInning() {
        return oversInInning;
    }
    public BallWiseScoreBoard getBallWiseScoreBoard() {
        return ballWiseScoreBoard;
    }
    public OverWiseScoreBoard getOverWiseScoreBoard() {
        return overWiseScoreBoard;
    }

    public void setTeams(Team firstBattingTeam, Team secondBattingTeam) {
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
    }
    public void setTossWinningTeam(Team tossWinningTeam)
    {
        this.tossWinningTeam = tossWinningTeam;
    }
    public void addBallStats(BallStats ballDetails, int inningNo) {
        ballWiseScoreBoard.addBallStats(ballDetails, inningNo);
    }
    public void addOverStats(OverStats overDetails, int inningNo) {
        overWiseScoreBoard.addOverStats(overDetails, inningNo);
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
            if(player.getWicketTakenByBowler() == null) wicketTakenByPlayerName = "Not Name";
            else wicketTakenByPlayerName = player.getWicketTakenByBowler().getName();
            System.out.println(
                    player.getPlayerId() + " " +
                    player.getRunScore() + " " +
                    player.getRunsGiven() + " " +
                    player.getBallsFaced() + " " +
                    player.getBallsBowled() + " " +
                    player.getWicketsTaken() + " " +
                    wicketTakenByPlayerName + " " +
                    player.getDotBallsPlayed() + " " +
                    player.getOneRunsScored() + " " +
                    player.getTwoRunsScored() + " " +
                    player.getThreeRunsScored() + " " +
                    player.getFourRunsScored() + " " +
                    player.getSixRunsScored() + " Given " +
                    player.getDotBallsBowled() + " " +
                    player.getOneRunBallsBowled() + " " +
                    player.getTwoRunBallsBowled() + " " +
                    player.getThreeRunBallsBowled() + " " +
                    player.getFourRunBallsBowled() + " " +
                    player.getSixRunBallsBowled() + " " +
                    player.getWideBallsBowled() + " " +
                    player.getNoBallsBowled()
            );
        }
        System.out.println("-----------------\n");
    }
    private void printEveryOverDetails(List<OverStats> inningsPerOverDetail) {
        for(OverStats perOverDetail : inningsPerOverDetail)
        {
            System.out.println(
                    perOverDetail.getOverNumber() + " " +
                    perOverDetail.getWickets() + " " +
                    perOverDetail.getRuns() + " " +
                    perOverDetail.getPlayedDotBalls() + " " +
                    perOverDetail.getOneRunsScored() + " " +
                    perOverDetail.getTwoRunsScored() + " " +
                    perOverDetail.getThreeRunsScored() + " " +
                    perOverDetail.getFourRunsScored() + " " +
                    perOverDetail.getSixRunsScored() + " " +
                    perOverDetail.getWideBalls() + " " +
                    perOverDetail.getNoBalls() + " " +
                    perOverDetail.getBowlerId() + " " +
                    perOverDetail.getTeamId()
            );
        }
    }
    private void printEveryBallDetails(List<BallStats> inningsPerBallDetail) {
        for (BallStats everyBallDetail : inningsPerBallDetail) {
            System.out.println(
                    everyBallDetail.getOverNumber() + "." +
                    everyBallDetail.getBallNumberInOver() + " " +
                    everyBallDetail.getBatsman().getName() + " " +
                    everyBallDetail.getBowler().getName() + " " +
                    everyBallDetail.getBallOutcome().getDisplayName() + " " +
                    everyBallDetail.getTeamScore() + " " +
                    everyBallDetail.getTeamWickets()
            );
        }
    }
    private void printWinningTeam() {
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

        List<List<BallStats>> inningsPerBallDetails = new ArrayList<>();
        inningsPerBallDetails.add(ballWiseScoreBoard.getFirstInningBallStats());
        inningsPerBallDetails.add(ballWiseScoreBoard.getSecondInningBallStats());

        List<List<OverStats>> inningsPerOverDetails = new ArrayList<>();
        inningsPerOverDetails.add(overWiseScoreBoard.getFirstInningOverStats());
        inningsPerOverDetails.add(overWiseScoreBoard.getSecondInningOverStats());

        for(Team team: teamList) printTeam(team);
        for(Team team:teamList) printSummery(team);
        for (List<OverStats> inningsPerOverDetail : inningsPerOverDetails)printEveryOverDetails(inningsPerOverDetail);
        for (List<BallStats> inningsPerBallDetail : inningsPerBallDetails)printEveryBallDetails(inningsPerBallDetail);
        printWinningTeam();
    }

    public ScoreBoard(int matchId, int oversInInning)
    {
        this.matchId = matchId;
        this.oversInInning = oversInInning;
        this.overWiseScoreBoard = new OverWiseScoreBoard(matchId);
        this.ballWiseScoreBoard = new BallWiseScoreBoard(matchId);
    }
}
