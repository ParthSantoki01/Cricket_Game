package com.company;
public class PrintingClass {

    public void printWicketCommentary(Team battingTeam, Player strikerBatsman)
    {
        System.out.println("Wicket gone!");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(strikerBatsman.getName() + "\t" + strikerBatsman.getRole() + "\nRun - " + strikerBatsman.getRunScore() + "(" + strikerBatsman.getBallsFaced() +")");
        System.out.println("Team Score - " + battingTeam.getRunScore() + " \t Wickets - " + battingTeam.getWickets());
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void printTeam(Team team)
    {
        System.out.println("Team - " + team.getName());
        System.out.println("\nTeam Players Details");
        for(byte i = 0; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            System.out.print(player.getName() + " \t\t ");
            System.out.print(player.getRole() + " ");
            System.out.println("\t\tJersey number " + player.getJerseyNumber() + " ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------\n");
    }

    public static void printScoreBoard(Team team)
    {
        System.out.println("\n----------------------------------");
        System.out.println("Team :- " + team.getName());
        System.out.println("----------------------------------");
        System.out.println("Summary");
        System.out.println("Runs : \t\t" + team.getRunScore());
        System.out.println("Over: \t\t" + team.getPlayedOvers());
        System.out.println("Wickets:\t" + team.getWickets());
        System.out.println("Wide: \t\t" + team.getWideRuns());
        System.out.println("No Ball:\t" + team.getNoBalls());
        System.out.println("-----------------\n");
        System.out.println("Score Board");
        System.out.println("-----------------");
        System.out.println("Batting Performance");
        for(byte i = 0; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            float strikeRate = 0;
            if(player.getBallsFaced() != 0)   strikeRate = ((float) player.getRunScore() / (float) player.getBallsFaced()) * 100;

            System.out.print(player.getName() + " \t Run -> " + player.getRunScore() + "\t Ball -> " + player.getBallsFaced() + "\t Strike Rate -> " + String.format("%.2f", strikeRate));

            System.out.println("\t Batting Status -> " + ((player.getBallsFaced() == 0)?"Did Not Bat":(player.isBatsmanDismissal()?"Out":"Not Out")));
        }

        System.out.println("\nBowling Performance");
        for(byte i = 6; i < 11; i++)
        {
            Player player = team.getPlayerList().get(i);
            float economyRate = 0;
            if(player.getWicketsTaken() != 0)
            {
                economyRate = (float) ((player.getBallsDelivered())  / player.getWicketsTaken());
            }
            System.out.println(player.getName() + " \t Wickets -> " + player.getWicketsTaken() + "\t over -> " + player.getOversDelivered() + " \t Economy Rate -> " + String.format("%.2f", economyRate) + " \t Given Run -> " + player.getRunsGiven());
        }
    }

    public static void printRunCommentary(Team battingTeam,int run)
    {
        System.out.println(run + "\t Total Run - " + battingTeam.getRunScore());
    }
}
