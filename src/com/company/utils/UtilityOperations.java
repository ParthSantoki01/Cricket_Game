package com.company.utils;
import com.company.bean.Player;
import com.company.bean.Team;
import com.company.enums.PossibleOutputOfBall;

import static java.lang.Math.max;

public class UtilityOperations {
    private UtilityOperations() {}

    public static Player[] swapPlayer(Player strikerBatsman, Player nonStrikerBatsman)
    {
        Player temp = strikerBatsman;
        strikerBatsman = nonStrikerBatsman;
        nonStrikerBatsman = temp;
        return new Player[]{strikerBatsman,nonStrikerBatsman};
    }
    public static Player nextBatsman(Team battingTeam, Player strikerBatsman, Player nonStrikerBatsman)
    {
        strikerBatsman = battingTeam.getPlayerList().get(max(strikerBatsman.getBattingOrder(),nonStrikerBatsman.getBattingOrder()) + 1);
        return strikerBatsman;
    }
    public static PossibleOutputOfBall randomGenerator(Player strikerBatsman) {
        return PossibleOutputOfBall.getOutcomeOfBall(strikerBatsman.getRole());
    }
}
