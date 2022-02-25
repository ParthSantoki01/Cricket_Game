package com.company.Utility;

public enum availableChoicesForTossWinningTeam {
    Batting,
    Bowling;

    public static availableChoicesForTossWinningTeam getRandomChoice() {
        return values()[(int) (Math.random() * values().length)];
    }
}
