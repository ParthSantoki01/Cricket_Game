package com.company.databases;

import com.company.been.Player;
import com.company.been.Team;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.List;

public class UpdatePlayerStatsDatabase {
    public static void enterPlayersStats(List<Team> teamList, int matchId, Connection conn)
    {
        try {
            for(Team team : teamList)
            {
                for(Player player : team.getPlayerList())
                {
                    String query = "insert into PlayerStats (playerId, matchId, runScore, runsGiven, ballsFaced, ballsDelivered, wicketsTaken, wicketTakenByBowlerId, noOfDotBall, noOf1sScore, noOf2sScore, noOf3sScore, noOfFourScore, noOfSixScore, noOfDotBallGiven, noOf1sGiven, noOf2sGiven, noOf3sGiven, noOfFourGiven, noOfSixGiven, wideBallDelivered, noBallDelivered)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1,player.getId());
                    stmt.setInt(2,matchId);
                    stmt.setInt(3,player.getRunScore());
                    stmt.setInt(4,player.getRunsGiven());
                    stmt.setInt(5,player.getBallsFaced());
                    stmt.setInt(6,player.getBallsDelivered());
                    stmt.setInt(7,player.getWicketsTaken());
                    int wicketTakenByBowlerId = 0;
                    if(player.getWicketTakenBy() != null) wicketTakenByBowlerId = player.getWicketTakenBy().getId();
                    stmt.setInt(8,wicketTakenByBowlerId);
                    stmt.setInt(9,player.getNoOfDotBall());
                    stmt.setInt(10,player.getNoOf1sScore());
                    stmt.setInt(11,player.getNoOf2sScore());
                    stmt.setInt(12,player.getNoOf3sScore());
                    stmt.setInt(13,player.getNoOfFourScore());
                    stmt.setInt(14,player.getNoOfSixScore());
                    stmt.setInt(15,player.getNoOfDotBallGiven());
                    stmt.setInt(16,player.getNoOf1sGiven());
                    stmt.setInt(17,player.getNoOf2sGiven());
                    stmt.setInt(18,player.getNoOf3sGiven());
                    stmt.setInt(19,player.getNoOfFourGiven());
                    stmt.setInt(20,player.getNoOfSixGiven());
                    stmt.setInt(21,player.getWideBallDelivered());
                    stmt.setInt(22,player.getNoBallDelivered());

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
