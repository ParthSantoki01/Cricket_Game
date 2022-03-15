package com.company.repository;

import com.company.bean.Balls;
import com.company.config.DbConnector;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.List;

@Service
public class BallRepo {
    public int getNewBallID() {
        try {
            Connection conn = DbConnector.getConnection();
            String getOverIdQuery = "select count(*) from Balls";
            Statement getOverIdStmt = conn.createStatement();
            ResultSet getOverIdResult = getOverIdStmt.executeQuery(getOverIdQuery);
            getOverIdResult.next();
            int newBallId = getOverIdResult.getInt(1) + 1;
            DbConnector.closeConnection();
            return newBallId;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String insertBallsDetails(List<Balls> ballsList) {
        try {
            Connection conn = DbConnector.getConnection();
            for(Balls balls : ballsList)
            {
                String insertBallQuery = "insert into Balls (ballId, overId, ballNumberInOver, batsmanId, ballOutcome, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertBallStmt = conn.prepareStatement(insertBallQuery);
                insertBallStmt.setInt(1, balls.getBallId());
                insertBallStmt.setInt(2, balls.getOverId());
                insertBallStmt.setInt(3, balls.getBallNumberInOver());
                insertBallStmt.setInt(4, balls.getBatsmanId());
                insertBallStmt.setString(5, balls.getBallOutcome());
                insertBallStmt.setLong(6, balls.getCreatedTime());
                insertBallStmt.setLong(7, balls.getModifiedTime());
                insertBallStmt.setBoolean(8, balls.isDeleted());
                insertBallStmt.executeUpdate();
                insertBallStmt.close();
            }
            DbConnector.closeConnection();
            return "Insert SuccessFully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
