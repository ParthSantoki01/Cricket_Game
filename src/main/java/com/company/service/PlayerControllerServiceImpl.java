package com.company.service;

import com.company.enums.PossibleOutputOfBall;
import com.company.repository.PlayerRepo;
import com.company.repository.TeamRepo;
import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.PlayerStats;
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
    public List<PlayerInfo> getAllPlayers() {
        int lastPlayerId = playerRepo.getNewPlayerId();
        List<PlayerInfo> playerList = new ArrayList<>();
        for(int i = 1; i <= lastPlayerId; i++)
        {
            if(playerRepo.isPlayerAvailable(i))
            {
                List<String> playerInformation = new ArrayList<>();
                playerRepo.getPlayerInfo(i,playerInformation);
                playerList.add(new PlayerInfo(playerInformation.get(0),playerInformation.get(1),playerInformation.get(2),playerInformation.get(3),playerInformation.get(4)));
            }
        }
        return playerList;
    }

    @Override
    public PlayerInfo getPlayerInfo(int playerId) {
        if(playerRepo.isPlayerAvailable(playerId))
        {
            List<String> playerInformation = new ArrayList<>();
            playerRepo.getPlayerInfo(playerId,playerInformation);
            return new PlayerInfo(playerInformation.get(0),playerInformation.get(1),playerInformation.get(2),playerInformation.get(3),playerInformation.get(4));
        }
        else
        {
            return null;
        }
    }

    @Override
    public PlayerStats getPlayerStats(int playerId, int matchId) {
        List<String> playerInformation = new ArrayList<>();
        playerRepo.getPlayerInfo(playerId,playerInformation);
        PlayerStats player = new PlayerStats(playerInformation.get(0),playerInformation.get(1),playerInformation.get(2),playerInformation.get(3),playerInformation.get(4));

        List<List<String>> battingOutcome = new ArrayList<>();
        playerRepo.getPlayerBattingOutcomeInMatch(playerId,matchId,battingOutcome);
        for(List<String> outcome : battingOutcome)
        {
            switch (PossibleOutputOfBall.valueOf(outcome.get(0))) {
                case RUN_0:
                    player.addDotBallPlayed();
                    player.addBallPlayed();
                    break;
                case RUN_1:
                    player.addOneRunsScored();
                    player.addRunScore(1);
                    player.addBallPlayed();
                    break;
                case RUN_2:
                    player.addTwoRunsScored();
                    player.addRunScore(2);
                    player.addBallPlayed();
                    break;
                case RUN_3:
                    player.addThreeRunsScore();
                    player.addRunScore(3);
                    player.addBallPlayed();
                    break;
                case RUN_4:
                    player.addFourRunsScore();
                    player.addRunScore(4);
                    player.addBallPlayed();
                    break;
                case RUN_6:
                    player.addSixRunsScore();
                    player.addRunScore(6);
                    player.addBallPlayed();
                    break;
                case WICKET:
                    player.addBallPlayed();
                    player.setWicketTakenByBowler(playerRepo.getPlayerNameById(Integer.parseInt(outcome.get(1))));
                    break;
            }
        }

        List<String> bowlingOutcome = new ArrayList<>();
        playerRepo.getPlayerBowlerOutcomeInMatch(playerId,matchId,bowlingOutcome);
        for (String stats : bowlingOutcome)
        {
            switch (PossibleOutputOfBall.valueOf(stats)) {
                case RUN_0:
                    player.addDotBallsDelivered();
                    player.addBallDelivered();
                    break;
                case RUN_1:
                    player.addOneRunBallsBowled();
                    player.addRunGiven(1);
                    player.addBallDelivered();
                    break;
                case RUN_2:
                    player.addTwoRunBallsBowled();
                    player.addRunGiven(2);
                    player.addBallDelivered();
                    break;
                case RUN_3:
                    player.addThreeRunBallsBowled();
                    player.addRunGiven(3);
                    player.addBallDelivered();
                    break;
                case RUN_4:
                    player.addFourRunBallsBowled();
                    player.addRunGiven(4);
                    player.addBallDelivered();
                    break;
                case RUN_6:
                    player.addSixRunBallsBowled();
                    player.addRunGiven(6);
                    player.addBallDelivered();
                    break;
                case WICKET:
                    player.addWicketTaken();
                    player.addBallDelivered();
                    break;
                case WIDE:
                    player.addWideBallsDelivered();
                    player.addBallDelivered();
                    break;
                case NO_BALL:
                    player.addNoBallsDelivered();
                    player.addBallDelivered();
                    break;
            }
        }
        return player;
    }
}
