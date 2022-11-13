package com.example.basketballstattracker.nba;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Player
 * class that has all the Player functions below along with variables and arraylists
 */
public class Player {
    // variables below
    private String name;
    private float points;
    private float assists;
    private float rebounds;
    private float steals;
    private float blocks;
    private float turnovers;
    private float FG;
    private float threePT;
    private float FT;

    /**
     * Player
     * Constructor that passes in name, position, and age objects
     *
     * @param name: Player's name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Get player stats
     */
    public String getPlayerStats() {
        String str1 = ("Name: " + name + "\n" + "Points: " + points + "\n" + "Assists: " + assists + "\n" +
                "Rebounds: " + rebounds + "\n" + "Steals: " + steals + "\n" + "Blocks: " + blocks + "\n" +
                "Turnovers: " + turnovers + "\n" + "Field goal %: " + FG + "\n" + "Three point %: " + threePT + "\n" +
                "Free throw %: " + FT);
        return str1;
    }


    /**
     * Output all the player attributes
     */
    public void outputPlayerStats() {
        System.out.println(name);
        System.out.println("Points: " + points);
        System.out.println("Assists: " + assists);
        System.out.println("Rebounds: " + rebounds);
        System.out.println("Steals: " + steals);
        System.out.println("Blocks: " + blocks);
        System.out.println("Turnovers: " + turnovers);
        System.out.println("FG%: " + FG);
        System.out.println("ThreePT%: " + threePT);
        System.out.println("FT%: " + FT);
    }

    /**
     * setName function
     *
     * @param playerName: playerName
     */
    public void setName(String playerName) {
        name = playerName;
    }

    /**
     * getName function
     *
     * @return returns player name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * setPoints function
     *
     * @param playerPoints: playerPoints
     */
    public void setPoints(float playerPoints) {
        points = playerPoints;
    }

    /**
     * getPoints function
     *
     * @return Returns player points as a float
     */
    public float getPoints() {
        return points;
    }

    /**
     * setAssists function
     *
     * @param playerAssists: playerAssists
     */
    public void setAssists(float playerAssists) {
        assists = playerAssists;
    }

    /**
     * getAssists function
     *
     * @return Return player's assists as a float
     */
    public float getAssists() {
        return assists;
    }

    /**
     * setRebounds function
     *
     * @param playerRebounds: playerRebounds
     */
    public void setRebounds(float playerRebounds) {
        rebounds = playerRebounds;
    }

    /**
     * getRebounds function
     *
     * @return Return player's rebounds as a float
     */
    public float getRebounds() {
        return rebounds;
    }

    /**
     * setSteals function
     *
     * @param playerSteals: playerSteals
     */
    public void setSteals(float playerSteals) {
        steals = playerSteals;
    }

    /**
     * getSteals function
     *
     * @return Returns player steals as a float
     */
    public float getSteals() {
        return steals;
    }

    /**
     * setBlocks function
     *
     * @param playerBlocks: playerBlocks
     */
    public void setBlocks(float playerBlocks) {
        blocks = playerBlocks;
    }

    /**
     * getBlocks function
     *
     * @return Returns player blocks as a float
     */
    public float getBlocks() {
        return blocks;
    }

    /**
     * setTurnovers function
     *
     * @param playerTurnovers: playerTurnovers
     */
    public void setTurnovers(float playerTurnovers) {
        turnovers = playerTurnovers;
    }

    /**
     * getTurnovers function
     *
     * @return Returns playerTurnovers as a float
     */
    public float getTurnovers() {
        return turnovers;
    }

    /**
     * setFG function
     *
     * @param playerFG: playerFG
     */
    public void setFG(float playerFG) {
        FG = playerFG;
    }

    /**
     * getFG function
     *
     * @return Return player FG as a float
     */
    public float getFG() {
        return FG;
    }

    /**
     * setThreePT function
     *
     * @param playerThreePT: playerThreePT
     */
    public void setThreePT(float playerThreePT) {
        threePT = playerThreePT;
    }

    /**
     * getThreePT function
     *
     * @return Returns player threePT as a float
     */
    public float getThreePT() {
        return threePT;
    }

    /**
     * setFT function
     *
     * @param playerFT: playerFT
     */
    public void setFT(float playerFT) {
        FT = playerFT;
    }

    /**
     * getFT function
     *
     * @return Returns player FT as a float
     */
    public float getFT() {
        return FT;
    }
}
