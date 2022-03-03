package com.company.databases;

import com.company.enums.PlayerRole;
import java.sql.*;
import java.util.Scanner;

public class CreateNewTeam {
    private void addPlayerInTeam(Scanner sc, Connection conn, int teamId)
    {
        try {
            String noOfPlayerQuery = "select count(*) from Players";
            Statement playerStmt = conn.createStatement();
            ResultSet playerResult = playerStmt.executeQuery(noOfPlayerQuery);
            playerResult.next();
            int noOfPlayerInDatabase = playerResult.getInt(1);
            int startPlayerId = noOfPlayerInDatabase + 1;

            for (int battingOrder = 0; battingOrder < 11; battingOrder++)
            {
                int playerId = startPlayerId + battingOrder;
                System.out.print("Enter Player " + (battingOrder+1) + " Player Name: ");
                String PlayerName = sc.nextLine();

                PlayerRole playerRole;
                if(battingOrder < 5) playerRole = PlayerRole.BATSMAN;
                else if(battingOrder == 5)playerRole = PlayerRole.WICKETKEEPER;
                else playerRole = PlayerRole.BOWLER;

                String query = "insert into Players (playerId, playerName, battingOrder, playerRole, teamId)" + " values (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, playerId);
                stmt.setString(2, PlayerName);
                stmt.setInt(3, battingOrder);
                stmt.setString(4, String.valueOf(playerRole));
                stmt.setInt(5, teamId);

                int x = stmt.executeUpdate();
                System.out.println(x + " player details inserted into database");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void makeTeam(Scanner sc, Connection conn) {
        try {
            String noOfTeamQuery = "select count(*) from Teams";
            Statement teamStmt = conn.createStatement();
            ResultSet teamResult = teamStmt.executeQuery(noOfTeamQuery);
            teamResult.next();
            int noOfTeamInDatabase = teamResult.getInt(1);
            int teamId = noOfTeamInDatabase + 1;

            System.out.print("Enter your Unique TeamName: ");
            String teamName = sc.nextLine();

            String query = "insert into Teams (teamId, teamName)" + " values (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            stmt.setString(2, teamName);
            int no = stmt.executeUpdate();
            System.out.println(no+" Team inserted");
            stmt.close();
            addPlayerInTeam(sc,conn,teamId);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
