package com.company.enums;

public enum tossWinTeam {
    FirstTeam,
    SecondTeam;

    public static tossWinTeam getTossWinningTeam() {
        return values()[(int) (Math.random() * values().length)];
    }
}
