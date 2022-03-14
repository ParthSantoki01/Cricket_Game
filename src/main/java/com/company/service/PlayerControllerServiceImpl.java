package com.company.service;

import com.company.bean.Players;
import com.company.enums.PossibleOutputOfBall;
import com.company.repository.PlayerRepo;
import com.company.repository.TeamRepo;
import com.company.response.PlayerStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerControllerServiceImpl implements PlayerControllerService {

    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private TeamRepo teamRepo;

    @Override
    public List<Players> getAllPlayers() {
        int lastPlayerId = playerRepo.getNewPlayerId();
        List<Players> playerList = new ArrayList<>();
        for(int i = 1; i <= lastPlayerId; i++) {
            if(playerRepo.isPlayerAvailable(i)) {
                playerList.add(getPlayer(i));
            }
        }
        return playerList;
    }

    @Override
    public Players getPlayer(int playerId) {
        if(!playerRepo.isPlayerAvailable(playerId)) return null;
        return playerRepo.getPlayer(playerId);
    }

    @Override
    public PlayerStatsResponse getPlayerStats(int playerId, int matchId) {
        if(!playerRepo.isPlayerAvailableInMatch(playerId,matchId)) return null;
        Players playerDetails = playerRepo.getPlayer(playerId);
        String playerTeamName = teamRepo.getTeamName(playerDetails.getTeamId());
        PlayerStatsResponse playerStats = new PlayerStatsResponse(playerDetails.getPlayerId(),playerDetails.getPlayerName(),playerDetails.getBattingOrder(),playerDetails.getPlayerRole(),playerTeamName);

        List<List<String>> battingOutcome = playerRepo.getPlayerBattingOutcomeInMatch(playerId,matchId);
        for(List<String> outcome : battingOutcome) {
            switch (PossibleOutputOfBall.valueOf(outcome.get(0))) {
                case RUN_0:
                    playerStats.addDotBallPlayed();
                    playerStats.addBallPlayed();
                    break;
                case RUN_1:
                    playerStats.addOneRunsScored();
                    playerStats.addBallPlayed();
                    break;
                case RUN_2:
                    playerStats.addTwoRunsScored();
                    playerStats.addBallPlayed();
                    break;
                case RUN_3:
                    playerStats.addThreeRunsScore();
                    playerStats.addBallPlayed();
                    break;
                case RUN_4:
                    playerStats.addFourRunsScore();
                    playerStats.addBallPlayed();
                    break;
                case RUN_6:
                    playerStats.addSixRunsScore();
                    playerStats.addBallPlayed();
                    break;
                case WICKET:
                    playerStats.addBallPlayed();
                    playerStats.setWicketTakenByBowler(playerRepo.getPlayerNameById(Integer.parseInt(outcome.get(1))));
                    break;
            }
        }
        List<String> bowlingOutcome = playerRepo.getPlayerBowlerOutcomeInMatch(playerId,matchId);
        for (String stats : bowlingOutcome) {
            switch (PossibleOutputOfBall.valueOf(stats)) {
                case RUN_0:
                    playerStats.addDotBallsDelivered();
                    playerStats.addBallDelivered();
                    break;
                case RUN_1:
                    playerStats.addOneRunBallsBowled();
                    playerStats.addBallDelivered();
                    break;
                case RUN_2:
                    playerStats.addTwoRunBallsBowled();
                    playerStats.addBallDelivered();
                    break;
                case RUN_3:
                    playerStats.addThreeRunBallsBowled();
                    playerStats.addBallDelivered();
                    break;
                case RUN_4:
                    playerStats.addFourRunBallsBowled();
                    playerStats.addBallDelivered();
                    break;
                case RUN_6:
                    playerStats.addSixRunBallsBowled();
                    playerStats.addBallDelivered();
                    break;
                case WICKET:
                    playerStats.addWicketTaken();
                    playerStats.addBallDelivered();
                    break;
                case WIDE:
                    playerStats.addWideBallsDelivered();
                    playerStats.addBallDelivered();
                    break;
                case NO_BALL:
                    playerStats.addNoBallsDelivered();
                    playerStats.addBallDelivered();
                    break;
            }
        }
        return playerStats;
    }

    @Override
    public String updatePlayerName(String playerName, int playerId) {
        return playerRepo.updatePlayerName(playerName,playerId);
    }
}
