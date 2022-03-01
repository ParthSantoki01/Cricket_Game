package com.company.bean;
import com.company.enums.*;
import com.company.utility.UtilityOperations;
import com.company.databases.*;
import java.sql.Connection;
import java.util.List;
import static java.lang.Math.max;

public class CricketMatch {
    private final int matchId;
    private final int oversInInning;
    private Player strikerBatsman;
    private Player nonStrikerBatsman;
    private Player strikeBowler;
    private Team battingTeam;
    private Team bowlingTeam;
    private final ScoreBoard scoreBoard;

    public void tossOfMatch(List<Team> teams)
    {
        tossWinTeam tossWinningTeam = tossWinTeam.getTossWinningTeam();
        availableChoicesForTossWinningTeam tossWinningTeamChoice = availableChoicesForTossWinningTeam.getRandomChoice();
        switch (tossWinningTeam) {
            case FirstTeam -> {
                switch (tossWinningTeamChoice) {
                    case Batting -> {
                        battingTeam = teams.get(0);
                        bowlingTeam = teams.get(1);
                    }
                    default -> {
                        battingTeam = teams.get(1);
                        bowlingTeam = teams.get(0);
                    }
                }
                scoreBoard.setTossWinningTeam(teams.get(0));
            }
            default -> {
                switch (tossWinningTeamChoice) {
                    case Bowling -> {
                        battingTeam = teams.get(0);
                        bowlingTeam = teams.get(1);
                    }
                    default -> {
                        battingTeam = teams.get(1);
                        bowlingTeam = teams.get(0);
                    }
                }
                scoreBoard.setTossWinningTeam(teams.get(1));
            }
        }
        scoreBoard.setTeams(battingTeam,bowlingTeam);
    }

    private void nextStrikerBatsman()
    {
        strikerBatsman = battingTeam.getPlayerList().get(max(strikerBatsman.getBattingOrder(),nonStrikerBatsman.getBattingOrder()) + 1);
    }

    private void updateRunInScoreBoard(int runScore, int inningNo, int over, int j, PossibleOutputOfBall generatedOutput, BallStats ballStats)
    {
        strikerBatsman.addBallsFaced();
        strikerBatsman.updateBatsmanRunStates(runScore);
        battingTeam.addBallsFaced();
        battingTeam.setRunScore(runScore);
        strikeBowler.addBallsDelivered();
        strikeBowler.updateBowlerRunStates(runScore);
        ballStats.setBallOutcome(generatedOutput);
        ballStats.setTeamScore(battingTeam.getRunScore());
        ballStats.setTeamWickets(battingTeam.getWickets());
        if(runScore % 2 == 1)
        {
            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    private void afterWicketGone(int inningNo, int over, int j, BallStats ballStats)
    {
        strikerBatsman.addBallsFaced();
        strikerBatsman.setWicketTakenByBowler(strikeBowler);
        battingTeam.addBallsFaced();
        battingTeam.addWickets();
        strikeBowler.addBallsDelivered();
        strikeBowler.addWicketsTaken();
        ballStats.setBallOutcome(PossibleOutputOfBall.Wicket);
        ballStats.setTeamScore(battingTeam.getRunScore());
        ballStats.setTeamWickets(battingTeam.getWickets());
        if(battingTeam.getWickets() >= 10) return;
        nextStrikerBatsman();
    }
    private void afterWide(int inningNo, int over, int j, BallStats ballStats)
    {
        battingTeam.setRunScore(1);
        battingTeam.addWideRuns(1);
        strikeBowler.addRunsGiven(1);
        strikeBowler.addWideBallDelivered();
        ballStats.setBallOutcome(PossibleOutputOfBall.Wide);
        ballStats.setTeamScore(battingTeam.getRunScore());
        ballStats.setTeamWickets(battingTeam.getWickets());
    }
    private void afterNoBall(int inningNo, int over, int j, BallStats ballStats)
    {
        battingTeam.setRunScore(1);
        battingTeam.addNoBallRuns();
        strikeBowler.addRunsGiven(1);
        strikeBowler.addNoBallDelivered();
        ballStats.setBallOutcome(PossibleOutputOfBall.NoBall);
        ballStats.setTeamScore(battingTeam.getRunScore());
        ballStats.setTeamWickets(battingTeam.getWickets());
    }

    private PossibleOutputOfBall randomGenerator() {
        return PossibleOutputOfBall.getOutcomeOfBall(strikerBatsman.getRole());
    }

    private void startAnInning(int inningNo)
    {
        strikerBatsman = battingTeam.getPlayerList().get(0);
        nonStrikerBatsman = battingTeam.getPlayerList().get(1);
        for(byte over = 0; over < oversInInning; over++)
        {
            strikeBowler = bowlingTeam.getPlayerList().get(10 - (over % 5));
            OverStats overStats = new OverStats(battingTeam.getTeamId(),over + 1, strikeBowler.getPlayerId());
            byte ball = 0;
            for(byte j = 1; j <= 6; j++)
            {
                ball++;
                BallStats ballStats = new BallStats(battingTeam.getTeamId(),over,ball,strikerBatsman,strikeBowler);
                PossibleOutputOfBall generatedOutput = randomGenerator();
                if(generatedOutput.equals(PossibleOutputOfBall.Wicket))
                {
                    overStats.addWickets();
                    afterWicketGone(inningNo,over,j,ballStats);
                    if(battingTeam.getWickets() >= 10)
                    {
                        scoreBoard.addOverStats(overStats,inningNo);
                        scoreBoard.addBallStats(ballStats,inningNo);
                        return;
                    }
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.Wide))
                {
                    overStats.addWideBalls();
                    afterWide(inningNo,over,j,ballStats);
                    j--;
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.NoBall))
                {
                    overStats.addNoBalls();
                    afterNoBall(inningNo,over,j,ballStats);
                    j--;
                }
                else
                {
                    int runCount = Integer.parseInt(String.valueOf((generatedOutput.getDisplayName()).charAt(0)));
                    overStats.updateOverRunStates(runCount);
                    updateRunInScoreBoard(runCount,inningNo,over,j,generatedOutput,ballStats);
                }
                scoreBoard.addBallStats(ballStats,inningNo);
                if((inningNo == 2) && (bowlingTeam.getRunScore() < battingTeam.getRunScore()))
                {
                    scoreBoard.addOverStats(overStats,inningNo);
                    return;
                }
            }
            scoreBoard.addOverStats(overStats,inningNo);

            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    public void startGame(List<Team> teamList, Connection conn)
    {
        startAnInning(1);

        Team[] swappedTeams = UtilityOperations.swapTeam(battingTeam,bowlingTeam);
        battingTeam = swappedTeams[0];
        bowlingTeam = swappedTeams[1];

        startAnInning(2);

        scoreBoard.printScoreBoard();

        MatchDatabase.insertMatchDetails(scoreBoard,conn);
        InningStatsDatabase.insertInningsStats(teamList,matchId,conn);
        PlayerStatsDatabase.insertPlayersStats(teamList,matchId,conn);
        OverStatsDatabase.insertOversStats(scoreBoard.getOverWiseScoreBoard(),conn);
        BallStatsDatabase.insertBallStats(scoreBoard.getBallWiseScoreBoard(),conn);
    }
    public CricketMatch(int oversInInning, int matchId)
    {
        this.oversInInning = oversInInning;
        this.matchId = matchId;
        this.scoreBoard = new ScoreBoard(matchId,oversInInning);
    }
}