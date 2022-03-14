package com.company.repository;

import com.company.bean.Matches;
import com.company.config.DbConnector;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchRepo {
    public int getNewMatchId() {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Matches";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            result.next();
            int matchId = result.getInt(1) + 1;
            stmt.close();
            DbConnector.closeConnection();
            return matchId;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public String insertMatchDetails(Matches matches) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "insert into Matches (matchId, tossWinningTeamId, firstBattingTeamId, secondBattingTeamId, winningTeamId, runMargin, wicketMargin, oversInInning, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matches.getMatchId());
            stmt.setInt(2, matches.getTossWinningTeamId());
            stmt.setInt(3, matches.getFirstBattingTeamId());
            stmt.setInt(4, matches.getSecondBattingTeamId());
            stmt.setInt(5, matches.getWinningTeamId());
            stmt.setInt(6, matches.getRunMargin());
            stmt.setInt(7, matches.getWicketMargin());
            stmt.setInt(8, matches.getOversInInning());
            stmt.setLong(9, matches.getCreatedTime());
            stmt.setLong(10, matches.getModifiedTime());
            stmt.setBoolean(11, matches.isDeleted());
            stmt.executeUpdate();
            stmt.close();
            DbConnector.closeConnection();
            return "Insert SuccessFully";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error";
        }
    }
    public boolean isMatchAvailable(int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Matches where matchId = (?) and deleted = false";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            ResultSet result = stmt.executeQuery();
            result.next();
            int count = result.getInt(1);
            DbConnector.closeConnection();
            return count != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Matches getMatch(int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String getMatchQuery = "select * from Matches where matchId = (?)";
            PreparedStatement getMatchStmt = conn.prepareStatement(getMatchQuery);
            getMatchStmt.setInt(1, matchId);
            ResultSet result = getMatchStmt.executeQuery();
            result.next();
            return new Matches(result.getInt(1),result.getInt(2),result.getInt(3),result.getInt(4),result.getInt(5),result.getInt(6),result.getInt(7),result.getInt(8),result.getLong(9),result.getLong(10),result.getBoolean(11));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String deleteMatchDetails(int matchId) {
        if(!isMatchAvailable(matchId)) return "Error, MatchId Is Invalid.";
        try {
            Connection conn = DbConnector.getConnection();
            String query1 = "update Balls set deleted = true where overId in (select overId from Overs where matchId = " + matchId +");";
            String query2 = "update Overs set deleted = true where matchId = " + matchId + ";";
            String query3 = "update Matches set deleted = true where matchId = " + matchId + ";";

            Statement stmt = conn.createStatement();
            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            stmt.executeBatch();
            conn.close();
            return "Match " + matchId + " was Deleted.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error.";
        }
    }
    public List<Integer> getTeamsIdInMatch(int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select firstBattingTeamId,secondBattingTeamId from Matches where matchId = (?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            ResultSet teamResult = stmt.executeQuery();
            teamResult.next();
            List<Integer> teamIdList = new ArrayList<>();
            teamIdList.add(teamResult.getInt(1));
            teamIdList.add(teamResult.getInt(2));
            DbConnector.closeConnection();
            return teamIdList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getOverInInning(int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String getMatchQuery = "select oversInInning from Matches where matchId = (?)";
            PreparedStatement getMatchStmt = conn.prepareStatement(getMatchQuery);
            getMatchStmt.setInt(1, matchId);
            ResultSet result = getMatchStmt.executeQuery();
            result.next();
            int overInInning = result.getInt(1);
            DbConnector.closeConnection();
            return overInInning;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
