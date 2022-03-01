package com.company.databases;

import com.company.bean.Player;
import com.company.bean.Team;
import java.sql.*;
import java.util.List;

public class PlayerStatsDatabase {
    public static void insertPlayersStats(List<Team> teamList, int matchId, Connection conn)
    {
        try {
            for(Team team : teamList)
            {
                for(Player player : team.getPlayerList())
                {
                    String query = "insert into PlayerStats (playerId, matchId, runScore, runsGiven, ballsFaced, ballsBowled, wicketsTaken, wicketTakenByBowlerId, dotBallsPlayed, oneRunsScored, twoRunsScored, threeRunsScored, fourRunsScored, sixRunsScored, dotBallsBowled, oneRunBallsBowled, twoRunBallsBowled, threeRunBallsBowled, fourRunBallsBowled, sixRunBallsBowled, wideBallsBowled, noBallsBowled) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1,player.getPlayerId());
                    stmt.setInt(2,matchId);
                    stmt.setInt(3,player.getRunScore());
                    stmt.setInt(4,player.getRunsGiven());
                    stmt.setInt(5,player.getBallsFaced());
                    stmt.setInt(6,player.getBallsBowled());
                    stmt.setInt(7,player.getWicketsTaken());
                    int wicketTakenByBowlerId = 0;
                    if(player.getWicketTakenByBowler() != null) wicketTakenByBowlerId = player.getWicketTakenByBowler().getPlayerId();
                    stmt.setInt(8,wicketTakenByBowlerId);
                    stmt.setInt(9,player.getDotBallsPlayed());
                    stmt.setInt(10,player.getOneRunsScored());
                    stmt.setInt(11,player.getTwoRunsScored());
                    stmt.setInt(12,player.getThreeRunsScored());
                    stmt.setInt(13,player.getFourRunsScored());
                    stmt.setInt(14,player.getSixRunsScored());
                    stmt.setInt(15,player.getDotBallsBowled());
                    stmt.setInt(16,player.getOneRunBallsBowled());
                    stmt.setInt(17,player.getTwoRunBallsBowled());
                    stmt.setInt(18,player.getThreeRunBallsBowled());
                    stmt.setInt(19,player.getFourRunBallsBowled());
                    stmt.setInt(20,player.getSixRunBallsBowled());
                    stmt.setInt(21,player.getWideBallsBowled());
                    stmt.setInt(22,player.getNoBallsBowled());
                    stmt.executeUpdate();
                    stmt.close();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
