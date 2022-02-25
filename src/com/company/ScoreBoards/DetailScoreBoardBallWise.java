package com.company.ScoreBoards;
import java.util.ArrayList;
import java.util.List;

public class DetailScoreBoardBallWise {
    private final int matchId;
    private final int scoreBoardId;
    private final List<PerBallDetails> firstInningEveryBallDetails = new ArrayList<>();
    private final List<PerBallDetails> secondInningEveryBallDetails = new ArrayList<>();

    public List<PerBallDetails> getFirstInningEveryBallDetails() {
        return firstInningEveryBallDetails;
    }
    public List<PerBallDetails> getSecondInningEveryBallDetails() {
        return secondInningEveryBallDetails;
    }

    public void updateDetailsScoreBoardBallWise(PerBallDetails ballDetails, int inning_no) {
        if(inning_no == 1)firstInningEveryBallDetails.add(ballDetails);
        else secondInningEveryBallDetails.add(ballDetails);
    }

    public DetailScoreBoardBallWise(int matchId, int scoreBoardId) {
        this.matchId = matchId;
        this.scoreBoardId = scoreBoardId;
    }
}
