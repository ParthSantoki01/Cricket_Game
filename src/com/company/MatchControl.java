package com.company;

import com.company.databases.*;
import com.company.been.CricketMatch;
import com.company.been.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchControl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/CricketMatches";
            conn = DriverManager.getConnection(url, "root", "Parth@01257");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //  If You add New Team In DataBase.
//        CreateNewTeam.makeTeam(sc, conn);

        List<Team> teamList = new ArrayList<>();

        System.out.print("Enter Number of Match Over : ");
        int totalOver = sc.nextInt();

        CricketMatch cricketMatch = NewMatchSetUp.newCricketGame(sc, teamList, totalOver, conn);
        cricketMatch.tossOfMatch(teamList);
        cricketMatch.startGame(teamList,conn);

        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
