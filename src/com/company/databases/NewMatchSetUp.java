package com.company.databases;

import com.company.bean.*;
import com.company.enums.PlayerRole;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class NewMatchSetUp {
    private static void setPlayersInTeam(List<Team> teamList, Connection conn, int teamId, int teamNo)
    {
        try {
            String playerQuery = "select * from Players where teamId = (?)";
            PreparedStatement playerStmt = conn.prepareStatement(playerQuery);
            playerStmt.setInt(1, teamId);
            ResultSet playerResults = playerStmt.executeQuery();
            while (playerResults.next())
            {
                String playerRoleInString = playerResults.getString(4);
                PlayerRole playerRole = null;
                if(String.valueOf(PlayerRole.Batsman).equals(playerRoleInString)) playerRole = PlayerRole.Batsman;
                else if(String.valueOf(PlayerRole.Bowler).equals(playerRoleInString)) playerRole = PlayerRole.Bowler;
                else playerRole = PlayerRole.Wicketkeeper;
                teamList.get(teamNo).getPlayerList().add(new Player(playerResults.getInt(1),playerResults.getString(2),playerResults.getInt(3),playerRole,playerResults.getInt(5),playerResults.getInt(6)));
            }
            playerStmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private static void setTeams(List<Team> teamList, Connection conn)
    {
        try {
            String noOfTeamQuery = "select count(*) from Teams";
            Statement noOfTeamStmt = conn.createStatement();
            ResultSet noOfTeamResult = noOfTeamStmt.executeQuery(noOfTeamQuery);
            noOfTeamResult.next();
            int noOfTeamInDatabase = noOfTeamResult.getInt(1);

            int team1Id;
            int team2Id;
            do{
                team1Id = 1 + (int) (Math.random() * noOfTeamInDatabase);
                team2Id = 1 + (int) (Math.random() * noOfTeamInDatabase);
            }
            while (team1Id == team2Id);

            int[] randomTeamIds = {team1Id,team2Id};

            for (int i = 0; i < randomTeamIds.length; i++)
            {
                String teamQuery = "select * from Teams where teamId = (?)";
                PreparedStatement teamStmt = conn.prepareStatement(teamQuery);
                teamStmt.setInt(1, randomTeamIds[i]);
                ResultSet teamResult = teamStmt.executeQuery();
                teamResult.next();
                teamList.add(new Team(teamResult.getInt(1), teamResult.getString(2)));
                teamStmt.close();

                setPlayersInTeam(teamList,conn,randomTeamIds[i],i);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static CricketMatch newCricketGame(Scanner sc, List<Team> teamList, int totalOver, Connection conn)
    {
        int matchId = 0;
        try {
            String matchQuery = "select count(*) from Matches";
            Statement matchStmt = conn.createStatement();
            ResultSet matchResult = matchStmt.executeQuery(matchQuery);
            matchResult.next();
            int noOfTeamInDatabase = matchResult.getInt(1);
            matchId = noOfTeamInDatabase + 1;
            matchStmt.close();

            setTeams(teamList,conn);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new CricketMatch(totalOver,matchId);
    }
}
