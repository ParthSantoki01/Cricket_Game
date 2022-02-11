package com.company;

public class Player {
    int player_Id;
    int jerseyNumber;
    String name;
    String type;
    String outBy = "Did not Bat";
    int run = 0;
    int wickets = 0;
    int playedBall = 0;
    float playerOver = 0;

    public Player (String Name, String Type, int no, int id)
    {
        name = Name;
        type = Type;
        jerseyNumber = no;
        player_Id = id;
    }
}
