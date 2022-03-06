package com.company.service;

import com.company.bean.*;
import com.company.enums.*;
import com.company.utils.UtilityOperations;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatchServiceImpl implements MatchService {
    @Override
    public void tossOfMatch(List<Team> teams, Match match){
        TossWinTeam tossWinningTeam = TossWinTeam.getTossWinningTeam();
        AvailableChoicesForTossWinningTeam tossWinningTeamChoice = AvailableChoicesForTossWinningTeam.getRandomChoice();
        switch (tossWinningTeam) {
            case FIRST_TEAM:
                switch (tossWinningTeamChoice) {
                    case BATTING:
                        match.setFirstBattingTeam(teams.get(0));
                        match.setSecondBattingTeam(teams.get(1));
                        break;
                    default:
                        match.setFirstBattingTeam(teams.get(1));
                        match.setSecondBattingTeam(teams.get(0));
                        break;
                }
                match.setTossWinningTeam(teams.get(0));
                break;
            default:
                switch (tossWinningTeamChoice) {
                    case BOWLING:
                        match.setFirstBattingTeam(teams.get(0));
                        match.setSecondBattingTeam(teams.get(1));
                        break;
                    default :
                        match.setFirstBattingTeam(teams.get(1));
                        match.setSecondBattingTeam(teams.get(0));
                        break;
                }
                match.setTossWinningTeam(teams.get(1));
                break;
        }
    }

    @Override
    public void startGame(Match match, List<Team> teamList, int overId, int ballId) {
        tossOfMatch(teamList,match);
        startAnInning(1,match.getFirstBattingTeam(),match.getSecondBattingTeam(),match, overId, ballId);

        Over lastOver = match.getOverList().get(match.getOverList().size() - 1);
        overId = lastOver.getOverId();
        ballId = lastOver.getBallList().get(lastOver.getBallList().size() - 1).getBallId();

        startAnInning(2,match.getSecondBattingTeam(),match.getFirstBattingTeam(),match, overId, ballId);
        selectWinningTeam(match);
    }

    private void startAnInning(int inningNo, Team battingTeam, Team bowlingTeam, Match match, int overId, int ballId) {
        int oversInInning = match.getOversInInning();
        Player strikerBatsman = battingTeam.getPlayerList().get(0);
        Player nonStrikerBatsman = battingTeam.getPlayerList().get(1);

        for(byte overNo = 1; overNo <= oversInInning; overNo++)
        {
            Player strikeBowler = bowlingTeam.getPlayerList().get(10 - ((overNo-1) % 5));
            Over over = new Over(++overId,match.getMatchId(),battingTeam.getTeamId(),overNo,strikeBowler,System.currentTimeMillis(),System.currentTimeMillis(),false);
            for(byte ballNo = 1; ballNo <= 6; ballNo++)
            {
                Ball ball = new Ball(++ballId,overId,ballNo,strikerBatsman,System.currentTimeMillis(),System.currentTimeMillis(),false);
                PossibleOutputOfBall generatedOutput = UtilityOperations.randomGenerator(strikerBatsman);
                if(generatedOutput.equals(PossibleOutputOfBall.WICKET))
                {
                    battingTeam.addWickets();
                    ball.setBallOutcome(PossibleOutputOfBall.WICKET);
                    if(battingTeam.getWickets() >= 10)
                    {
                        over.getBallList().add(ball);
                        match.getOverList().add(over);
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
                    match.getOverList().add(over);
                    return;
                }
            }
            match.getOverList().add(over);
            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    private void selectWinningTeam(Match match) {
        if(match.getFirstBattingTeam().getRunScore() > match.getSecondBattingTeam().getRunScore())
        {
            match.setWinningTeam(match.getFirstBattingTeam());
            match.setRunMargin(match.getFirstBattingTeam().getRunScore() - match.getSecondBattingTeam().getRunScore());
        }
        else if(match.getFirstBattingTeam().getRunScore() < match.getSecondBattingTeam().getRunScore())
        {
            match.setWinningTeam(match.getSecondBattingTeam());
            match.setWicketMargin(10 - match.getSecondBattingTeam().getWickets());
        }
    }
}
