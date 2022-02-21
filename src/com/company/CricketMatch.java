package com.company;
import com.company.Utility.UtilityOperations;
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
    private DetailScoreBoardBallWise detailScoreBoardBallWise;

    public int getMatchId() {
        return matchId;
    }
    public int getNumberOfOversInAnInning() {
        return numberOfOversInAnInning;
    }

    private enum availableChoicesForTossWinningTeam {
        Batting,
        Bowling;
        public static availableChoicesForTossWinningTeam getRandomChoice() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
    private enum tossWinTeam {
        FirstTeam,
        SecondTeam;
        public static tossWinTeam getTossWinningTeam() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
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
                        detailScoreBoardBallWise.setTossWinningTeamChoice("Batting");
                    }
                    default -> {
                        battingTeam = teams.get(1);
                        bowlingTeam = teams.get(0);
                        detailScoreBoardBallWise.setTossWinningTeamChoice("Bowling");
                    }
                }
                detailScoreBoardBallWise.setTossWinningTeam(teams.get(0));
            }
            default -> {
                switch (tossWinningTeamChoice) {
                    case Bowling -> {
                        battingTeam = teams.get(0);
                        bowlingTeam = teams.get(1);
                        detailScoreBoardBallWise.setTossWinningTeamChoice("Bowling");
                    }
                    default -> {
                        battingTeam = teams.get(1);
                        bowlingTeam = teams.get(0);
                        detailScoreBoardBallWise.setTossWinningTeamChoice("Batting");
                    }
                }
                detailScoreBoardBallWise.setTossWinningTeam(teams.get(1));
            }
        }
        detailScoreBoardBallWise.setTeams(battingTeam,bowlingTeam);
    }

    private void nextStrikerBatsman()
    {
        strikerBatsman = battingTeam.getPlayerList().get(max(strikerBatsman.getBattingOrder(),nonStrikerBatsman.getBattingOrder()) + 1);
    }
    private void updateRunInScoreBoard(int run)
    {
//        strikerBatsman.addRunScore(run);
        strikerBatsman.addBallsFaced();
        strikerBatsman.updatePlayerRunStates(run);
        battingTeam.setRunScore(run);
        strikeBowler.addRunsGiven(run);
        if(run % 2 == 1)
        {
            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    private void afterWicketGone()
    {
        strikerBatsman.addBallsFaced();
        strikerBatsman.setBatsmanDismissal(true);
        strikerBatsman.setWicketTakenBy(strikeBowler.getName());
        battingTeam.addWickets();
        strikeBowler.addWicketsTaken();

        if(battingTeam.getWickets() >= 10){
            return;
        }
        nextStrikerBatsman();
    }
    private void afterWide()
    {
        battingTeam.setRunScore(1);
        battingTeam.addWideRuns(1);
        strikeBowler.addRunsGiven(1);
//        int extraByeRun = (int)(Math.random() * 5);
//        updateRunInScoreBoard(extraByeRun + 1);
    }
    private void afterNoBall()
    {
        battingTeam.setRunScore(1);
        battingTeam.addNoBalls();
        strikeBowler.addRunsGiven(1);
        int noBallRun = (int)(Math.random() * 6);
        if(noBallRun == 5)noBallRun = 6; // No Ball And Wide + 4, Not happen.
        updateRunInScoreBoard(noBallRun);
    }

    private String randomGenerator() {
        possibleOutputOfBall outcome = possibleOutputOfBall.getOutcomeOfBall(strikerBatsman.getRole());
        return outcome.getDisplayName();
    }

    private void innings(int inning_no)
    {
        strikerBatsman = battingTeam.getPlayerList().get(0);
        nonStrikerBatsman = battingTeam.getPlayerList().get(1);
        for(byte over = 0; over < numberOfOversInAnInning; over++)
        {
            strikeBowler = bowlingTeam.getPlayerList().get(10 - (over % 5));
            for(byte ball = 1; ball <= 6; ball++)
            {
                String generatedOutput = randomGenerator();
                if(generatedOutput.equals("Wicket"))
                {
                    afterWicketGone();
                    battingTeam.addBallsFaced();
                    strikeBowler.addBallsDelivered();
                    if(battingTeam.getWickets() >= 10){
                        detailScoreBoardBallWise.updateStatesScoreBoard(inning_no,generatedOutput,strikerBatsman,strikeBowler,over,ball,battingTeam.getRunScore());
                        return;
                    }
                }
                else if(generatedOutput.equals("Wide"))
                {
                    afterWide();
                    ball--;
                }
                else if(generatedOutput.equals("No Ball"))
                {
                    afterNoBall();
                    ball--;
                }
                else
                {
                    int runCount = Integer.parseInt(String.valueOf(generatedOutput.charAt(0)));
                    updateRunInScoreBoard(runCount);
                    battingTeam.addBallsFaced();
                    strikeBowler.addBallsDelivered();
                }
                detailScoreBoardBallWise.updateStatesScoreBoard(inning_no,generatedOutput,strikerBatsman,strikeBowler,over,ball,battingTeam.getRunScore());
                if((inning_no == 2) && (bowlingTeam.getRunScore() < battingTeam.getRunScore()))
                {
                    return;
                }
            }
            Player[] swappedPlayers = UtilityOperations.swapPlayer(strikerBatsman,nonStrikerBatsman);
            strikerBatsman = swappedPlayers[0];
            nonStrikerBatsman = swappedPlayers[1];
        }
    }

    public void startGame()
    {

        innings(1);

        Team[] swappedTeams = UtilityOperations.swapTeam(battingTeam,bowlingTeam);
        battingTeam = swappedTeams[0];
        bowlingTeam = swappedTeams[1];

        innings(2);
        detailScoreBoardBallWise.selectWinningTeam();
        detailScoreBoardBallWise.printScoreBoardBallWise();

        System.out.println(detailScoreBoardBallWise.getWinningStatus());
    }
    public CricketMatch(int numberOfOversInAnInning, int matchId)
    {
        this.numberOfOversInAnInning = numberOfOversInAnInning;
        this.matchId = matchId;
        detailScoreBoardBallWise = new DetailScoreBoardBallWise(matchId,numberOfOversInAnInning);

    }
}