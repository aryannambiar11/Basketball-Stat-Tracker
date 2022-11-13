package com.example.basketballstattracker.nba;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Team
 * class that has all the team functions below along with variables and arraylists
 */
public class Team {
    private static final int DEC2PER = 100;
    private String name;
    private int wins;
    private int losses;
    private ArrayList<Player> playerList = new ArrayList<>();

    /**
     * method with name object being passed in it
     *
     * @param name: name of the team
     */
    public Team(String name) {
        this.name = name;
    }

    /**
     * adding a player object to a playerList
     *
     * @param player: the player object
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    /**
     * Arraylist of playerList
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    // set and get for Name, Wins, Losses, and WinRate below
    public String getName() {
        return name;
    }

    public void setWins(int TeamWins) {
        wins = TeamWins;
    }

    public int getWins() {
        return wins;
    }

    public void setLosses(int TeamLosses) {
        losses = TeamLosses;
    }

    public int getLosses() {
        return losses;
    }

    public float getWinRate() {
        if (wins == 0 && losses == 0) {
            return (float) 00.0;
        }
        BigDecimal bd = new BigDecimal(Float.toString(DEC2PER * (float) wins / (wins + losses)));
        bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * getTeamRecord
     * Function gets Team record from user input and returns it as a string
     */
    public String getTeamRecord() {
        String str = ("The " + getName() + "'s record is: " + "\n" + "Wins: " + getWins() + "\n" + "Losses: " + getLosses() + "\n" + "WinRate(Percentage): " + String.format("%.1f", getWinRate()) + "%");
        return str;
    }

    /**
     * outputTeamRecord
     * Functions gets a team from the user and outputs that team's wins, losses, and winrate
     */
    public void outputTeamRecord() {
        System.out.println("The " + getName() + "'s record is: ");
        System.out.println("Wins: " + getWins());
        System.out.println("Losses: " + getLosses());
        System.out.println("WinRate(Percentage): " + String.format("%.1f", getWinRate()) + "%");
    }

    /**
     * outputTeamPlayers
     * Function gets a team from the user and outputs all the players that are on that team
     */
    public void outputTeamPlayers() {
        System.out.println("The team's players are: ");
        ArrayList<String> sPlayerList = new ArrayList<>();
        for (Player player : getPlayerList()) {
            sPlayerList.add(player.getName());
        }
        System.out.println(sPlayerList);
    }
}
