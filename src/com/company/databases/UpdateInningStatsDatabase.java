package com.company.databases;

import com.company.been.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateInningStatsDatabase {
    public static void enterInningsStats(List<Team> teamList, int matchId, Connection conn)
    {
        try {
            for(Team team : teamList)
            {
                String query = "insert into InningStats (teamId, matchId, runScore, ballsFaced, wickets, wideRuns, noBallRuns)" + " values (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1,team.getTeamId());
                stmt.setInt(2,matchId);
                stmt.setInt(3,team.getRunScore());
                stmt.setInt(4,team.getBallsFaced());
                stmt.setInt(5,team.getWickets());
                stmt.setInt(6,team.getWideRuns());
                stmt.setInt(7,team.getNoBallRuns());
                stmt.executeUpdate();
                stmt.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
