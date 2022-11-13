package com.example.basketballstattracker;

import com.example.basketballstattracker.nba.AdvancedStats;
import com.example.basketballstattracker.nba.Conference;
import com.example.basketballstattracker.nba.Player;
import com.example.basketballstattracker.nba.Team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    public static HashMap<String, Conference> conferenceHashMap = new HashMap<>();
    public static HashMap<String, Team> teamHashMap = new HashMap<>();
    public static HashMap<String, Player> playerHashMap = new HashMap<>();
    Conference eastern;

    @BeforeEach
    void setup() {
        eastern = new Conference("Eastern");
        Team bucks = new Team("BUCKS");
        eastern.addTeam(bucks);
        bucks.setWins(45);
        bucks.setLosses(27);
        teamHashMap.put("BUCKS", bucks);
        Player giannis = new Player("GIANNIS ANTETOKOUNMPO");
        playerHashMap.put("GIANNIS ANTETOKOUNMPO", giannis);
        giannis.setPoints((float)29.7);
        giannis.setAssists((float)5.8);
        giannis.setRebounds((float)11.6);
        giannis.setSteals((float)1.1);
        giannis.setBlocks((float)1.4);
        giannis.setTurnovers((float)3.4);
        giannis.setFG((float)55);
        giannis.setThreePT((float)30.2);
        giannis.setFT((float)72.1);
        bucks.addPlayer(giannis);
        conferenceHashMap.put("EASTERN", eastern);
        Team heat = new Team("HEAT");
        teamHashMap.put("HEAT", heat);
        heat.setWins(11);
        heat.setLosses(20);
        eastern.addTeam(heat);
        Team nets = new Team("NETS");
        teamHashMap.put("NETS", nets);
        nets.setWins(38);
        nets.setLosses(35);
        Player kyrie = new Player("KYRIE IRVING");
        playerHashMap.put("KYRIE IRVING", kyrie);
        kyrie.setPoints((float)28.5);
        kyrie.setAssists((float)5.5);
        kyrie.setRebounds((float)4.6);
        kyrie.setSteals((float)1.4);
        kyrie.setBlocks((float)0.7);
        kyrie.setTurnovers((float)2.4);
        kyrie.setFG((float)49.3);
        kyrie.setThreePT((float)43.8);
        kyrie.setFT((float)90.8);
        nets.addPlayer(kyrie);
        Player kevin = new Player("KEVIN DURANT");
        playerHashMap.put("KEVIN DURANT", kevin);
        kevin.setPoints((float)29.9);
        kevin.setAssists((float)6.1);
        kevin.setRebounds((float)7.3);
        kevin.setSteals((float)0.8);
        kevin.setBlocks((float)0.9);
        kevin.setTurnovers((float)3.3);
        kevin.setFG((float)52.3);
        kevin.setThreePT((float)37.5);
        kevin.setFT((float)90.3);
        nets.addPlayer(kevin);
        eastern.addTeam(nets);
    }

    /**
     * Test getWinRate function in Team class for default team wins and losses values (0)
     */
    @Test
    public void team_getWinRate_default() {
        Team team = new Team("Team");
        float winRate = team.getWinRate();
        float expectedWinRate = (float) 00.0;
        assertEquals(expectedWinRate, winRate);
    }

    /**
     * Test getWinRate function in Team class for winRate at 50%
     */
    @Test
    public void team_getWinRate_w10_l10() {
        Team team = new Team("Team");
        team.setWins(10);
        team.setLosses(10);
        float winRate = team.getWinRate();
        float expectedWinRate = (float) 50.0;
        assertEquals(expectedWinRate, winRate);
    }

    /**
     * Test getWinRate function in Team class for winRate above 50%
     */
    @Test
    public void team_getWinRate_w35_l5() {
        Team team = new Team("Team");
        team.setWins(35);
        team.setLosses(5);
        float winRate = team.getWinRate();
        float expectedWinRate = (float) 87.5;
        assertEquals(expectedWinRate, winRate);
    }

    /**
     * Test getWinRate function in Team class for winRate below 50%
     */
    @Test
    public void team_getWinRate_w15_l45() {
        Team team = new Team("Team");
        team.setWins(15);
        team.setLosses(45);
        float winRate = team.getWinRate();
        float expectedWinRate = (float) 25.0;
        assertEquals(expectedWinRate, winRate);
    }

    /**
     * Test getWinRate function in Team class for winRate that rounds up
     */
    @Test
    public void team_getWinRate_w7_l34() {
        Team team = new Team("Team");
        team.setWins(7);
        team.setLosses(34);
        float winRate = team.getWinRate();
        float expectedWinRate = (float) 17.1;
        assertEquals(expectedWinRate, winRate);
    }

    /**
     * Test getWinRate function in Team class for winRate that rounds down
     */
    @Test
    public void team_getWinRate_w7_l37() {
        Team team = new Team("Team");
        team.setWins(7);
        team.setLosses(37);
        float winRate = team.getWinRate();
        float expectedWinRate = (float) 15.9;
        assertEquals(expectedWinRate, winRate);
    }

    /**
     * Test getTeamsOrderedName function in Conference class
     */
    @Test
    public void conference_getTeamsOrderedName() {
        ArrayList<Team> teamsOrdered = eastern.getTeamsOrderedName();
        ArrayList<String> sTeamsOrdered = new ArrayList<>();
        for (Team team : teamsOrdered) {
            sTeamsOrdered.add(team.getName());
        }
        ArrayList<String> expected = new ArrayList<>();
        expected.add("BUCKS");
        expected.add("HEAT");
        expected.add("NETS");
        assertEquals(expected, sTeamsOrdered);
    }
    /**
     * Test getTeamsOrderedName function in Conference class for teams that start with the same letters
     */
    @Test
    public void conference_getTeamsOrderedName_sameLetters() {
        Team nea = new Team("NEA");
        eastern.addTeam(nea);
        ArrayList<Team> teamsOrdered = eastern.getTeamsOrderedName();
        ArrayList<String> sTeamsOrdered = new ArrayList<>();
        for (Team team : teamsOrdered) {
            sTeamsOrdered.add(team.getName());
        }
        ArrayList<String> expected = new ArrayList<>();
        expected.add("BUCKS");
        expected.add("HEAT");
        expected.add("NEA");
        expected.add("NETS");
        assertEquals(expected, sTeamsOrdered);
    }

    /**
     * Test getTeamsOrderedWinRate function in Conference class
     */
    @Test
    public void conference_getTeamsOrderedWinRate() {
        ArrayList<Team> teamsOrdered = eastern.getTeamsOrderedWinRate();
        ArrayList<String> sTeamsOrdered = new ArrayList<>();
        for (Team team : teamsOrdered) {
            sTeamsOrdered.add(team.getName());
        }
        ArrayList<String> expected = new ArrayList<>();
        expected.add("BUCKS");
        expected.add("NETS");
        expected.add("HEAT");
        assertEquals(expected, sTeamsOrdered);
    }

    /**
     * Test getTeamsOrderedWinRate function in Conference class when two teams have the same record (team that was added to the system first is put in first)
     */
    @Test
    public void conference_getTeamsOrderedWinRate_sameRecord() {
        Team bad = new Team("BAD");
        bad.setWins(45);
        bad.setLosses(27);
        eastern.addTeam(bad);
        ArrayList<Team> teamsOrdered = eastern.getTeamsOrderedWinRate();
        ArrayList<String> sTeamsOrdered = new ArrayList<>();
        for (Team team : teamsOrdered) {
            sTeamsOrdered.add(team.getName());
        }
        ArrayList<String> expected = new ArrayList<>();
        expected.add("BUCKS");
        expected.add("BAD");
        expected.add("NETS");
        expected.add("HEAT");
        assertEquals(expected, sTeamsOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for invalid input
     */
    @Test
    public void advancedStats_outputTopPlayerStat_invalid() {
        String stat = "invalid";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "Invalid stat input";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for POINTS
     */
    @Test
    public void advancedStats_outputTopPlayerStat_points() {
        String stat = "POINTS";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "KEVIN DURANT with 29.9\n";
        expected += "GIANNIS ANTETOKOUNMPO with 29.7\n";
        expected += "KYRIE IRVING with 28.5\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for ASSISTS
     */
    @Test
    public void advancedStats_outputTopPlayerStat_assists() {
        String stat = "ASSISTS";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "KEVIN DURANT with 6.1\n";
        expected += "GIANNIS ANTETOKOUNMPO with 5.8\n";
        expected += "KYRIE IRVING with 5.5\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for REBOUNDS
     */
    @Test
    public void advancedStats_outputTopPlayerStat_rebounds() {
        String stat = "REBOUNDS";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "GIANNIS ANTETOKOUNMPO with 11.6\n";
        expected += "KEVIN DURANT with 7.3\n";
        expected += "KYRIE IRVING with 4.6\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for STEALS
     */
    @Test
    public void advancedStats_outputTopPlayerStat_steals() {
        String stat = "STEALS";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "KYRIE IRVING with 1.4\n";
        expected += "GIANNIS ANTETOKOUNMPO with 1.1\n";
        expected += "KEVIN DURANT with 0.8\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for BLOCKS
     */
    @Test
    public void advancedStats_outputTopPlayerStat_blocks() {
        String stat = "BLOCKS";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "GIANNIS ANTETOKOUNMPO with 1.4\n";
        expected += "KEVIN DURANT with 0.9\n";
        expected += "KYRIE IRVING with 0.7\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for TURNOVERS
     */
    @Test
    public void advancedStats_outputTopPlayerStat_turnovers() {
        String stat = "TURNOVERS";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "GIANNIS ANTETOKOUNMPO with 3.4\n";
        expected += "KEVIN DURANT with 3.3\n";
        expected += "KYRIE IRVING with 2.4\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for FG
     */
    @Test
    public void advancedStats_outputTopPlayerStat_FG() {
        String stat = "FG";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "GIANNIS ANTETOKOUNMPO with 55.0\n";
        expected += "KEVIN DURANT with 52.3\n";
        expected += "KYRIE IRVING with 49.3\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for THREEPT
     */
    @Test
    public void advancedStats_outputTopPlayerStat_threePT() {
        String stat = "THREEPT";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "KYRIE IRVING with 43.8\n";
        expected += "KEVIN DURANT with 37.5\n";
        expected += "GIANNIS ANTETOKOUNMPO with 30.2\n";
        assertEquals(expected, sPlayersOrdered);
    }

    /**
     * Test for outputTopPlayerStat function in AdvancedStats class for FT
     */
    @Test
    public void advancedStats_outputTopPlayerStat_FT() {
        String stat = "FT";
        String sPlayersOrdered = AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
        String expected = "";
        expected += "Top Players of " + stat + " are: \n";
        expected += "KYRIE IRVING with 90.8\n";
        expected += "KEVIN DURANT with 90.3\n";
        expected += "GIANNIS ANTETOKOUNMPO with 72.1\n";
        assertEquals(expected, sPlayersOrdered);
    }
}
