package com.company;

import com.company.databases.DAO;
import com.company.bean.CricketMatch;
import com.company.bean.Team;
import com.company.service.CricketGameServiceImpl;

import java.sql.*;
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

        DAO dataAccessObject = new DAO();

        System.out.println("You want to add new Team. Press Y for yes.");
        String check = sc.nextLine();
        if(check.equals("Y"))
        {
            dataAccessObject.createNewTeam(sc, conn);
        }

        List<Team> teamList = new ArrayList<>();
        System.out.print("Enter Number of Match Over : ");
        int totalOver = sc.nextInt();

        CricketMatch cricketMatch = dataAccessObject.createNewMatch(teamList,totalOver,conn);
        CricketGameServiceImpl cricketGameService = new CricketGameServiceImpl();
        cricketGameService.startGame(cricketMatch,teamList);
        dataAccessObject.insertMatchData(cricketMatch,conn);

        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
