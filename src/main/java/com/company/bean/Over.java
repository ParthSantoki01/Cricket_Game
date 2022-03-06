package com.company.bean;

import java.util.ArrayList;
import java.util.List;

public class Over {
    private final int overId;
    private final int matchId;
    private final int battingTeamId;
    private final int overNumber;
    private final Player bowler;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;
    private final List<Ball> ballList = new ArrayList<>();

    public int getOverId() {
        return overId;
    }
    public int getMatchId() {
        return matchId;
    }
    public int getBattingTeamId() {
        return battingTeamId;
    }
    public int getOverNumber() {
        return overNumber;
    }
    public Player getBowler() {
        return bowler;
    }
    public long getCreatedTime() {
        return createdTime;
    }
    public long getModifiedTime() {
        return modifiedTime;
    }
    public boolean isDeleted() {
        return deleted;
    }

    public List<Ball> getBallList() {
        return ballList;
    }

    public Over(int overId, int matchId, int battingTeamId, int overNumber, Player bowler, Long createdTime, Long modifiedTime, Boolean deleted)
    {
        this.overId = overId;
        this.matchId = matchId;
        this.battingTeamId = battingTeamId;
        this.overNumber = overNumber;
        this.bowler = bowler;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
    }
}
