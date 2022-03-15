package com.company.repository;

import com.company.bean.Teams;
import com.company.config.DbConnector;
import com.company.request.ReqObjNewTeam;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamRepo {
    public int getNewTeamId() {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Teams";
            Statement stmt = conn.createStatement();
            ResultSet teamResults = stmt.executeQuery(query);
            teamResults.next();
            int lastTeamId = teamResults.getInt(1) + 1;
            teamResults.close();
            DbConnector.closeConnection();
            return lastTeamId;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public int insertNewTeamDetails(ReqObjNewTeam newTeamObj) {
        try {
            int teamId = getNewTeamId();
            Connection conn = DbConnector.getConnection();
            String query = "insert into Teams (teamId, teamName, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            stmt.setString(2, newTeamObj.getTeamName());
            stmt.setLong(3,System.currentTimeMillis());
            stmt.setLong(4,System.currentTimeMillis());
            stmt.setBoolean(5,false);
            stmt.executeUpdate();
            stmt.close();
            DbConnector.closeConnection();
            return teamId;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int noOfTeamInDatabase() {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Teams where deleted = false";
            Statement stmt = conn.createStatement();
            ResultSet teamResults = stmt.executeQuery(query);
            teamResults.next();
            int noOfTeams = teamResults.getInt(1) + 1;
            DbConnector.closeConnection();
            return noOfTeams;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public boolean isTeamAvailable(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Teams where teamId = (?) and deleted = false";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            ResultSet teamResult = stmt.executeQuery();
            teamResult.next();
            int count = teamResult.getInt(1);
            DbConnector.closeConnection();
            return count != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Teams getTeamDetails(int teamId)
    {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select * from Teams where teamId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            ResultSet teamResult = stmt.executeQuery();
            teamResult.next();
            Teams team = new Teams(teamResult.getInt("teamId"),teamResult.getString("teamName"),teamResult.getLong("createdTime"),teamResult.getLong("modifiedTime"),teamResult.getBoolean("deleted"));
            DbConnector.closeConnection();
            return team;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public String getTeamName(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select teamName from Teams where teamId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            ResultSet teamResults = stmt.executeQuery();
            teamResults.next();
            return teamResults.getString(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error";
        }
    }

    public List<String> getTeamOutcomes(int teamId, int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select ballOutcome from Balls join (select * from Overs where matchId = (?)) as overs where Balls.overId = overs.overId and battingTeamId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            stmt.setInt(2, teamId);
            ResultSet teamResult = stmt.executeQuery();
            List<String> teamOutcome = new ArrayList<>();
            while (teamResult.next())
            {
                teamOutcome.add(teamResult.getString(1));
            }
            DbConnector.closeConnection();
            return teamOutcome;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateTeamName(String teamName, int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "update Teams set teamName = (?), modifiedTime = (?) where teamId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,teamName);
            stmt.setLong(2,System.currentTimeMillis());
            stmt.setInt(3, teamId);
            stmt.executeUpdate();
            DbConnector.closeConnection();
            return "Success, TeamName Updated";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "Error, Team Name Can not Update";
        }
    }
}
