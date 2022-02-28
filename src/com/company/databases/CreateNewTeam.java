package com.company.databases;

import com.company.enums.PlayerRole;

import java.sql.*;
import java.util.Scanner;

public class CreateNewTeam {
    public static void makeTeam(Scanner sc, Connection conn) {
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

            String noOfPlayerQuery = "select count(*) from Players";
            Statement playerStmt = conn.createStatement();
            ResultSet playerResult = playerStmt.executeQuery(noOfPlayerQuery);
            playerResult.next();
            int noOfPlayerInDatabase = playerResult.getInt(1);
            int startPlayerId = noOfPlayerInDatabase + 1;
            for (int i = 0; i < 11; i++)
            {
                int playerId = startPlayerId + i;

                System.out.print("Enter Player " + (i+1) + " Player Name: ");
                String PlayerName = sc.nextLine();

                int battingOrder = i;

                PlayerRole playerRole;
                if(i < 5) playerRole = PlayerRole.Batsman;
                else if(i == 5)playerRole = PlayerRole.Wicketkeeper;
                else playerRole = PlayerRole.Bowler;

                System.out.print("Enter Player " + (i+1) + " jersey Number : ");
                int jerseyNumber = Integer.parseInt(sc.nextLine());

                query = "insert into Players (playerId, playerName, battingOrder, playerRole, jerseyNumber, teamId)" + " values (?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, playerId);
                stmt.setString(2, PlayerName);
                stmt.setInt(3, battingOrder);
                stmt.setString(4, String.valueOf(playerRole));
                stmt.setInt(5, jerseyNumber);
                stmt.setInt(6, teamId);

                int x = stmt.executeUpdate();
                System.out.println(x + " player details inserted into database");
            }
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
