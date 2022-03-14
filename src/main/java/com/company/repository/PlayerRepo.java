package com.company.repository;

import com.company.bean.Players;
import com.company.config.DbConnector;
import com.company.enums.PlayerRole;
import com.company.request.ReqObjNewTeam;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerRepo {
    public int getNewPlayerId() {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Players";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            result.next();
            int lastPlayerId = result.getInt(1) + 1;
            result.close();
            DbConnector.closeConnection();
            return lastPlayerId;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public boolean insertNewPlayersDetails(int teamId, ReqObjNewTeam newTeamObj) {
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
                stmt.setString(2, newTeamObj.getPlayerNameList().get(battingOrder));
                stmt.setInt(3, battingOrder);
                stmt.setString(4, String.valueOf(playerRole));
                stmt.setInt(5, teamId);
                stmt.setLong(6,System.currentTimeMillis());
                stmt.setLong(7,System.currentTimeMillis());
                stmt.setBoolean(8,false);
                stmt.executeUpdate();
            }
            DbConnector.closeConnection();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Players> getTeamPlayers(int teamId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select * from Players where teamId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            ResultSet result = stmt.executeQuery();
            List<Players> playerList = new ArrayList<>();
            while (result.next()) {
                playerList.add(new Players(result.getInt(1),result.getString(2),result.getInt(3), PlayerRole.valueOf(result.getString(4)),result.getInt(5),result.getLong(6),result.getLong(7),result.getBoolean(8)));
            }
            DbConnector.closeConnection();
            return playerList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isPlayerAvailable(int playerId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select count(*) from Players where playerId = (?) and deleted = false";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, playerId);
            ResultSet playerResult = stmt.executeQuery();
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
    public Players getPlayer(int playerId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select * from Players where playerId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, playerId);
            ResultSet result = stmt.executeQuery();
            result.next();
            Players player = new Players(result.getInt(1),result.getString(2),result.getInt(3), PlayerRole.valueOf(result.getString(4)),result.getInt(5),result.getLong(6),result.getLong(7),result.getBoolean(8));
            return player;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isPlayerAvailableInMatch(int playerId, int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = " select count(*) from (select firstBattingTeamId,secondBattingTeamId,teamId from matches join players where players.playerId = (?) and matches.matchId = (?) and players.deleted = false and matches.deleted = false) as t1 where t1.firstBattingTeamId = t1.teamId or t1.secondBattingTeamId = t1.teamId";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setInt(2, matchId);
            ResultSet playerResult = stmt.executeQuery();
            playerResult.next();
            int count = playerResult.getInt(1);
            DbConnector.closeConnection();
            return count != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getPlayerNameById(int playerId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select playerName from Players where playerId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, playerId);
            ResultSet result = stmt.executeQuery();
            result.next();
            String playerName = result.getString(1);
            DbConnector.closeConnection();
            return playerName;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error";
        }
    }
    public List<List<String>> getPlayerBattingOutcomeInMatch(int playerId, int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select ballOutcome,bowlerId from Balls join (select * from Overs where matchId = (?)) as overs where Balls.overId = overs.overId and Balls.batsmanId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            stmt.setInt(2, playerId);
            ResultSet result = stmt.executeQuery();
            List<List<String>> battingOutcome = new ArrayList<>();
            while(result.next())
            {
                List<String> outcome = new ArrayList<>();
                outcome.add(result.getString(1));
                outcome.add(String.valueOf(result.getInt(2)));
                battingOutcome.add(outcome);
            }
            DbConnector.closeConnection();
            return battingOutcome;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<String> getPlayerBowlerOutcomeInMatch(int playerId, int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "select ballOutcome from Balls join (select * from Overs where matchId = (?) and bowlerId = (?)) as overs where Balls.overId = overs.overId";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            stmt.setInt(2, playerId);
            ResultSet result = stmt.executeQuery();
            List<String> bowlingOutcome = new ArrayList<>();
            while(result.next()) bowlingOutcome.add(result.getString(1));
            DbConnector.closeConnection();
            return bowlingOutcome;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String updatePlayerName(String playerName, int playerId) {
        try {
            Connection conn = DbConnector.getConnection();
            String query = "update Players set playerName = (?), modifiedTime = (?) where playerId = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,playerName);
            stmt.setLong(2,System.currentTimeMillis());
            stmt.setInt(3, playerId);
            stmt.executeUpdate();
            DbConnector.closeConnection();
            return "Success, PlayerName Updated";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "Error, Player Name Can not Update";
        }
    }
    public String getBowlerInOver(int matchId, int battingTeamId, int overNumber) {
        try {
            Connection conn = DbConnector.getConnection();
            String bowlerQuery = "select bowlerId from Overs where matchId = (?) and battingTeamId = (?) and overNumber = (?)";
            PreparedStatement bowlerStmt = conn.prepareStatement(bowlerQuery);
            bowlerStmt.setInt(1, matchId);
            bowlerStmt.setInt(2, battingTeamId);
            bowlerStmt.setInt(3, overNumber);
            ResultSet bowlerResult = bowlerStmt.executeQuery();
            bowlerResult.next();
            int bowlerId = bowlerResult.getInt(1);
            DbConnector.closeConnection();
            return getPlayerNameById(bowlerId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
