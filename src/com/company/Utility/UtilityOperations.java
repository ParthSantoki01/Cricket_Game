package com.company.Utility;
import com.company.Player;
import com.company.Team;

public class UtilityOperations {
    private UtilityOperations() {}

    public static Player[] swapPlayer(Player strikerBatsman, Player nonStrikerBatsman)
    {
        Player temp = strikerBatsman;
        strikerBatsman = nonStrikerBatsman;
        nonStrikerBatsman = temp;
        return new Player[]{strikerBatsman,nonStrikerBatsman};
    }
    public static Team[] swapTeam(Team firstTeam, Team secondTeam)
    {
        Team temp = firstTeam;
        firstTeam = secondTeam;
        secondTeam = temp;
        return new Team[]{firstTeam,secondTeam};
    }
}
