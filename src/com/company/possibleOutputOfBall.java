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

    private final String displayName;

    possibleOutputOfBall(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Probability of getting Index of possible output for Batsman and Bowler.
    private static final double[] probabilityArrayOfBatsman = {0.3, 0.25, 0.15, 0.05, 0.12, 0.08, 0.025, 0.015, 0.01};
    private static final double[] probabilityArrayOfBowler =  {0.5, 0.2, 0.1, 0.03, 0.05, 0.03, 0.025, 0.015, 0.5};

    public static possibleOutputOfBall getOutcomeOfBall(String role) {

        double randomNumber = Math.random();
        double probabilitySum = 0.0;
        if(role.equals("Batsman"))
        {
            for(int i = 0; i < values().length; i++)
            {
                probabilitySum += probabilityArrayOfBatsman[i];
                if(randomNumber <= probabilitySum)
                {
                    return values()[i];
                }
            }
        }
        else
        {
            for(int i = 0; i < values().length; i++)
            {
                probabilitySum += probabilityArrayOfBowler[i];
                if(randomNumber <= probabilitySum)
                {
                    return values()[i];
                }
            }
        }
        return values()[8];
    }
}
