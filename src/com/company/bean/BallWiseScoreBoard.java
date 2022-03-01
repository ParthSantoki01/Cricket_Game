package com.company.bean;
import java.util.ArrayList;
import java.util.List;

public class BallWiseScoreBoard {
    private final int matchId;
    private final List<BallStats> firstInningBallStats = new ArrayList<>();
    private final List<BallStats> secondInningBallStats = new ArrayList<>();

    public int getMatchId() {
        return matchId;
    }
    public List<BallStats> getFirstInningBallStats() {
        return firstInningBallStats;
    }
    public List<BallStats> getSecondInningBallStats() {
        return secondInningBallStats;
    }

    public void addBallStats(BallStats ballStats, int inningNo) {
        if(inningNo == 1) firstInningBallStats.add(ballStats);
        else secondInningBallStats.add(ballStats);
    }

    public BallWiseScoreBoard(int matchId) {
        this.matchId = matchId;
    }
}
