package com.company.databases;

import com.company.bean.Team;
import java.sql.*;
import java.util.List;

public class InningStatsDatabase {
    public static void insertInningsStats(List<Team> teamList, int matchId, Connection conn)
    {
        try {
            for(Team team : teamList)
            {
                String query = "insert into InningStats (teamId, matchId, runScore, ballsFaced, dotBallsPlayed, oneRunsScored, twoRunsScored, threeRunsScored, fourRunsScored, sixRunsScored, wickets, wideRuns, noBallRuns)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1,team.getTeamId());
                stmt.setInt(2,matchId);
                stmt.setInt(3,team.getRunScore());
                stmt.setInt(4,team.getBallsFaced());
                stmt.setInt(5,team.getDotBallsPlayed());
                stmt.setInt(6,team.getOneRunsScored());
                stmt.setInt(7,team.getTwoRunsScored());
                stmt.setInt(8,team.getThreeRunsScored());
                stmt.setInt(9,team.getFourRunsScored());
                stmt.setInt(10,team.getSixRunsScored());
                stmt.setInt(11,team.getWickets());
                stmt.setInt(12,team.getWideRuns());
                stmt.setInt(13,team.getNoBallRuns());
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
