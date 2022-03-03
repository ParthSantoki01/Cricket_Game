package com.company.enums;

public enum AvailableChoicesForTossWinningTeam {
    BATTING,
    BOWLING;
    public static AvailableChoicesForTossWinningTeam getRandomChoice() {
        return values()[(int) (Math.random() * values().length)];
    }
}
