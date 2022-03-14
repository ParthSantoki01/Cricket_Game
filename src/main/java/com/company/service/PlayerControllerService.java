package com.company.service;

import com.company.bean.Players;
import com.company.response.PlayerStatsResponse;

import java.util.List;

public interface PlayerControllerService {
    List<Players> getAllPlayers();
    Players getPlayer(int playerId);
    PlayerStatsResponse getPlayerStats(int playerId, int matchId);
    String updatePlayerName(String playerName, int playerId);
}
