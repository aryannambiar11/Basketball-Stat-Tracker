package com.example.basketballstattracker.util;
import com.example.basketballstattracker.nba.Conference;
import com.example.basketballstattracker.nba.Player;
import com.example.basketballstattracker.nba.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.example.basketballstattracker.Main.*;

public final class Reader {
    /**
     * Function loads data from csv file (this version is for the command line input)
     *
     * @param teamFile: csv file to open
     */
    public static String loadData(String teamFile) {
        String message = "";
        // try in case opening file gives an error
        try {
            // create string of the filePath for scanner object
            String filePath = "src/main/java/com/example/basketballstattracker/data/" + teamFile;
            Scanner scanner = new Scanner(new File(filePath));
            String teamData = scanner.nextLine();
            // split data by commas
            ArrayList<String> splitTeamData = new ArrayList<>(Arrays.asList(teamData.split(",")));
            String teamName = splitTeamData.get(0);
            int teamWins = Integer.parseInt(splitTeamData.get(1));
            int teamLosses = Integer.parseInt(splitTeamData.get(2));
            String conferenceName = scanner.nextLine();
            // create conference and team objects from data
            if (!conferenceHashMap.containsKey(conferenceName)) {
                Conference conference = new Conference(conferenceName);
                conferenceHashMap.put(conferenceName, conference);
            }
            if (!conferenceHashMap.containsKey(conferenceName)) {
                message += conferenceName + " conference not in system cannot add team to it\n";
            } else {
                Team team = new Team(teamName);
                // adds team entered to a hashmap to store the data
                teamHashMap.put(teamName, team);
                conferenceHashMap.get(conferenceName).addTeam(team);
            }
            if (!teamHashMap.containsKey(teamName)) {
                message += teamName + " team does not exist, cannot set wins and losses\n";
            } else {
                // add wins to a team with a hashmap
                teamHashMap.get(teamName).setWins(teamWins);
                // add losses to a team with a hashmap
                teamHashMap.get(teamName).setLosses(teamLosses);
            }
            while (scanner.hasNextLine()) {
                String playerData = scanner.nextLine();
                ArrayList<String> splitPlayerData = new ArrayList<>(Arrays.asList(playerData.split(",")));
                // parse all player data
                String playerName = splitPlayerData.get(0).toUpperCase();
                float playerPoints = Float.parseFloat(splitPlayerData.get(1));
                float playerAssists = Float.parseFloat(splitPlayerData.get(2));
                float playerRebounds = Float.parseFloat(splitPlayerData.get(3));
                float playerSteals = Float.parseFloat(splitPlayerData.get(4));
                float playerBlocks = Float.parseFloat(splitPlayerData.get(5));
                float playerTurnovers = Float.parseFloat(splitPlayerData.get(6));
                float playerFG = Float.parseFloat(splitPlayerData.get(7));
                float playerThreePT = Float.parseFloat(splitPlayerData.get(8));
                float playerFT = Float.parseFloat(splitPlayerData.get(9));
                // add player to team
                if (!teamHashMap.containsKey(teamName)) {
                    message += teamName + " team does not exist, cannot add player to team\n";
                } else {
                    Player player = new Player(playerName);
                    // stores player data entered above into a hashmap to store data
                    playerHashMap.put(playerName, player);
                    // adds player to a team hashmap to store that player onto that specific team
                    teamHashMap.get(teamName).addPlayer(player);
                }
                // add stats to player
                playerHashMap.get(playerName).setPoints(playerPoints);
                playerHashMap.get(playerName).setAssists(playerAssists);
                playerHashMap.get(playerName).setRebounds(playerRebounds);
                playerHashMap.get(playerName).setSteals(playerSteals);
                playerHashMap.get(playerName).setBlocks(playerBlocks);
                playerHashMap.get(playerName).setTurnovers(playerTurnovers);
                playerHashMap.get(playerName).setFG(playerFG);
                playerHashMap.get(playerName).setThreePT(playerThreePT);
                playerHashMap.get(playerName).setFT(playerFT);
            }
            scanner.close();
            message += "Data Loaded from src/main/java/com/example/basketballstattracker/data/\n";
            return message;
        } catch (Exception e) {
            message += "Error: File does not exist\n";
            return message;
        }
    }

