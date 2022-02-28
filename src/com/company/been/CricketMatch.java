package com.company.been;
import com.company.databases.UpdateInningStatsDatabase;
import com.company.databases.UpdateMatchDatabase;
import com.company.databases.UpdatePlayerStatsDatabase;
import com.company.enums.*;
import com.company.utility.UtilityOperations;

import java.sql.Connection;
import java.util.List;
import static java.lang.Math.max;

public class CricketMatch {
    private final int matchId;
    private final int numberOfOversInAnInning;
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
                        scoreBoard.setTossWinningTeamChoice(availableChoicesForTossWinningTeam.Batting);
                    }
                    default -> {
                        battingTeam = teams.get(1);
                        bowlingTeam = teams.get(0);
                        scoreBoard.setTossWinningTeamChoice(availableChoicesForTossWinningTeam.Bowling);
                    }
                }
                scoreBoard.setTossWinningTeam(teams.get(0));
            }
            default -> {
                switch (tossWinningTeamChoice) {
                    case Bowling -> {
                        battingTeam = teams.get(0);
                        bowlingTeam = teams.get(1);
                        scoreBoard.setTossWinningTeamChoice(availableChoicesForTossWinningTeam.Bowling);
                    }
                    default -> {
                        battingTeam = teams.get(1);
                        bowlingTeam = teams.get(0);
                        scoreBoard.setTossWinningTeamChoice(availableChoicesForTossWinningTeam.Batting);
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

    private void updateRunInScoreBoard(int runScore, int inningNo, int over, int j, PossibleOutputOfBall generatedOutput, PerBallDetails ballDetails)
    {
        strikerBatsman.addBallsFaced();
        strikerBatsman.updateBatsmanRunStates(runScore);
        battingTeam.addBallsFaced();
        battingTeam.setRunScore(runScore);
        strikeBowler.addBallsDelivered();
        strikeBowler.updateBowlerRunStates(runScore);
        ballDetails.setBallOutcome(generatedOutput);
        ballDetails.setTeamScoreUpToThisBall(battingTeam.getRunScore());
        ballDetails.setWicketsUpToThisBall(battingTeam.getWickets());
        if(runScore % 2 == 1)
        {
            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    private void afterWicketGone(int inningNo, int over, int j, PerBallDetails ballDetails)
    {
        strikerBatsman.addBallsFaced();
        strikerBatsman.setBatsmanDismissal(true);
        strikerBatsman.setWicketTakenBy(strikeBowler);
        battingTeam.addBallsFaced();
        battingTeam.addWickets();
        strikeBowler.addBallsDelivered();
        strikeBowler.addWicketsTaken();
        ballDetails.setBallOutcome(PossibleOutputOfBall.Wicket);
        ballDetails.setTeamScoreUpToThisBall(battingTeam.getRunScore());
        ballDetails.setWicketsUpToThisBall(battingTeam.getWickets());
        if(battingTeam.getWickets() >= 10) return;
        nextStrikerBatsman();
    }
    private void afterWide(int inningNo, int over, int j, PerBallDetails ballDetails)
    {
        battingTeam.setRunScore(1);
        battingTeam.addWideRuns(1);
        strikeBowler.addRunsGiven(1);
        strikeBowler.addWideBallDelivered();
        ballDetails.setBallOutcome(PossibleOutputOfBall.Wide);
        ballDetails.setTeamScoreUpToThisBall(battingTeam.getRunScore());
        ballDetails.setWicketsUpToThisBall(battingTeam.getWickets());
    }
    private void afterNoBall(int inningNo, int over, int j, PerBallDetails ballDetails)
    {
        battingTeam.setRunScore(1);
        battingTeam.addNoBallRuns();
        strikeBowler.addRunsGiven(1);
        strikeBowler.addNoBallDelivered();
        ballDetails.setBallOutcome(PossibleOutputOfBall.NoBall);
        ballDetails.setTeamScoreUpToThisBall(battingTeam.getRunScore());
        ballDetails.setWicketsUpToThisBall(battingTeam.getWickets());
    }

    private PossibleOutputOfBall randomGenerator() {
        return PossibleOutputOfBall.getOutcomeOfBall(strikerBatsman.getRole());
    }

    private void startAnInning(int inningNo)
    {
        strikerBatsman = battingTeam.getPlayerList().get(0);
        nonStrikerBatsman = battingTeam.getPlayerList().get(1);
        for(byte over = 0; over < numberOfOversInAnInning; over++)
        {
            strikeBowler = bowlingTeam.getPlayerList().get(10 - (over % 5));
            PerOverDetails overDetails = new PerOverDetails(over + 1, strikeBowler.getId());
            byte ball = 0;
            for(byte j = 1; j <= 6; j++)
            {
                ball++;
                PerBallDetails ballDetails = new PerBallDetails(strikerBatsman,strikeBowler,over,ball);
                PossibleOutputOfBall generatedOutput = randomGenerator();
                if(generatedOutput.equals(PossibleOutputOfBall.Wicket))
                {
                    overDetails.addNoOfWickets();
                    afterWicketGone(inningNo,over,j,ballDetails);
                    if(battingTeam.getWickets() >= 10)
                    {
                        scoreBoard.updateDetailsScoreBoardOverWise(overDetails,inningNo);
                        scoreBoard.updateDetailsScoreBoardBallWise(ballDetails,inningNo);
                        return;
                    }
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.Wide))
                {
                    overDetails.addWideBall();
                    afterWide(inningNo,over,j,ballDetails);
                    j--;
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.NoBall))
                {
                    overDetails.addNoBall();
                    afterNoBall(inningNo,over,j,ballDetails);
                    j--;
                }
                else
                {
                    int runCount = Integer.parseInt(String.valueOf((generatedOutput.getDisplayName()).charAt(0)));
                    overDetails.updateOverRunStates(runCount);
                    updateRunInScoreBoard(runCount,inningNo,over,j,generatedOutput,ballDetails);
                }
                scoreBoard.updateDetailsScoreBoardBallWise(ballDetails,inningNo);
                if((inningNo == 2) && (bowlingTeam.getRunScore() < battingTeam.getRunScore()))
                {
                    scoreBoard.updateDetailsScoreBoardOverWise(overDetails,inningNo);
                    return;
                }
            }
            scoreBoard.updateDetailsScoreBoardOverWise(overDetails,inningNo);

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

        UpdateMatchDatabase.enterMatchDetails(scoreBoard,conn);
        UpdateInningStatsDatabase.enterInningsStats(teamList,matchId,conn);
        UpdatePlayerStatsDatabase.enterPlayersStats(teamList,matchId,conn);
    }
    public CricketMatch(int numberOfOversInAnInning, int matchId)
    {
        this.numberOfOversInAnInning = numberOfOversInAnInning;
        this.matchId = matchId;
        this.scoreBoard = new ScoreBoard(matchId,numberOfOversInAnInning);
    }
}