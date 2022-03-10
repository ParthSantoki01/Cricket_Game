package com.company.repository;

import com.company.bean.Over;
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
            int noOfOvers = getOverIdResult.getInt(1);
            DbConnector.closeConnection();
            return noOfOvers;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String insertOversDetails(List<Over> overList){
        try {
            Connection conn = DbConnector.getConnection();
            for(Over over : overList)
            {
                String insertOverQuery = "insert into Overs (overId, matchId, battingTeamId, overNumber, bowlerId, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertOverStmt = conn.prepareStatement(insertOverQuery);
                insertOverStmt.setInt(1,over.getOverId());
                insertOverStmt.setInt(2,over.getMatchId());
                insertOverStmt.setInt(3,over.getBattingTeamId());
                insertOverStmt.setInt(4,over.getOverNumber());
                insertOverStmt.setInt(5,over.getBowler().getPlayerId());
                insertOverStmt.setLong(6,over.getCreatedTime());
                insertOverStmt.setLong(7,over.getModifiedTime());
                insertOverStmt.setBoolean(8,over.isDeleted());
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
}
