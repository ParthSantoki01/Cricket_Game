package com.company.controller;

import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.PlayerStats;
import com.company.service.PlayerControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerControllerServiceImpl playerControllerService;

    @GetMapping("/")
    public List<PlayerInfo> getAllPlayers()
    {
        return playerControllerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public PlayerInfo getPlayerInfo(@PathVariable int playerId) {
        return playerControllerService.getPlayerInfo(playerId);
    }

    @GetMapping("/{playerId}/{matchId}")
    public PlayerStats getPlayerStats(@PathVariable int playerId, @PathVariable int matchId)
    {
        return playerControllerService.getPlayerStats(playerId,matchId);
    }
}
