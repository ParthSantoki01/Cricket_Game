package com.company.enums;

public enum TossWinTeam {
    FIRST_TEAM,
    SECOND_TEAM;
    public static TossWinTeam getTossWinningTeam() {
        return values()[(int) (Math.random() * values().length)];
    }
}
