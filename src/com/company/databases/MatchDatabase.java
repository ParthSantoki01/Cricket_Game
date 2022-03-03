package com.company.databases;

import com.company.bean.ScoreBoard;
import java.sql.*;

public class MatchDatabase {
    public void insertMatchDetails(ScoreBoard scoreBoard, Connection conn)
    {
        try {
            String query = "insert into Matches (matchId, tossWinningTeamId, firstBattingTeamId, secondBattingTeamId, winningTeamId, runMargin, wicketMargin, oversInInning)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,scoreBoard.getMatchId());
            stmt.setInt(2,scoreBoard.getTossWinningTeam().getTeamId());
            stmt.setInt(3,scoreBoard.getFirstBattingTeam().getTeamId());
            stmt.setInt(4,scoreBoard.getSecondBattingTeam().getTeamId());
            stmt.setInt(5,scoreBoard.getWinningTeam().getTeamId());
            stmt.setInt(6,scoreBoard.getRunMargin());
            stmt.setInt(7,scoreBoard.getWicketMargin());
            stmt.setInt(8,scoreBoard.getOversInInning());
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
