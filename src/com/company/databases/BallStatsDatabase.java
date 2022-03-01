package com.company.databases;

import com.company.bean.BallWiseScoreBoard;
import com.company.bean.BallStats;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BallStatsDatabase {
    public static void insertBallStats(BallWiseScoreBoard ballWiseScoreBoard, Connection conn)
    {
        List<List<BallStats>> inningsPerBallDetails = new ArrayList<>();
        inningsPerBallDetails.add(ballWiseScoreBoard.getFirstInningBallStats());
        inningsPerBallDetails.add(ballWiseScoreBoard.getSecondInningBallStats());

        try {
            for (List<BallStats> inningsPerBallDetail : inningsPerBallDetails) {
                for (BallStats perBallDetail : inningsPerBallDetail) {
                    String query = "insert into BallStats (matchId, teamId, overNumber, ballNumberInOver, batsmanId, bowlerId, ballOutcome, teamScore, teamWickets)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, ballWiseScoreBoard.getMatchId());
                    stmt.setInt(2,perBallDetail.getTeamId());
                    stmt.setInt(3,perBallDetail.getOverNumber());
                    stmt.setInt(4,perBallDetail.getBallNumberInOver());
                    stmt.setInt(5,perBallDetail.getBatsman().getPlayerId());
                    stmt.setInt(6,perBallDetail.getBowler().getPlayerId());
                    stmt.setString(7, String.valueOf(perBallDetail.getBallOutcome()));
                    stmt.setInt(8,perBallDetail.getTeamScore());
                    stmt.setInt(9,perBallDetail.getTeamWickets());
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
