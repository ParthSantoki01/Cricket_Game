package com.company;

import com.company.bean.Match;
import com.company.bean.Team;
import com.company.repository.*;
import com.company.service.MatchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchControl {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        MatchRepo matchRepo = new MatchRepo();
//        int matchId = matchRepo.getNewMatchId();
//
//        List<Team> teamList = new ArrayList<>();
//        TeamRepo teamRepo = new TeamRepo();
//        if(!teamRepo.getTeams(teamList)) return;
//
//        PlayerRepo playerRepo = new PlayerRepo();
//        if(!playerRepo.setPlayerInTeam(teamList))return;
//
//        OverRepo overRepo = new OverRepo();
//        int newOverId = overRepo.getNoOfOverInDatabase();
//
//        BallRepo ballRepo = new BallRepo();
//        int newBallId = ballRepo.getNoOfBallInDatabase();
//
//        System.out.println("Enter Number Of Over: ");
//        int overInInning = sc.nextInt();
//        Match newMatch = new Match(matchId, overInInning);
//        MatchServiceImpl matchService = new MatchServiceImpl();
//
//        matchService.startGame(newMatch, teamList, newOverId, newBallId);
//        String matchStatus = matchRepo.insertMatchDetails(newMatch);
//        String overStatus = overRepo.insertOversDetails(newMatch.getOverList());
//        String ballStatus = ballRepo.insertBallsDetails(newMatch.getOverList());
    }
}
