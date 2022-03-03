package com.company.databases;

import com.company.bean.CricketMatch;
import com.company.bean.Team;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class DAO {
    public void createNewTeam(Scanner sc, Connection conn)
    {
        CreateNewTeam newTeam = new CreateNewTeam();
        newTeam.makeTeam(sc,conn);
    }
    public void insertMatchData(CricketMatch cricketMatch,Connection conn)
    {
        MatchDatabase matchDatabase = new MatchDatabase();
        matchDatabase.insertMatchDetails(cricketMatch.getScoreBoard(),conn);

        OverDatabase overDatabase = new OverDatabase();
        overDatabase.insertOversDetails(cricketMatch.getScoreBoard().getOverList(),conn);
    }
    public CricketMatch createNewMatch(List<Team> teamList, int totalOver, Connection conn)
    {
        CreateNewMatch match = new CreateNewMatch();
        return match.newCricketGame(teamList,totalOver,conn);
    }
}
