package com.company;

public enum possibleOutputOfBall {
    Run_0("0 Run"),
    Run_1("1 Run"),
    Run_2("2 Run"),
    Run_3("3 Run"),
    Run_4("4 Run"),
    Run_6("6 Run"),
    Wide("Wide"),
    NoBall("No Ball"),
    Wicket("Wicket");

    private String displayName;

    possibleOutputOfBall(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static possibleOutputOfBall getOutcomeOfBall() {
        return values()[(int) (Math.random() * values().length)];
    }
}
