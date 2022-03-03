package com.company.service;

import com.company.bean.*;
import com.company.enums.AvailableChoicesForTossWinningTeam;
import com.company.enums.PossibleOutputOfBall;
import com.company.enums.TossWinTeam;
import com.company.utils.UtilityOperations;

import java.util.List;

public class CricketGameServiceImpl implements CricketGameService{
    @Override
    public void tossOfMatch(List<Team> teams, ScoreBoard scoreBoard){
        TossWinTeam tossWinningTeam = TossWinTeam.getTossWinningTeam();
        AvailableChoicesForTossWinningTeam tossWinningTeamChoice = AvailableChoicesForTossWinningTeam.getRandomChoice();
        switch (tossWinningTeam) {
            case FIRST_TEAM:
                switch (tossWinningTeamChoice) {
                    case BATTING:
                        scoreBoard.setFirstBattingTeam(teams.get(0));
                        scoreBoard.setSecondBattingTeam(teams.get(1));
                        break;
                    default:
                        scoreBoard.setFirstBattingTeam(teams.get(1));
                        scoreBoard.setSecondBattingTeam(teams.get(0));
                        break;
                }
                scoreBoard.setTossWinningTeam(teams.get(0));
                break;
            default:
                switch (tossWinningTeamChoice) {
                    case BOWLING:
                        scoreBoard.setFirstBattingTeam(teams.get(0));
                        scoreBoard.setSecondBattingTeam(teams.get(1));
                        break;
                    default :
                        scoreBoard.setFirstBattingTeam(teams.get(1));
                        scoreBoard.setSecondBattingTeam(teams.get(0));
                        break;
                }
                scoreBoard.setTossWinningTeam(teams.get(1));
                break;
        }
    }

    @Override
    public void startGame(CricketMatch cricketMatch, List<Team> teamList)
    {
        ScoreBoard scoreBoard = cricketMatch.getScoreBoard();
        tossOfMatch(teamList,cricketMatch.getScoreBoard());
        startAnInning(1,cricketMatch.getScoreBoard().getFirstBattingTeam(),cricketMatch.getScoreBoard().getSecondBattingTeam(),cricketMatch);
        startAnInning(2,cricketMatch.getScoreBoard().getSecondBattingTeam(),cricketMatch.getScoreBoard().getFirstBattingTeam(),cricketMatch);

        selectWinningTeam(scoreBoard);
        cricketMatch.getScoreBoard().printScoreBoard();
    }

    private void startAnInning(int inningNo, Team battingTeam, Team bowlingTeam, CricketMatch cricketMatch)
    {
        int oversInInning = cricketMatch.getOversInInning();
        ScoreBoard scoreBoard = cricketMatch.getScoreBoard();
        Player strikerBatsman = battingTeam.getPlayerList().get(0);
        Player nonStrikerBatsman = battingTeam.getPlayerList().get(1);
        for(byte overNo = 1; overNo <= oversInInning; overNo++)
        {
            Player strikeBowler = bowlingTeam.getPlayerList().get(10 - ((overNo-1) % 5));
            Over over = new Over(cricketMatch.getMatchId(),battingTeam.getTeamId(),overNo,strikeBowler);
            for(byte ballNo = 1; ballNo <= 6; ballNo++)
            {
                Ball ball = new Ball(ballNo,strikerBatsman);
                PossibleOutputOfBall generatedOutput = UtilityOperations.randomGenerator(strikerBatsman);
                if(generatedOutput.equals(PossibleOutputOfBall.WICKET))
                {
                    battingTeam.addWickets();
                    ball.setBallOutcome(PossibleOutputOfBall.WICKET);
                    if(battingTeam.getWickets() >= 10)
                    {
                        over.getBallList().add(ball);
                        scoreBoard.getOverList().add(over);
                        return;
                    }

                    strikerBatsman = UtilityOperations.nextBatsman(battingTeam,strikerBatsman,nonStrikerBatsman);
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.WIDE))
                {
                    battingTeam.addRunScore(1);
                    ball.setBallOutcome(PossibleOutputOfBall.WIDE);
                    ballNo--;
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.NO_BALL))
                {
                    battingTeam.addRunScore(1);
                    ball.setBallOutcome(PossibleOutputOfBall.NO_BALL);
                    ballNo--;
                }
                else
                {
                    int runScore = Integer.parseInt(String.valueOf((generatedOutput.getDisplayName()).charAt(0)));
                    battingTeam.addRunScore(runScore);
                    ball.setBallOutcome(generatedOutput);
                    if(runScore % 2 == 1)
                    {
                        Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
                        strikerBatsman = swappedPlayers[0];
                        nonStrikerBatsman = swappedPlayers[1];
                    }
                }
                over.getBallList().add(ball);
                if((inningNo == 2) && (bowlingTeam.getRunScore() < battingTeam.getRunScore()))
                {
                    scoreBoard.getOverList().add(over);
                    return;
                }
            }
            scoreBoard.getOverList().add(over);
            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    private void selectWinningTeam(ScoreBoard scoreBoard) {
        if(scoreBoard.getFirstBattingTeam().getRunScore() > scoreBoard.getSecondBattingTeam().getRunScore())
        {
            scoreBoard.setWinningTeam(scoreBoard.getFirstBattingTeam());
            scoreBoard.setRunMargin(scoreBoard.getFirstBattingTeam().getRunScore() - scoreBoard.getSecondBattingTeam().getRunScore());
        }
        else if(scoreBoard.getFirstBattingTeam().getRunScore() < scoreBoard.getSecondBattingTeam().getRunScore())
        {
            scoreBoard.setWinningTeam(scoreBoard.getSecondBattingTeam());
            scoreBoard.setWicketMargin(10 - scoreBoard.getSecondBattingTeam().getWickets());
        }
    }
}
