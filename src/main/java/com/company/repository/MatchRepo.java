package com.company.repository;

import com.company.bean.Match;
import com.company.config.DbConnector;
import com.company.repository.entity.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MatchRepo {

    @Autowired
    private TeamRepo teamRepo;
    public boolean isMatchAvailable(int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String matchQuery = "select count(*) from Matches where matchId = (?)";
            PreparedStatement matchStmt = conn.prepareStatement(matchQuery);
            matchStmt.setInt(1, matchId);
            ResultSet matchResult = matchStmt.executeQuery();
            matchResult.next();
            int count = matchResult.getInt(1);
            DbConnector.closeConnection();
            return count != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getNewMatchId() {
        try {
            Connection conn = DbConnector.getConnection();
            String matchQuery = "select count(*) from Matches";
            Statement matchStmt = conn.createStatement();
            ResultSet matchResult = matchStmt.executeQuery(matchQuery);
            matchResult.next();
            int matchId = matchResult.getInt(1) + 1;
            matchStmt.close();
            DbConnector.closeConnection();
            return matchId;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public MatchInfo getMatch(int matchId) {
        try {
            Connection conn = DbConnector.getConnection();
            String getMatchQuery = "select * from Matches where matchId = (?)";
            PreparedStatement getMatchStmt = conn.prepareStatement(getMatchQuery);
            getMatchStmt.setInt(1, matchId);
            ResultSet matchResult = getMatchStmt.executeQuery();
            matchResult.next();
            String tossWinningTeamName = teamRepo.getTeamName(matchResult.getInt(2));
            String firstBattingTeamName = teamRepo.getTeamName(matchResult.getInt(3));
            String secondBattingTeamName = teamRepo.getTeamName(matchResult.getInt(4));
            String winningTeamName = teamRepo.getTeamName(matchResult.getInt(5));
            return new MatchInfo(matchId,tossWinningTeamName,firstBattingTeamName,secondBattingTeamName,winningTeamName,matchResult.getInt(6),matchResult.getInt(7),matchResult.getInt(8),matchResult.getLong(9),matchResult.getLong(10));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String insertMatchDetails(Match match) {
        try {
            Connection conn = DbConnector.getConnection();
            String matchInsertQuery = "insert into Matches (matchId, tossWinningTeamId, firstBattingTeamId, secondBattingTeamId, winningTeamId, runMargin, wicketMargin, oversInInning, createdTime, modifiedTime, deleted)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement matchInsertStmt = conn.prepareStatement(matchInsertQuery);
            matchInsertStmt.setInt(1, match.getMatchId());
            matchInsertStmt.setInt(2, match.getTossWinningTeam().getTeamId());
            matchInsertStmt.setInt(3, match.getFirstBattingTeam().getTeamId());
            matchInsertStmt.setInt(4, match.getSecondBattingTeam().getTeamId());
            matchInsertStmt.setInt(5, match.getWinningTeam().getTeamId());
            matchInsertStmt.setInt(6, match.getRunMargin());
            matchInsertStmt.setInt(7, match.getWicketMargin());
            matchInsertStmt.setInt(8, match.getOversInInning());
            matchInsertStmt.setLong(9,match.getCreatedTime());
            matchInsertStmt.setLong(10,match.getModifiedTime());
            matchInsertStmt.setBoolean(11,match.isDeleted());
            matchInsertStmt.executeUpdate();
            matchInsertStmt.close();
            DbConnector.closeConnection();
            return "Insert SuccessFully";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error";
        }
    }
}
