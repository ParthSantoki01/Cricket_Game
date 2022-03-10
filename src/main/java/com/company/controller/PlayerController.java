package com.company.controller;

import com.company.repository.entity.PlayerInfo;
import com.company.repository.entity.PlayerStats;
import com.company.service.PlayerControllerService;
import com.company.service.PlayerControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerControllerService playerControllerService;

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

    @PutMapping("/{playerId}")
    public String updatePlayerName(@RequestBody String playerName,@PathVariable int playerId)
    {
        return playerControllerService.updatePlayerName(playerName,playerId);
    }
}
