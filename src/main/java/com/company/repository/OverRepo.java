package com.company.repository;

import com.company.bean.Overs;
import com.company.config.DbConnector;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class OverRepo {
    public int getNewOverId() {
        try {
            Connection conn = DbConnector.getConnection();
            String getOverIdQuery = "select count(*) from Overs";
            Statement getOverIdStmt = conn.createStatement();
            ResultSet getOverIdResult = getOverIdStmt.executeQuery(getOverIdQuery);
            getOverIdResult.next();
            int newOverId = getOverIdResult.getInt(1) + 1;
            DbConnector.closeConnection();
            return newOverId;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String insertOversDetails(List<Overs> oversList){
        try {
            Connection conn = DbConnector.getConnection();
            for(Overs overs : oversList)
            {
                String insertOverQuery = "insert into Overs (overId, matchId, battingTeamId, overNumber, bowlerId, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertOverStmt = conn.prepareStatement(insertOverQuery);
                insertOverStmt.setInt(1, overs.getOverId());
                insertOverStmt.setInt(2, overs.getMatchId());
                insertOverStmt.setInt(3, overs.getBattingTeamId());
                insertOverStmt.setInt(4, overs.getOverNumber());
                insertOverStmt.setInt(5, overs.getBowlerId());
                insertOverStmt.setLong(6, overs.getCreatedTime());
                insertOverStmt.setLong(7, overs.getModifiedTime());
                insertOverStmt.setBoolean(8, overs.isDeleted());
                insertOverStmt.executeUpdate();
                insertOverStmt.close();
            }
            DbConnector.closeConnection();
            return "Insert SuccessFully";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    public boolean isOverAvailableInMatch(int matchId, int battingTeamId, int overNumber) {
        try {
            Connection conn = DbConnector.getConnection();
            String overQuery = "select count(ballOutcome) from Balls where overId in (select overId from Overs where matchId = (?) and battingTeamId = (?) and overNumber = (?) and deleted = false)";
            PreparedStatement overStmt = conn.prepareStatement(overQuery);
            overStmt.setInt(1, matchId);
            overStmt.setInt(2, battingTeamId);
            overStmt.setInt(3, overNumber);
            ResultSet overResult = overStmt.executeQuery();
            overResult.next();
            int count = overResult.getInt(1);
            DbConnector.closeConnection();
            return count != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<String> getOverDetailInMatch(int matchId, int battingTeamId, int overNumber) {
        try {
            Connection conn = DbConnector.getConnection();
            String overQuery = "select ballOutcome from Balls where overId in (select overId from Overs where matchId = (?) and battingTeamId = (?) and overNumber = (?))";
            PreparedStatement overStmt = conn.prepareStatement(overQuery);
            overStmt.setInt(1, matchId);
            overStmt.setInt(2, battingTeamId);
            overStmt.setInt(3, overNumber);
            ResultSet overResult = overStmt.executeQuery();
            List<String> oversDetails = new ArrayList<>();
            while(overResult.next()) oversDetails.add(overResult.getString(1));
            DbConnector.closeConnection();
            return oversDetails;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
