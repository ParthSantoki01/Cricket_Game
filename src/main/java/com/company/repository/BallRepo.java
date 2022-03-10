package com.company.repository;

import com.company.bean.Ball;
import com.company.bean.Over;
import com.company.config.DbConnector;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
@Service
public class BallRepo {
    public int getNewBallID() {
        int ballId = 0;
        try {
            Connection conn = DbConnector.getConnection();
            String getOverIdQuery = "select count(*) from Balls";
            Statement getOverIdStmt = conn.createStatement();
            ResultSet getOverIdResult = getOverIdStmt.executeQuery(getOverIdQuery);
            getOverIdResult.next();
            ballId = getOverIdResult.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ballId;
    }
    public String insertBallsDetails(List<Over> overList) {
        try {
            Connection conn = DbConnector.getConnection();
            for(Over over : overList)
            {
                for(Ball ball : over.getBallList())
                {
                    String insertBallQuery = "insert into Balls (ballId, overId, ballNumberInOver, batsmanId, ballOutcome, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement insertBallStmt = conn.prepareStatement(insertBallQuery);
                    insertBallStmt.setInt(1,ball.getBallId());
                    insertBallStmt.setInt(2,ball.getOverId());
                    insertBallStmt.setInt(3,ball.getBallNumberInOver());
                    insertBallStmt.setInt(4,ball.getBatsman().getPlayerId());
                    insertBallStmt.setString(5, String.valueOf(ball.getBallOutcome()));
                    insertBallStmt.setLong(6,ball.getCreatedTime());
                    insertBallStmt.setLong(7,ball.getModifiedTime());
                    insertBallStmt.setBoolean(8,ball.isDeleted());
                    insertBallStmt.executeUpdate();
                    insertBallStmt.close();
                }
            }
            DbConnector.closeConnection();
            return "Insert SuccessFully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
