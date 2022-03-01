package com.company.databases;

import com.company.bean.OverWiseScoreBoard;
import com.company.bean.OverStats;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OverStatsDatabase {
    public static void insertOversStats(OverWiseScoreBoard overWiseScoreBoard, Connection conn)
    {
        List<List<OverStats>> inningsPerOverDetails = new ArrayList<>();
        inningsPerOverDetails.add(overWiseScoreBoard.getFirstInningOverStats());
        inningsPerOverDetails.add(overWiseScoreBoard.getSecondInningOverStats());

        try {
            for (List<OverStats> inningsPerOverDetail : inningsPerOverDetails) {
                for (OverStats perOverDetail : inningsPerOverDetail) {
                    String query = "insert into OverStats (matchId, teamId, overNumber, bowlerId, runs, playedDotBalls, oneRunsScored, twoRunsScored, threeRunsScored, fourRunsScored, sixRunsScored, wideBalls, noBalls, wickets)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1,overWiseScoreBoard.getMatchId());
                    stmt.setInt(2,perOverDetail.getTeamId());
                    stmt.setInt(3,perOverDetail.getOverNumber());
                    stmt.setInt(4,perOverDetail.getBowlerId());
                    stmt.setInt(5,perOverDetail.getRuns());
                    stmt.setInt(6,perOverDetail.getPlayedDotBalls());
                    stmt.setInt(7,perOverDetail.getOneRunsScored());
                    stmt.setInt(8,perOverDetail.getTwoRunsScored());
                    stmt.setInt(9,perOverDetail.getThreeRunsScored());
                    stmt.setInt(10,perOverDetail.getFourRunsScored());
                    stmt.setInt(11,perOverDetail.getSixRunsScored());
                    stmt.setInt(12,perOverDetail.getWideBalls());
                    stmt.setInt(13,perOverDetail.getNoBalls());
                    stmt.setInt(14,perOverDetail.getWickets());
                    stmt.executeUpdate();
                    stmt.close();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
