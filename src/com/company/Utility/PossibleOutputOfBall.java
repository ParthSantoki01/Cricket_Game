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
    private static final double[] probabilityArrayOfBatsman =   {0.25, 0.30, 0.15, 0.03, 0.125, 0.05, 0.05, 0.02, 0.025};
    private static final double[] probabilityArrayOfBowler =    {0.50, 0.20, 0.10, 0.02, 0.040, 0.02, 0.05, 0.02, 0.050};

    public static PossibleOutputOfBall getOutcomeOfBall(PlayerRole playerRole) {

        double randomNumber = Math.random();
        double probabilitySum = 0.0;
        if (playerRole == PlayerRole.Batsman || playerRole == PlayerRole.Wicketkeeper) {
            for (int i = 0; i < values().length; i++) {
                probabilitySum += probabilityArrayOfBatsman[i];
                if (randomNumber <= probabilitySum) {
                    return values()[i];
                }
            }
        } else if (playerRole == PlayerRole.Bowler) {
            for (int i = 0; i < values().length; i++) {
                probabilitySum += probabilityArrayOfBowler[i];
                if (randomNumber <= probabilitySum) {
                    return values()[i];
                }
            }
        }
        return values()[8];
    }
}
