package com.example.basketballstattracker.nba;

import java.util.*;

/**
 * Conference
 * class that has all the conference functions below along with variables and arraylists
 */
public class Conference {
    private String name;
    private ArrayList<Team> teams = new ArrayList<>();

    /**
     * Constructor for creating conference
     *
     * @param name: conferenceName
     */
    public Conference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Function orders teams by name
     *
     * @return A sorted ArrayList of teams by name
     */
    public ArrayList<Team> getTeamsOrderedName() {
        teams.sort(new teamComparatorByName());
        return teams;
    }

    /**
     * Function orders teams by winRate
     *
     * @return A sorted ArrayList of teams by WinRate
     */
    public ArrayList<Team> getTeamsOrderedWinRate() {
        teams.sort(new teamComparatorByWinRate());
        return teams;
    }

    /**
     * Function outputs the teams of a conference in alphabetical order
     * @return
     */
    public void outputTeamsOrderedName() {
        ArrayList<Team> orderedList = getTeamsOrderedName();
        ArrayList<String> orderedStringList = new ArrayList<>();
        for (Team team : orderedList) {
            orderedStringList.add(team.getName());
        }
        System.out.println("List of conference teams in alphabetical order: " + orderedStringList);
    }

    /**
     * outputConferenceStandings
     * Functions calculates the teams standings by winrate and prints it out
     */
    public void outputConferenceStandings() {
        // ArrayList with teams ordered by winRate
        ArrayList<Team> orderedList = getTeamsOrderedWinRate();
        System.out.println("Rank\tTeam\tWins\tLosses\tWinRate");
        int rank = 1;
        for (Team team : orderedList) {
            System.out.println(rank + "\t\t" + team.getName() + "\t" + team.getWins() + "\t\t" + team.getLosses() + "\t\t" + String.format("%.1f", team.getWinRate()) + "%");
            rank += 1;
        }
    }

    static class teamComparatorByName implements Comparator<Team> {
        @Override
        public int compare(Team o1, Team o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    static class teamComparatorByWinRate implements Comparator<Team> {
        @Override
        public int compare(Team o1, Team o2) {
            return (int) (Math.ceil(o2.getWinRate() - o1.getWinRate()));
        }
    }
}