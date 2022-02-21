package com.company.Utility;

public enum PossibleOutputOfBall {
    Run_0("0 Run"),
    Run_1("1 Run"),
    Run_2("2 Run"),
    Run_3("3 Run"),
    Run_4("4 Run"),
    Run_6("6 Run"),
    Wide("Wide"),
    NoBall("No Ball"),
    Wicket("Wicket");

    private final String displayName;

    PossibleOutputOfBall(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Probability of getting Index of possible output for Batsman and Bowler.
    private static final double[] probabilityArrayOfBatsman =       {0.25, 0.30, 0.15, 0.03, 0.10, 0.05, 0.05, 0.02, 0.05};
    private static final double[] probabilityArrayOfWicketKeeper =  {0.35, 0.25, 0.13, 0.03, 0.08, 0.04, 0.05, 0.02, 0.05};
    private static final double[] probabilityArrayOfBowler =        {0.50, 0.20, 0.09, 0.03, 0.04, 0.02, 0.05, 0.02, 0.05};

    public static PossibleOutputOfBall getOutcomeOfBall(PlayerRole playerRole) {

        double randomNumber = Math.random();
        double probabilitySum = 0.0;
        switch (playerRole) {
            case Batsman:
                for (int i = 0; i < values().length; i++) {
                    probabilitySum += probabilityArrayOfBatsman[i];
                    if (randomNumber <= probabilitySum) {
                        return values()[i];
                    }
                }
                break;
            case Wicketkeeper:
                for (int i = 0; i < values().length; i++) {
                    probabilitySum += probabilityArrayOfWicketKeeper[i];
                    if (randomNumber <= probabilitySum) {
                        return values()[i];
                    }
                }
                break;
            case Bowler:
                for (int i = 0; i < values().length; i++) {
                    probabilitySum += probabilityArrayOfBowler[i];
                    if (randomNumber <= probabilitySum) {
                        return values()[i];
                    }
                }
                break;
        }
        return values()[8];
    }
}
