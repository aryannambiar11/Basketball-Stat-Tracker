package com.example.basketballstattracker.util;

import com.example.basketballstattracker.MainController;
import com.example.basketballstattracker.nba.Player;
import com.example.basketballstattracker.nba.Team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.basketballstattracker.Main.conferenceHashMap;

public final class Writer {
    /**
     * Function checks if the file inputted exists and creates a new one if it doesn't
     *
     * @param file: File to check
     */
    public static String checkFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                String fString = String.format("Cannot create new team data file %s%n!", file.getAbsoluteFile());
                return fString;
            }
        }
        if (!file.exists() || !file.isFile() || !file.canWrite()) {
            String fString = String.format("The file %s cannot be accessed!%n", file.getAbsoluteFile());
            return fString;
        }
        return "";
    }

    /**
     * Function writes data to the respective team csv file
     *
     * @throws IOException: exception in case the file gives an error
     */
    public static String saveData() throws IOException {
        for (String conference : conferenceHashMap.keySet()) {
            for (Team team : conferenceHashMap.get(conference).getTeams()) {
                String filePath = "src/main/java/com/example/basketballstattracker/data/" + team.getName() + ".csv";
                File file = new File(filePath);
                String message = checkFile(file);
                if (!(message.isEmpty())) {
                    return message;
                }
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(team.getName() + "," + team.getWins() + "," + team.getLosses());
                out.newLine();
                out.write(conferenceHashMap.get(conference).getName());
                out.newLine();
                for (Player player : team.getPlayerList()) {
                    out.write(player.getName() + "," + player.getPoints() + "," + player.getAssists() + "," + player.getRebounds()
                            + "," + player.getSteals() + "," + player.getBlocks() + "," + player.getTurnovers() + "," + player.getFG() + "," + player.getThreePT() + "," + player.getFT());
                    out.newLine();
                }
                out.close();
            }
        }
        return "Data Saved to csv files in src/main/java/com/example/basketballstattracker/data/";
    }
}
