package com.company.databases;

import com.company.bean.Ball;
import com.company.bean.Over;

import java.sql.*;
import java.util.List;

public class OverDatabase {
    private void insertBallDetails(List<Ball> ballList, int overId, Connection conn) {
        try {
            String getBallIdQuery = "select count(*) from Balls";
            Statement getBallIdStmt = conn.createStatement();
            ResultSet getBallIdResult = getBallIdStmt.executeQuery(getBallIdQuery);
            getBallIdResult.next();
            int ballId = getBallIdResult.getInt(1);

            for(Ball ball : ballList)
            {
                ballId++;
                String insertBallQuery = "insert into Balls (ballId, overId, ballNumberInOver, batsmanId, ballOutcome)" + " values (?, ?, ?, ?, ?)";
                PreparedStatement insertBallStmt = conn.prepareStatement(insertBallQuery);
                insertBallStmt.setInt(1,ballId);
                insertBallStmt.setInt(2,overId);
                insertBallStmt.setInt(3,ball.getBallNumberInOver());
                insertBallStmt.setInt(4,ball.getBatsman().getPlayerId());
                insertBallStmt.setString(5, String.valueOf(ball.getBallOutcome()));
                insertBallStmt.executeUpdate();
                insertBallStmt.close();
            }

            getBallIdStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertOversDetails(List<Over> overList, Connection conn)
    {
        try {
            String getOverIdQuery = "select count(*) from Overs";
            Statement getOverIdStmt = conn.createStatement();
            ResultSet getOverIdResult = getOverIdStmt.executeQuery(getOverIdQuery);
            getOverIdResult.next();
            int overId = getOverIdResult.getInt(1);

            for(Over over : overList)
            {
                overId++;
                String insertOverQuery = "insert into Overs (overId, matchId, battingTeamId, overNumber, bowlerId)" + " values (?, ?, ?, ?, ?)";
                PreparedStatement insertOverStmt = conn.prepareStatement(insertOverQuery);
                insertOverStmt.setInt(1,overId);
                insertOverStmt.setInt(2,over.getMatchId());
                insertOverStmt.setInt(3,over.getBattingTeamId());
                insertOverStmt.setInt(4,over.getOverNumber());
                insertOverStmt.setInt(5,over.getBowler().getPlayerId());
                insertOverStmt.executeUpdate();
                insertBallDetails(over.getBallList(), overId, conn);

                insertOverStmt.close();
            }
            getOverIdStmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
