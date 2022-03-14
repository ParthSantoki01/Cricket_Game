package com.company.service;

import com.company.bean.*;
import com.company.enums.*;
import com.company.utils.UtilityOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService{

    @Override
    public void tossOfMatch(int team1Id, int team2Id, Matches cricketMatch){
        TossWinTeam tossWinningTeam = TossWinTeam.getTossWinningTeam();
        AvailableChoicesForTossWinningTeam tossWinningTeamChoice = AvailableChoicesForTossWinningTeam.getRandomChoice();
        switch (tossWinningTeam) {
            case FIRST_TEAM:
                switch (tossWinningTeamChoice) {
                    case BATTING:
                        cricketMatch.setFirstBattingTeamId(team1Id);
                        cricketMatch.setSecondBattingTeamId(team2Id);
                        break;
                    case BOWLING:
                        cricketMatch.setFirstBattingTeamId(team2Id);
                        cricketMatch.setSecondBattingTeamId(team1Id);
                        break;
                }
                cricketMatch.setTossWinningTeamId(team1Id);
                break;
            case SECOND_TEAM:
                switch (tossWinningTeamChoice) {
                    case BATTING:
                        cricketMatch.setFirstBattingTeamId(team2Id);
                        cricketMatch.setSecondBattingTeamId(team1Id);
                        break;
                    case BOWLING:
                        cricketMatch.setFirstBattingTeamId(team1Id);
                        cricketMatch.setSecondBattingTeamId(team2Id);
                        break;
                }
                cricketMatch.setTossWinningTeamId(team2Id);
                break;
        }
    }

    @Override
    public void startGame(Matches cricketMatch, Map<Integer,Teams> teams, Map<Integer,List<Players>> players, List<Overs> oversList, List<Balls> ballsList, int overId, int ballId) {
        int[] runScore = new int[]{0,0};
        int[] wickets = new int[]{0,0};
        int firstBattingTeamId = cricketMatch.getFirstBattingTeamId();
        int secondBattingTeamId = cricketMatch.getSecondBattingTeamId();

        startAnInning(1, teams.get(firstBattingTeamId), teams.get(secondBattingTeamId), players, oversList, ballsList, cricketMatch, overId, ballId, runScore, wickets);

        overId = oversList.get(oversList.size() - 1).getOverId() + 1;
        ballId = ballsList.get(ballsList.size() - 1).getBallId() + 1;

        startAnInning(2, teams.get(secondBattingTeamId), teams.get(firstBattingTeamId), players, oversList, ballsList, cricketMatch, overId, ballId, runScore, wickets);
        selectWinningTeam(cricketMatch,runScore,wickets);
    }

    private void startAnInning(int inningNo, Teams battingTeam, Teams bowlingTeam, Map<Integer,List<Players>> players, List<Overs> oversList, List<Balls> ballsList, Matches cricketMatch, int overId, int ballId, int[] runScore, int[] wickets) {
        int oversInInning = cricketMatch.getOversInInning();
        Players strikerBatsman = players.get(battingTeam.getTeamId()).get(0);
        Players nonStrikerBatsman = players.get(battingTeam.getTeamId()).get(1);

        for(byte overNo = 1; overNo <= oversInInning; overNo++)
        {
            Players strikeBowler = players.get(bowlingTeam.getTeamId()).get(10 - ((overNo-1) % 5));
            Overs over = new Overs(overId++, cricketMatch.getMatchId(), battingTeam.getTeamId(),overNo,strikeBowler.getPlayerId(),System.currentTimeMillis(),System.currentTimeMillis(),false);
            oversList.add(over);
            for(byte ballNo = 1; ballNo <= 6; ballNo++)
            {
                Balls ball = new Balls(ballId++,over.getOverId(),ballNo,strikerBatsman.getPlayerId(),System.currentTimeMillis(),System.currentTimeMillis(),false);
                PossibleOutputOfBall generatedOutput = UtilityOperations.randomGenerator(strikerBatsman);
                if(generatedOutput.equals(PossibleOutputOfBall.WICKET))
                {
                    ball.setBallOutcome(PossibleOutputOfBall.WICKET);
                    wickets[inningNo-1]++;
                    if(wickets[inningNo-1] >= 10)
                    {
                        ballsList.add(ball);
                        return;
                    }
                    strikerBatsman = UtilityOperations.nextBatsman(players.get(battingTeam.getTeamId()),strikerBatsman,nonStrikerBatsman);
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.WIDE))
                {
                    runScore[inningNo-1]++;
                    ball.setBallOutcome(PossibleOutputOfBall.WIDE);
                    ballNo--;
                }
                else if(generatedOutput.equals(PossibleOutputOfBall.NO_BALL))
                {
                    runScore[inningNo-1]++;
                    ball.setBallOutcome(PossibleOutputOfBall.NO_BALL);
                    ballNo--;
                }
                else
                {
                    int runCount = Integer.parseInt(String.valueOf((generatedOutput.getDisplayName()).charAt(0)));
                    runScore[inningNo-1] += runCount;
                    ball.setBallOutcome(generatedOutput);
                    if(runCount % 2 == 1)
                    {
                        Players[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
                        strikerBatsman = swappedPlayers[0];
                        nonStrikerBatsman = swappedPlayers[1];
                    }
                }
                ballsList.add(ball);
                if((inningNo == 2) && (runScore[0] < runScore[1])) { return; }
            }
            Players[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    private void selectWinningTeam(Matches cricketMatch, int[] runScore, int[] wickets) {
        if(runScore[0] > runScore[1])
        {
            cricketMatch.setWinningTeamId(cricketMatch.getFirstBattingTeamId());
            cricketMatch.setRunMargin(runScore[0] - runScore[1]);
        }
        else if(runScore[0] < runScore[1])
        {
            cricketMatch.setWinningTeamId(cricketMatch.getSecondBattingTeamId());
            cricketMatch.setWicketMargin(10 - wickets[1]);
        }
    }
}
