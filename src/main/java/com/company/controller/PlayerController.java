package com.company.controller;

import com.company.bean.Players;
import com.company.request.ReqObjRenamePlayer;
import com.company.response.PlayerStatsResponse;
import com.company.service.PlayerControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerControllerService playerControllerService;

    @GetMapping("/")
    public List<Players> getAllPlayers() {
        return playerControllerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public Players getPlayerInfo(@PathVariable int playerId) {
        return playerControllerService.getPlayer(playerId);
    }

    @GetMapping("/{playerId}/{matchId}")
    public PlayerStatsResponse getPlayerStats(@PathVariable int playerId, @PathVariable int matchId) {
        return playerControllerService.getPlayerStats(playerId,matchId);
    }

    @PutMapping("/update")
    public String updatePlayerName(@RequestBody ReqObjRenamePlayer newNameObj) {
        return playerControllerService.updatePlayerName(newNameObj.getPlayerNewName(),newNameObj.getPlayerId());
    }
}
