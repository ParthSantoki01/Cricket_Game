package com.company.service;

import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.PlayerStats;

import java.util.List;

public interface PlayerControllerService {
    List<PlayerInfo> getAllPlayers();
    PlayerInfo getPlayerInfo(int playerId);
    PlayerStats getPlayerStats(int playerId, int matchId);
}
