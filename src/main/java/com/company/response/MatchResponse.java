package com.company.response;

public class MatchResponse {
    private int matchId;
    private String tossWinningTeamName;
    private String firstBattingTeamName;
    private String secondBattingTeamName;
    private String winningTeamName;
    private int runMargin;
    private int wicketMargin;
    private int oversInInning;


    public MatchResponse(int matchId, String tossWinningTeamName, String firstBattingTeamName, String secondBattingTeamName, String winningTeamName, int runMargin, int wicketMargin, int oversInInning) {
        this.matchId = matchId;
        this.tossWinningTeamName = tossWinningTeamName;
        this.firstBattingTeamName = firstBattingTeamName;
        this.secondBattingTeamName = secondBattingTeamName;
        this.winningTeamName = winningTeamName;
        this.runMargin = runMargin;
        this.wicketMargin = wicketMargin;
        this.oversInInning = oversInInning;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getTossWinningTeamName() {
        return tossWinningTeamName;
    }

    public String getFirstBattingTeamName() {
        return firstBattingTeamName;
    }

    public String getSecondBattingTeamName() {
        return secondBattingTeamName;
    }

    public String getWinningTeamName() {
        return winningTeamName;
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
}