    /**
     * Function loads all csv files in data folder
     */
    public static String loadData() {
        String message = "";
        // get all files from data folder
        File folder = new File("src/main/java/com/example/basketballstattracker/data");
        File[] listOfCSV = folder.listFiles();
        for (File file : listOfCSV) {
            try {
                Scanner scanner = new Scanner(file);
                String teamData = scanner.nextLine();
                ArrayList<String> splitTeamData = new ArrayList<>(Arrays.asList(teamData.split(",")));
                String teamName = splitTeamData.get(0).toUpperCase();
                int teamWins = Integer.parseInt(splitTeamData.get(1));
                int teamLosses = Integer.parseInt(splitTeamData.get(2));
                String conferenceData = scanner.nextLine().toUpperCase();
                ArrayList<String> splitConferenceData = new ArrayList<>(Arrays.asList(conferenceData.split(",")));
                String conferenceName = splitConferenceData.get(0);
                if (!conferenceHashMap.containsKey(conferenceName)) {
                    Conference conference = new Conference(conferenceName);
                    conferenceHashMap.put(conferenceName, conference);
                }
                if (!conferenceHashMap.containsKey(conferenceName)) {
                    message += conferenceName + " conference not in system cannot add team to it\n";
                } else {
                    Team team = new Team(teamName);
                    teamHashMap.put(teamName, team);
                    conferenceHashMap.get(conferenceName).addTeam(team);
                }
                if (!teamHashMap.containsKey(teamName)) {
                    message += teamName + " team does not exist, cannot set wins and losses\n";
                } else {
                    teamHashMap.get(teamName).setWins(teamWins);
                    teamHashMap.get(teamName).setLosses(teamLosses);
                }
                while (scanner.hasNextLine()) {
                    String playerData = scanner.nextLine();
                    ArrayList<String> splitPlayerData = new ArrayList<>(Arrays.asList(playerData.split(",")));
                    String playerName = splitPlayerData.get(0).toUpperCase();
                    float playerPoints = Float.parseFloat(splitPlayerData.get(1));
                    float playerAssists = Float.parseFloat(splitPlayerData.get(2));
                    float playerRebounds = Float.parseFloat(splitPlayerData.get(3));
                    float playerSteals = Float.parseFloat(splitPlayerData.get(4));
                    float playerBlocks = Float.parseFloat(splitPlayerData.get(5));
                    float playerTurnovers = Float.parseFloat(splitPlayerData.get(6));
                    float playerFG = Float.parseFloat(splitPlayerData.get(7));
                    float playerThreePT = Float.parseFloat(splitPlayerData.get(8));
                    float playerFT = Float.parseFloat(splitPlayerData.get(9));
                    if (!teamHashMap.containsKey(teamName)) {
                        message += teamName + " team does not exist, cannot add player to team\n";
                    } else {
                        Player player = new Player(playerName);
                        playerHashMap.put(playerName, player);
                        teamHashMap.get(teamName).addPlayer(player);
                    }
                    playerHashMap.get(playerName).setPoints(playerPoints);
                    playerHashMap.get(playerName).setAssists(playerAssists);
                    playerHashMap.get(playerName).setRebounds(playerRebounds);
                    playerHashMap.get(playerName).setSteals(playerSteals);
                    playerHashMap.get(playerName).setBlocks(playerBlocks);
                    playerHashMap.get(playerName).setTurnovers(playerTurnovers);
                    playerHashMap.get(playerName).setFG(playerFG);
                    playerHashMap.get(playerName).setThreePT(playerThreePT);
                    playerHashMap.get(playerName).setFT(playerFT);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                message += "Error: file does not exist\n";
                return message;
            }
        }
        message += "Data Loaded from src/main/java/com/example/basketballstattracker/data/\n";
        return message;
    }

}
