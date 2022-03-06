package com.company.repository;

import com.company.bean.Player;
import com.company.bean.Team;
import com.company.config.DbConnector;
import com.company.enums.PlayerRole;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerRepo {
    public boolean isPlayerAvailable(int playerId) {
        try {
            Connection conn = DbConnector.getConnection();
            String playerQuery = "select count(*) from Players where playerId = (?)";
            PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
            playerStmt.setInt(1, playerId);
            ResultSet playerResult = playerStmt.executeQuery();
            playerResult.next();
            int count = playerResult.getInt(1);
            DbConnector.closeConnection();
            if(count == 0)return false;
            else return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getPlayerNameById(int playerId) {
        try {
            Connection conn = DbConnector.getConnection();
            String playerQuery = "select playerName from Players where playerId = (?)";
            PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
            playerStmt.setInt(1, playerId);
            ResultSet playerResults = playerStmt.executeQuery();
            playerResults.next();
            String playerName = playerResults.getString(1);
            DbConnector.closeConnection();
            return playerName;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error";
        }
    }
    public int getNewPlayerId() {
        try {
            Connection conn = DbConnector.getConnection();
            String playerQuery = "select count(*) from Players";
            Statement playerStmt = conn.createStatement();
            ResultSet playerResults = playerStmt.executeQuery(playerQuery);
            playerResults.next();
            int lastPlayerId = playerResults.getInt(1) + 1;
            playerResults.close();
            DbConnector.closeConnection();
            return lastPlayerId;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public void getPlayerInfo(int playerId, List<String> playerInformation) {
        try {
            Connection conn = DbConnector.getConnection();
            String playerQuery = "select * from Players where playerId = (?)";
            PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
            playerStmt.setInt(1, playerId);
            ResultSet playerResults = playerStmt.executeQuery();
            playerResults.next();
            playerInformation.add(String.valueOf(playerId));
            playerInformation.add(playerResults.getString(2));
            playerInformation.add(String.valueOf(playerResults.getInt(3)));
            playerInformation.add(playerResults.getString(4));
            playerInformation.add(String.valueOf(playerResults.getInt(5)));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void getPlayerBattingOutcomeInMatch(int playerId, int matchId, List<List<String>> battingOutcome) {
        try {
            Connection conn = DbConnector.getConnection();
            String playerQuery = "select ballOutcome,bowlerId from Balls join (select * from Overs where matchId = (?)) as overs where Balls.overId = overs.overId and Balls.batsmanId = (?)";
            PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
            playerStmt.setInt(1, matchId);
            playerStmt.setInt(2, playerId);
            ResultSet playerResults = playerStmt.executeQuery();
            while(playerResults.next())
            {
                List<String> outcome = new ArrayList<>();
                outcome.add(playerResults.getString(1));
                outcome.add(String.valueOf(playerResults.getInt(2)));
                battingOutcome.add(outcome);
            }
            DbConnector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getPlayerBowlerOutcomeInMatch(int playerId, int matchId, List<String> bowlingOutcome) {
        try {
            Connection conn = DbConnector.getConnection();
            String playerQuery = "select ballOutcome from Balls join (select * from Overs where matchId = (?) and bowlerId = (?)) as overs where Balls.overId = overs.overId";
            PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
            playerStmt.setInt(1, matchId);
            playerStmt.setInt(2, playerId);
            ResultSet playerResults = playerStmt.executeQuery();
            while(playerResults.next()) bowlingOutcome.add(playerResults.getString(1));
            DbConnector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean setPlayerInTeam(List<Team> teamList) {
        try {
            Connection conn = DbConnector.getConnection();
            for(int i = 0; i < 2; i++)
            {
                String playerQuery = "select * from Players where teamId = (?)";
                PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
                playerStmt.setInt(1, teamList.get(i).getTeamId());
                ResultSet playerResults = playerStmt.executeQuery();
                while (playerResults.next())
                {
                    String playerRoleInString = playerResults.getString(4);
                    PlayerRole playerRole = PlayerRole.valueOf(playerRoleInString);
                    teamList.get(i).getPlayerList().add(new Player(playerResults.getInt(1),playerResults.getString(2),playerResults.getInt(3),playerRole,playerResults.getInt(5),playerResults.getLong(6),playerResults.getLong(7),playerResults.getBoolean(8)));
                }
                playerStmt.close();
                if(teamList.get(i).getPlayerList().size() != 11)
                {
                    return false;
                }
            }
            DbConnector.closeConnection();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public void addPlayerInTeam(int teamId, String[] teamDetails) {
        try {
            int startPlayerId = getNewPlayerId();
            Connection conn = DbConnector.getConnection();
            for (int battingOrder = 0; battingOrder < 11; battingOrder++)
            {
                int playerId = startPlayerId + battingOrder;
                PlayerRole playerRole;
                if(battingOrder < 5) playerRole = PlayerRole.BATSMAN;
                else if(battingOrder == 5)playerRole = PlayerRole.WICKETKEEPER;
                else playerRole = PlayerRole.BOWLER;
                String query = "insert into Players (playerId, playerName, battingOrder, playerRole, teamId, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, playerId);
                stmt.setString(2, teamDetails[battingOrder+1]);
                stmt.setInt(3, battingOrder);
                stmt.setString(4, String.valueOf(playerRole));
                stmt.setInt(5, teamId);
                stmt.setLong(6,System.currentTimeMillis());
                stmt.setLong(7,System.currentTimeMillis());
                stmt.setBoolean(8,false);
                stmt.executeUpdate();
            }
            DbConnector.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
