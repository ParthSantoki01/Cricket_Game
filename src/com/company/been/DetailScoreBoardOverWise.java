package com.company.been;

import java.util.ArrayList;
import java.util.List;

public class DetailScoreBoardOverWise {
    private final int matchId;
    private final int scoreBoardId;
    private List<PerOverDetails> firstInningOvers = new ArrayList<>();
    private List<PerOverDetails> secondInningOvers = new ArrayList<>();

    public List<PerOverDetails> getFirstInningOvers() {
        return firstInningOvers;
    }
    public List<PerOverDetails> getSecondInningOvers() {
        return secondInningOvers;
    }

    public void updateDetailsScoreBoardOverWise(PerOverDetails overDetails, int inningNo) {
        if (inningNo == 1) firstInningOvers.add(overDetails);
        else secondInningOvers.add(overDetails);
    }

    public DetailScoreBoardOverWise(int matchId, int scoreBoardId) {
        this.matchId = matchId;
        this.scoreBoardId = scoreBoardId;
    }
}
