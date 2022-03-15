package com.company.utils;
import com.company.bean.Players;
import com.company.enums.PlayerRole;
import com.company.enums.PossibleOutputOfBall;

import java.util.List;

import static java.lang.Math.max;

public final class UtilityOperations {
    public static Players[] swapPlayer(Players strikerBatsman, Players nonStrikerBatsman)
    {
        return new Players[]{nonStrikerBatsman,strikerBatsman};
    }
    public static Players nextBatsman(List<Players> playerList, Players strikerBatsman, Players nonStrikerBatsman)
    {
        strikerBatsman = playerList.get(max(strikerBatsman.getBattingOrder(),nonStrikerBatsman.getBattingOrder()) + 1);
        return strikerBatsman;
    }
    public static PossibleOutputOfBall randomGenerator(Players strikerBatsman) {
        return PossibleOutputOfBall.getOutcomeOfBall(PlayerRole.valueOf(strikerBatsman.getPlayerRole()));
    }
}
