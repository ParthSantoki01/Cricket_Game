package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private final int matchId;
    private Team tossWinningTeam;
    private Team firstBattingTeam;
    private Team secondBattingTeam;
    private Team winningTeam;
    private int runMargin = 0;
    private int wicketMargin = 0;
    private final int oversInInning;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;
    private final List<Over> overList = new ArrayList<>();

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
    public long getCreatedTime() {
        return createdTime;
    }
    public long getModifiedTime() {
        return modifiedTime;
    }
    public boolean isDeleted() {
        return deleted;
    }

    public List<Over> getOverList() {
        return overList;
    }

    public void setFirstBattingTeam(Team firstBattingTeam) {
        this.firstBattingTeam = firstBattingTeam;
    }
    public void setSecondBattingTeam(Team secondBattingTeam) {
        this.secondBattingTeam = secondBattingTeam;
    }
    public void setTossWinningTeam(Team tossWinningTeam)
    {
        this.tossWinningTeam = tossWinningTeam;
    }
    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
    }
    public void setRunMargin(int runMargin) {
        this.runMargin = runMargin;
    }
    public void setWicketMargin(int wicketMargin) {
        this.wicketMargin = wicketMargin;
    }

    public void printScoreBoard() {

        System.out.println("Toss win - " + tossWinningTeam.getName());
        System.out.println("First Batting Team - " + firstBattingTeam.getName());

        List<Team> teamList = new ArrayList<>();
        teamList.add(firstBattingTeam);
        teamList.add(secondBattingTeam);

        for(Team team: teamList) printTeam(team);
        for(Over over : overList)
        {
            System.out.println("OverId -> " + over.getOverId() +" Over -> " + over.getOverNumber() + " Bowler - " + over.getBowler().getName());
            for(Ball ball: over.getBallList())
            {
                System.out.println("BallId -> " + ball.getBallId() + " overId-> " + ball.getOverId() + " Ball -> " + ball.getBallNumberInOver() + "\t" + ball.getBatsman().getName() + "\t" + ball.getBallOutcome());
            }
        }
        printWinningTeam();
    }
    private void printTeam(Team team) {
        System.out.println("Team - " + team.getName());
        System.out.println("\nTeam Players Details");
        System.out.println("Runs - " + team.getRunScore());
        System.out.println("Wickets - " + team.getWickets());
        for(byte i = 0; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            System.out.print(player.getPlayerId() + "  ");
            System.out.print(player.getName() + "  ");
            System.out.print(player.getTeamId() + "  ");
            System.out.print(player.getBattingOrder() + "  ");
            System.out.println(player.getRole());
        }
    }
    private void printWinningTeam() {
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

    public Match(int matchId, int oversInInning)
    {
        this.matchId = matchId;
        this.oversInInning = oversInInning;
        this.createdTime = System.currentTimeMillis();
        this.modifiedTime = System.currentTimeMillis();
        this.deleted = false;
    }
}