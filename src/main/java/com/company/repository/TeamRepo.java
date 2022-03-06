package com.company.repository;

import com.company.bean.Team;
import com.company.config.DbConnector;
import com.company.repository.entity.PlayerInfo;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamRepo {
    public int insertTeamData(String[] teamDetails) {
        try {
            int teamId = getNewTeamId();
            Connection conn = DbConnector.getConnection();
            String query = "insert into Teams (teamId, teamName, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            stmt.setString(2, teamDetails[0]);
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


    public boolean getTeams(List<Team> teamList) {
        try {
            Connection conn = DbConnector.getConnection();
            String noOfTeamQuery = "select count(*) from Teams";
            Statement noOfTeamStmt = conn.createStatement();
            ResultSet noOfTeamResult = noOfTeamStmt.executeQuery(noOfTeamQuery);
            noOfTeamResult.next();
            int noOfTeamInDatabase = noOfTeamResult.getInt(1);

            if(noOfTeamInDatabase < 2)
            {
                return false;
            }

            int team1Id;
            int team2Id;
            do{
                team1Id = 1 + (int) (Math.random() * noOfTeamInDatabase);
                team2Id = 1 + (int) (Math.random() * noOfTeamInDatabase);
            }
            while (team1Id == team2Id);

            int[] randomTeamIds = {team1Id,team2Id};

            for (int randomTeamId : randomTeamIds) {
                String teamQuery = "select * from Teams where teamId = (?)";
                PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
                teamStmt.setInt(1, randomTeamId);
                ResultSet teamResult = teamStmt.executeQuery();
                teamResult.next();
                teamList.add(new Team(teamResult.getInt(1), teamResult.getString(2), teamResult.getLong(3), teamResult.getLong(4), teamResult.getBoolean(5)));
                teamStmt.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean isTeamAvailable(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String teamQuery = "select count(*) from Teams where teamId = (?)";
            PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
            teamStmt.setInt(1, teamId);
            ResultSet teamResult = teamStmt.executeQuery();
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
    public List<PlayerInfo> getTeamPlayers(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String teamQuery = "select * from Players where teamId = (?)";
            PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
            teamStmt.setInt(1, teamId);
            ResultSet playerResults = teamStmt.executeQuery();
            List<PlayerInfo> playerList = new ArrayList<>();
            while (playerResults.next())
            {
                playerList.add(new PlayerInfo(String.valueOf(playerResults.getInt(1)),playerResults.getString(2),String.valueOf(playerResults.getInt(3)),playerResults.getString(4),String.valueOf(playerResults.getInt(5))));
            }
            DbConnector.closeConnection();
            return playerList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getNewTeamId() {
        try {
            Connection conn = DbConnector.getConnection();
            String teamQuery = "select count(*) from Teams";
            Statement teamStmt = conn.createStatement();
            ResultSet teamResults = teamStmt.executeQuery(teamQuery);
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
    public List<String> getTeamInfo(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String teamQuery = "select * from Teams where teamId = (?)";
            PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
            teamStmt.setInt(1, teamId);
            ResultSet teamResult = teamStmt.executeQuery();
            teamResult.next();
            List<String> teamInformation = new ArrayList<>();
            teamInformation.add(String.valueOf(teamResult.getInt(1)));
            teamInformation.add(teamResult.getString(2));
            DbConnector.closeConnection();
            return teamInformation;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getTeamName(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String teamQuery = "select teamName from Teams where teamId = (?)";
            PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
            teamStmt.setInt(1, teamId);
            ResultSet teamResults = teamStmt.executeQuery();
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
            String teamQuery = "select ballOutcome from Balls join (select * from Overs where matchId = (?)) as overs where Balls.overId = overs.overId and battingTeamId = (?)";
            PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
            teamStmt.setInt(1, matchId);
            teamStmt.setInt(2, teamId);
            ResultSet teamResult = teamStmt.executeQuery();
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

    public void getTeamsIdInMatch(List<Integer> teamIdList, int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String teamQuery = "select firstBattingTeamId,secondBattingTeamId from Matches where matchId = (?);";
            PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
            teamStmt.setInt(1, matchId);
            ResultSet teamResult = teamStmt.executeQuery();
            teamResult.next();
            teamIdList.add(teamResult.getInt(1));
            teamIdList.add(teamResult.getInt(2));
            DbConnector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
