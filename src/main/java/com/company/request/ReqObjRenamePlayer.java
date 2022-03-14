package com.company.request;

public class ReqObjRenamePlayer {
    private int playerId;
    private String playerNewName;

    public ReqObjRenamePlayer(int playerId, String playerNewName) {
        this.playerId = playerId;
        this.playerNewName = playerNewName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerNewName() {
        return playerNewName;
    }
}
