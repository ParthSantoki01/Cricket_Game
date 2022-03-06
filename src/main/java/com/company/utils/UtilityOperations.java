package com.company.utils;
import com.company.bean.Player;
import com.company.bean.Team;
import com.company.enums.PossibleOutputOfBall;

import static java.lang.Math.max;

public final class UtilityOperations {
    public static Player[] swapPlayer(Player strikerBatsman, Player nonStrikerBatsman)
    {
        return new Player[]{nonStrikerBatsman,strikerBatsman};
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
