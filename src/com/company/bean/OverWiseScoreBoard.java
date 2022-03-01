package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class OverWiseScoreBoard {
    private final int matchId;
    private List<OverStats> firstInningOverStats = new ArrayList<>();
    private List<OverStats> secondInningOverStats = new ArrayList<>();

    public int getMatchId() {
        return matchId;
    }
    public List<OverStats> getFirstInningOverStats() {
        return firstInningOverStats;
    }
    public List<OverStats> getSecondInningOverStats() {
        return secondInningOverStats;
    }

    public void addOverStats(OverStats overStats, int inningNo) {
        if (inningNo == 1) firstInningOverStats.add(overStats);
        else secondInningOverStats.add(overStats);
    }
    public OverWiseScoreBoard(int matchId) {
        this.matchId = matchId;
    }
}
