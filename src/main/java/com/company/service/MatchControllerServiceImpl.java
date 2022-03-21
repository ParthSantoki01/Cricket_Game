package com.company.service;

import com.company.bean.*;
import com.company.enums.PossibleOutputOfBall;
import com.company.repository.*;
import com.company.response.MatchResponse;
import com.company.response.TeamStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchControllerServiceImpl implements MatchControllerService {

    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private OverRepo overRepo;
    @Autowired
    private BallRepo ballRepo;
    @Autowired
    private MatchService matchService;

    @Override
    public String createNewMatch(int overInInning) {
        if(overInInning == 0)return "Give Non Zero overs to play a Match";
        int noOfTeams = teamRepo.noOfTeamInDatabase();
        if(noOfTeams < 2) return "Please, First create at least 2 team for Play match";
        int team1Id, team2Id;
        do {
            team1Id = 1 + (int) (Math.random() * noOfTeams);
            team2Id = 1 + (int) (Math.random() * noOfTeams);
        } while ((team1Id == team2Id) || !teamRepo.isTeamAvailable(team1Id) || !teamRepo.isTeamAvailable(team2Id));

        Teams team1 = teamRepo.getTeamDetails(team1Id);
        if(team1 == null)return "Database Error!";
        Teams team2 = teamRepo.getTeamDetails(team2Id);
        if(team2 == null)return "Database Error!";

        Map<Integer,Teams> teams = new HashMap<>();
        teams.put(team1Id,team1);
        teams.put(team2Id,team2);

        List<Players> team1Players = playerRepo.getTeamPlayers(team1Id);
        if(team1Players == null)return "Database Error!";
        List<Players> team2Players = playerRepo.getTeamPlayers(team2Id);
        if(team2Players == null)return "Database Error!";

        Map<Integer,List<Players>> players = new HashMap<>();
        players.put(team1Id,team1Players);
        players.put(team2Id,team2Players);

        int matchId = matchRepo.getNewMatchId();
        if(matchId == 0)return "Error, Match not created";

        List<Overs> oversList = new ArrayList<>();
        List<Balls> ballsList = new ArrayList<>();
        int newOverId = overRepo.getNewOverId();
        int newBallId = ballRepo.getNewBallID();
        if(newBallId == 0 || newOverId == 0)return "Database Error!";

        Matches cricketMatch = new Matches(matchId, overInInning, System.currentTimeMillis(), System.currentTimeMillis(),false);
        matchService.tossOfMatch(team1Id,team2Id,cricketMatch);
        matchService.startGame(cricketMatch, teams, players, oversList, ballsList, newOverId, newBallId);
        String matchStatus = matchRepo.insertMatchDetails(cricketMatch);
        String overStatus = overRepo.insertOversDetails(oversList);
        String ballStatus = ballRepo.insertBallsDetails(ballsList);

        if(matchStatus.equals("Error") || overStatus.equals("Error") || ballStatus.equals("Error")) return "DataBase Error";
        return "New Match Created With matchId = " + matchId;
    }

    @Override
    public MatchResponse getMatch(int matchId) {
        if(!matchRepo.isMatchAvailable(matchId)) return null;
        Matches cricketMatch = matchRepo.getMatch(matchId);
        String firstBattingTeamName = teamRepo.getTeamName(cricketMatch.getFirstBattingTeamId());
        String secondBattingTeamName = teamRepo.getTeamName(cricketMatch.getSecondBattingTeamId());
        String tossWinningTeamName = (cricketMatch.getTossWinningTeamId() == cricketMatch.getFirstBattingTeamId()) ? firstBattingTeamName : secondBattingTeamName;
        String winningTeamName = "";
        if(cricketMatch.getRunMargin() != 0 || cricketMatch.getWicketMargin() != 0) {
            winningTeamName = (cricketMatch.getWinningTeamId() == cricketMatch.getFirstBattingTeamId()) ? firstBattingTeamName : secondBattingTeamName;
        }
        MatchResponse matchResponse = new MatchResponse(cricketMatch.getMatchId(),tossWinningTeamName,firstBattingTeamName,secondBattingTeamName,winningTeamName,cricketMatch.getRunMargin(),cricketMatch.getWicketMargin(),cricketMatch.getOversInInning());
        return matchResponse;
    }

    @Override
    public List<MatchResponse> getMatch() {
        List<MatchResponse> matchResponseList = new ArrayList<>();
        int lastMatchId = matchRepo.getNewMatchId();
        for(int i = 1; i < lastMatchId; i++)
            if (matchRepo.isMatchAvailable(i)) matchResponseList.add(getMatch(i));
        return matchResponseList;
    }

    @Override
    public List<TeamStatsResponse> getMatchTeamsStats(int matchId) {
        if(!matchRepo.isMatchAvailable(matchId)) return null;

        List<Integer> teamIdList = matchRepo.getTeamsIdInMatch(matchId);

        List<List<String>> teamOutcomes = new ArrayList<>();
        teamOutcomes.add(teamRepo.getTeamOutcomes(teamIdList.get(0),matchId));
        teamOutcomes.add(teamRepo.getTeamOutcomes(teamIdList.get(1),matchId));

        List<TeamStatsResponse> teamStatsList = new ArrayList<>();
        int i = 0;
        for (List<String> teamOutcome : teamOutcomes) {
            String teamName = teamRepo.getTeamName(teamIdList.get(i));
            TeamStatsResponse teamStats = new TeamStatsResponse(teamIdList.get(i),teamName);
            int playedBall = 0;
            for (String stats : teamOutcome)
            {
                switch (PossibleOutputOfBall.valueOf(stats)) {
                    case RUN_0:
                        playedBall++;
                        break;
                    case RUN_1:
                        playedBall++;
                        teamStats.addRunScore(1);
                        break;
                    case RUN_2:
                        playedBall++;
                        teamStats.addRunScore(2);
                        break;
                    case RUN_3:
                        playedBall++;
                        teamStats.addRunScore(3);
                        break;
                    case RUN_4:
                        playedBall++;
                        teamStats.addFours();
                        break;
                    case RUN_6:
                        playedBall++;
                        teamStats.addSixes();
                        break;
                    case WICKET:
                        playedBall++;
                        teamStats.addWickets();
                        break;
                    case WIDE:
                        teamStats.addWideRuns();
                        break;
                    case NO_BALL:
                        teamStats.addNoBallRuns();
                        break;
                }
            }
            teamStats.setPlayerOver((playedBall/6) + "" + ((playedBall%6 != 0) ? ("." + (playedBall % 6)) : ""));
            teamStatsList.add(teamStats);
            i++;
        }
        return teamStatsList;
    }

    @Override
    public String deleteMatchDetails(int matchId) {
        if(!matchRepo.isMatchAvailable(matchId))return "Match is not present, give valid matchId";
        return matchRepo.deleteMatchDetails(matchId);
    }
}
