package com.example.basketballstattracker;

import com.example.basketballstattracker.nba.AdvancedStats;
import com.example.basketballstattracker.nba.Conference;
import com.example.basketballstattracker.nba.Player;
import com.example.basketballstattracker.nba.Team;
import com.example.basketballstattracker.util.Reader;
import com.example.basketballstattracker.util.Writer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.basketballstattracker.Main.*;
import static com.example.basketballstattracker.Main.conferenceHashMap;


/**
 * Basketball Statistic Tracking Program in Java, JavaFX
 * Authors: Aryan Nambiar & Justin Chu
 * Version: v1.0
 * Course: CPSC233W22 (Dr.Hudson)
 * Tutorial: T04 (Amirhossein)
 * Date: 04/07/22
 * Assignment: Demo 3 Semester Project
 */

/**
 * Main Controller Class that holds functions for the gui interface in JavaFX
 */
public class MainController {
    @FXML
    private TextField FG;

    @FXML
    private TextField FT;

    @FXML
    private TextField addConference;

    @FXML
    private TextField addPlayer;

    @FXML
    private TextField addTeam;

    @FXML
    private TextField assists;

    @FXML
    private TextField blocks;

    @FXML
    private ChoiceBox<String> conferenceName;

    @FXML
    private ChoiceBox<String> playerName;

    @FXML
    private TextField points;

    @FXML
    private TextField rebounds;

    @FXML
    private TextField steals;

    @FXML
    private ChoiceBox<String> teamName;

    @FXML
    private TextField teamLosses;

    @FXML
    private TextField teamWins;

    @FXML
    private TextField threePT;

    @FXML
    private TextField turnovers;

    @FXML
    private ChoiceBox<String> outputOption;

    @FXML
    private TextArea outputBox;

    @FXML
    private TextArea statusBox;

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * handleSave handles the save operations in the gui and saves info to the writer file
     * @param event: event
     * @throws IOException: In case writer file opening fails
     */
    @FXML
    void handleSave(ActionEvent event) throws IOException {
        //saves user-input into writer file
        String message = Writer.saveData();
        statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
        statusBox.setText(message);
    }

    /**
     * handleLoad handles the load operations in the gui and loads info from the reader file
     * @param event: event
     */
    @FXML
    void handleLoad(ActionEvent event) {
        //loads info from reader file
        String message = Reader.loadData();
        statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
        statusBox.setText(message);
    }

    /**
     * AboutAction creates a pop-up message that gives the details about this project including the authors, version, and what the program does
     * @param event: event
     */
    @FXML
    void AboutAction(ActionEvent event) {
        //sets a message box that pops up and gives an alert with the info provided below
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Message");
        alert.setHeaderText("Message: ");
        alert.setContentText("""
                Author: Aryan Nambiar & Justin Chu
                Email: aryan.nambiar@ucalgary.ca, justin.chu1@ucalgary.ca
                Version: v1.0
                This is a basketball stat tracking program""");
        alert.show();
    }

    /**
     * handleAddConference handles the input into the conference text-field and adds them to a conference hashmap if that hashmap doesn't already contain the key
     * @param event: event
     */
    @FXML
    void handleAddConference(ActionEvent event) {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //converts conference entered to uppercase
        String conferenceName = addConference.getText().toUpperCase();
        //checks if conference entered is already in system or is empty
        if (addConference.getText().isEmpty()) {
            error += "Error: Conference input field empty\n";
        }else if (conferenceHashMap.containsKey(conferenceName)) {
            error += ("Error: Conference already in system\n");
        }
        //adds conference to conference hashmap
        if (error.isEmpty()) {
            Conference newConference = new Conference(conferenceName);
            conferenceHashMap.put(conferenceName, newConference);
            statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
            statusBox.setText("Added " + conferenceName + " conference to system");
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * handleAddTeam handles the input for adding a team text-field to a conference and adds the team to a team hashmap if that hashmap doesn't already contain the key
     * @param event: event
     */
    @FXML
    void handleAddTeam(ActionEvent event) {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //converts conference entered to uppercase
        String teamName = addTeam.getText().toUpperCase();
        //checks if conference selected is empty
        if (conferenceName.getValue() == null) {
            error += "Error: No conference selected to add team to\n";
        }
        //checks if team input is empty or already in the system
        if (addTeam.getText().isEmpty()) {
            error += "Error: Team input field empty\n";
        } else if (teamHashMap.containsKey(teamName)) {
            error += "Error: Team already in system\n";
        }
        //adds team to team hashmap and team to a conference which is added to a conference hashmap
        if (error.isEmpty()) {
            Team newTeam = new Team(teamName);
            teamHashMap.put(teamName, newTeam);
            conferenceHashMap.get(conferenceName.getValue()).addTeam(newTeam);
            statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
            statusBox.setText("Added team " + teamName + " to " + conferenceName.getValue() + " conference");
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * handleAddPlayer handles the input for adding a player text-filed to a team and adds the player to a player hashmap if that hashmap doesn't already contain the key
     * @param event: event
     */
    @FXML
    void handleAddPlayer(ActionEvent event) {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //converts conference entered to uppercase
        String playerName = addPlayer.getText().toUpperCase();
        //checks if a team is selected to add a player to
        if (teamName.getValue() == null) {
            error += "Error: No team selected to add player to\n";
        }
        //checks if player text-field is empty or if player already exists
        if (addPlayer.getText().isEmpty()) {
            error += "Error: Player input field empty\n";
        } else if (playerHashMap.containsKey(playerName)) {
            error += "Error: Player already in system\n";
        }
        //creates a player and adds it to player hashmap which then adds player to a team that adds to a hashmap
        if (error.isEmpty()) {
            Player newPlayer = new Player(playerName);
            // adds team entered to a hashmap to store the data
            playerHashMap.put(playerName, newPlayer);
            teamHashMap.get(teamName.getValue()).addPlayer(newPlayer);
            statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
            statusBox.setText("Added player " + playerName + " to the " + teamName.getValue() + " team");
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * handleChangePlayerStats handles the input for adding player stats from their text-fields and adds the player stats to the player hashmap and saves it
     * @param event: event
     */
    @FXML
    void handleChangePlayerStats(ActionEvent event) {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //checks if player name is null
        if (playerName.getValue() == null) {
            error += "Error: No player selected\n";
        }
        //checks if points is valid floating point number
        if (!isValidFloat(points)) {
            error += "Error: Invalid points input (positive float expected)\n";
        }
        //checks if assists is valid floating point number
        if (!isValidFloat(assists)) {
            error += "Error: Invalid assists input (positive float expected)\n";
        }
        //checks if rebounds is valid floating point number
        if (!isValidFloat(rebounds)) {
            error += "Error: Invalid rebounds input (positive float expected)\n";
        }
        //checks if steals is valid floating point number
        if (!isValidFloat(steals)) {
            error += "Error: Invalid steals input (positive float expected)\n";
        }
        //checks if blocks is valid floating point number
        if (!isValidFloat(blocks))  {
            error += "Error: Invalid blocks input (positive float expected)\n";
        }
        //checks if turnovers is valid floating point number
        if (!isValidFloat(turnovers)) {
            error += "Error: Invalid turnovers input (positive float expected)\n";
        }
        //checks if FG % is valid floating point number
        if (!isValidFloat(FG)) {
            error += "Error: Invalid FG input (positive float expected)\n";
        }
        //checks if threePT % is valid floating point number
        if (!isValidFloat(threePT) ) {
            error += "Error: Invalid threePT input (positive float expected)\n";
        }
        //checks if FT is valid floating point number
        if (!isValidFloat(FT)) {
            error += "Error: Invalid FT input (positive float expected)\n";
        }
        //adds player stats to a player
        if (error.isEmpty()) {
            Player player = playerHashMap.get(playerName.getValue());
            //set's points to a player
            if (!points.getText().isEmpty()) {
                float fPoints = string2Float1D(points.getText());
                player.setPoints(fPoints);
            }
            //set's assists to a player
            if (!assists.getText().isEmpty()) {
                float fAssists = string2Float1D(assists.getText());
                player.setAssists(fAssists);
            }
            //set's rebounds to a player
            if (!rebounds.getText().isEmpty()) {
                float fRebounds = string2Float1D(rebounds.getText());
                player.setRebounds(fRebounds);
            }
            //set's steals to a player
            if (!steals.getText().isEmpty()) {
                float fSteals = string2Float1D(steals.getText());
                player.setSteals(fSteals);
            }
            //set's blocks to a player
            if (!blocks.getText().isEmpty()) {
                float fBlocks = string2Float1D(blocks.getText());
                player.setBlocks(fBlocks);
            }
            //set's turnovers to a player
            if (!turnovers.getText().isEmpty()) {
                float fTurnovers = string2Float1D(turnovers.getText());
                player.setTurnovers(fTurnovers);
            }
            //set's FG % to a player
            if (!FG.getText().isEmpty()) {
                float fFG = string2Float1D(FG.getText());
                player.setFG(fFG);
            }
            //set's FT % to a player
            if (!FT.getText().isEmpty()) {
                float fFT = string2Float1D(FT.getText());
                player.setFT(fFT);
            }
            //set's threePT % to a player
            if (!threePT.getText().isEmpty()) {
                float fThreePT = string2Float1D(threePT.getText());
                player.setThreePT(fThreePT);
            }
            statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
            statusBox.setText("Player: " + playerName.getValue() + "'s stats have been updated");
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * handleChangeTeamRecord handles the input for team wins and losses and adds them to the team hashmap to save the info to a specific team
     * @param event: event
     */
    @FXML
    void handleChangeTeamRecord(ActionEvent event) {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //checks if team is selected to add record
        if (teamName.getValue() == null) {
            error += "Error: No team selected to add record to";
        }
        //checks if team wins input is valid
        if (!isValidInt(teamWins)) {
            error += "Error: Invalid input for team wins (positive integer expected)\n";
        }
        //checks if team losses input is valid
        if (!isValidInt(teamLosses)) {
            error += "Error: Invalid input for team losses (positive integer expected)\n";
        }
        //creates team and sets wins to the team
        if (error.isEmpty()) {
            Team team = teamHashMap.get(teamName.getValue());
            if (!teamWins.getText().isEmpty()) {
                int wins = Integer.parseInt(teamWins.getText());
                team.setWins(wins);
            }
            //creates team and sets losses to the team
            if (!teamLosses.getText().isEmpty()) {
                int losses = Integer.parseInt(teamLosses.getText());
                team.setLosses(losses);
            }
            statusBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
            statusBox.setText("Team: " + teamName.getValue() + "'s record has been updated");
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * handleOutput handles the output to the output box where the user can see either general output of the info they have given the program or
     * to view special output which gives the user a more advanced output to view using the stats they have entered into the program
     * @param event: event
     */
    @FXML
    void handleOutput(ActionEvent event) {
        //empty string that is used later in the function to check if user has entered any info
        String message = "";
        //checks if output option was chosen or not
        if (outputOption.getValue() == null) {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText("Error: No option selected");
        } else {
            //checks if user has chosen to output conferences
            if (outputOption.getValue().equals("Output Conferences")) {
                //checks if conferences is empty
                if (conferenceName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No conference selected");
                    //output's conferences stored from hashmap
                } else {
                    message += "Conferences stored: " + conferenceHashMap.keySet() + "\n";
                }
            }
            //checks if user has chosen to output teams
            if (outputOption.getValue().equals("Output Teams")) {
                //checks if teams is empty
                if (teamName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No team selected");
                    //output's teams stored from hashmap
                } else {
                    message += "Teams stored: " + teamHashMap.keySet() + "\n";
                }
            }
            //checks if user has chosen to output players
            if (outputOption.getValue().equals("Output Players")) {
                //checks if player name is empty
                if (playerName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No player selected");
                    //output's players stored from hashmap
                } else {
                    message += "Players stored: " + playerHashMap.keySet() + "\n";
                }
            }
            //checks if user has chosen to output team stats
            if (outputOption.getValue().equals("Output Team's Stats")) {
                //checks if team stats is empty
                if (teamName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No team selected");
                    //output's team stats from hashmap
                } else {
                    message += teamName.getValue() + "\nTeam Wins: " + teamHashMap.get(teamName.getValue()).getWins() + "\n" + "Team Losses: " + teamHashMap.get(teamName.getValue()).getLosses() + "\n";
                }
            }
            //checks if user has chosen to output player stats
            if (outputOption.getValue().equals("Output Player's Stats")) {
                //checks if player stats is empty
                if (playerName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No player selected");
                    //output's player stats from hashmap
                } else {
                    message += "Player Stats: \n" + playerHashMap.get(playerName.getValue()).getPlayerStats() + "\n";
                }
            }
            //checks if user has chosen to output team record
            if (outputOption.getValue().equals("Output Team Record")) {
                //checks if team record is empty
                if (teamName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No team selected");
                    //output's team record from hashmap
                } else {
                    message += "Team record: " + teamHashMap.get(teamName.getValue()).getTeamRecord() + "\n";
                }
            }
            //checks if user has chosen to output alphabetical order of teams in conference
            if (outputOption.getValue().equals("Alphabetical Order of Teams in Conference")) {
                //checks if conferences are empty
                if (conferenceName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No conference selected");
                    //output's teams in alphabetical order from hashmaps and team ordering
                } else {
                    Conference conference = conferenceHashMap.get(conferenceName.getValue());
                    ArrayList<Team> teamsOrdered = conference.getTeamsOrderedName();
                    message += "Teams in " + conferenceName.getValue() + " conference ordered in alphabetical order:\n";
                    for (Team team : teamsOrdered) {
                        message += team.getName() + "\n";
                    }
                }
            }
            //checks if user has chosen to output conference standings
            if (outputOption.getValue().equals("Conference Standings")) {
                //checks if conference name is empty
                if (conferenceName.getValue() == null) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No conference selected");
                    //output's conference standings from hashmaps
                } else {
                    Conference conference = conferenceHashMap.get(conferenceName.getValue());
                    ArrayList<Team> teamsOrdered = conference.getTeamsOrderedWinRate();
                    message += "Rank\t\tTeam\tWins\t\tLosses\tWinRate\n";
                    int rank = 1;
                    for (Team team : teamsOrdered) {
                        message += (rank + "\t\t" + team.getName() + "\t" + team.getWins() + "\t\t" + team.getLosses() + "\t\t" + String.format("%.1f", team.getWinRate()) + "%\n");
                        rank += 1;
                    }
                }
            }
            //checks if user has chosen to output top player of a stat
            if (outputOption.getValue().equals("Top Player of Stat")) {
                //checks if player hashmap is empty
                if (playerHashMap.isEmpty()) {
                    statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    statusBox.setText("Error: No players in system");
                    //output's top player of each statistic based on the statistic chosen by the user
                } else {
                    String[] stats = new String[]{"Points", "Assists", "Rebounds", "Steals", "Blocks", "Turnovers", "FG", "FT", "ThreePT"};
                    // create a choice dialog
                    ChoiceDialog<String> dialog = new ChoiceDialog(stats[0], stats);
                    dialog.setTitle("Top Player of Stat");
                    dialog.setHeaderText("Confirmation");
                    dialog.setContentText("Select a Stat");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isEmpty()) {
                        statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        statusBox.setText("Error: No stat selected");
                    }
                    String stat = result.get().toUpperCase();
                    message += AdvancedStats.outputTopPlayerStat(stat, conferenceHashMap);
                }
            }
        }
        outputBox.setText(message);
    }

    /**
     * initializeConferences helps start the add conference part of the program by having the user enter conference information and saving that info to a conference hashmap and saves the conference name
     */
    @FXML
    public void initializeConferences() {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //checks if conference hashmap is empty
        if (conferenceHashMap.isEmpty()) {
            error += "Error: No conferences in system\n";
        }
        //adds conference to an arraylist of conferences from the hashmap
        if (error.isEmpty()) {
            ObservableList<String> conferenceChoices = FXCollections.observableArrayList();
            conferenceChoices.addAll(conferenceHashMap.keySet());
            conferenceName.setItems(conferenceChoices);
            //other-wise display that an error has occurred
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * initializeTeams helps start the add teams part of the program by having the user enter team information and saving that info to a team hashmap and saves the team name
     */
    @FXML
    public void initializeTeams() {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //checks if conference name is empty
        if (conferenceName.getValue() == null) {
            error += "Error: No conference selected\n";
            //checks if teams in conference name in conference hashmap is empty
        } else if (conferenceHashMap.get(conferenceName.getValue()).getTeams().isEmpty()) {
            error += "Error: No teams in conference\n";
        }

        //adds team to list of teams in selected conference
        if (error.isEmpty()) {
            ObservableList<String> teamsChoices = FXCollections.observableArrayList();
            for (Team team : conferenceHashMap.get(conferenceName.getValue()).getTeams()) {
                teamsChoices.add(team.getName());
            }
            teamName.setItems(teamsChoices);
            //other-wise display an error has occurred
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
        ObservableList<String> Output = FXCollections.observableArrayList();
        Output.addAll("General Output", "Special Output");
        outputOption.setItems(Output);
    }

    /**
     * initializePlayers helps start the add players part of the program by having the user enter a player name and saving that info to a player hashmap
     */
    @FXML
    public void initializePlayers() {
        //empty string that is used later in the function to check if user has entered any info
        String error = "";
        //checks if team name is empty
        if (teamName.getValue() == null) {
            error += "Error: No team selected\n";
            //checks if there are any players in the team
        } else if (teamHashMap.get(teamName.getValue()).getPlayerList().isEmpty()) {
            error += "Error: No players in team\n";
        }

        //adds player to list of players in a team
        if (error.isEmpty()) {
            ObservableList<String> playersChoices = FXCollections.observableArrayList();
            for (Player player : teamHashMap.get(teamName.getValue()).getPlayerList()) {
                playersChoices.add(player.getName());
            }
            playerName.setItems(playersChoices);
            //other-wise display an error has occurred
        } else {
            statusBox.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            statusBox.setText(error);
        }
    }

    /**
     * initializeOptions helps start the ouput options part of the program by giving the user a choice box that has two methods of output to a view box
     * of the information the user will enter into the program
     */
    @FXML
    public void initializeOptions() {
        //lets the user view all the options that are possible to output entered stats and view them
        ObservableList<String> Output = FXCollections.observableArrayList();
        Output.addAll("Output Conferences", "Output Teams", "Output Players", "Output Team's Stats", "Output Player's Stats", "Output Team Record",
                "Alphabetical Order of Teams in Conference", "Conference Standings", "Top Player of Stat");
        outputOption.setItems(Output);
    }

    /**
     * Check if input can be converted to a float
     * @param input: input from TextField
     * @return boolean value, true for can be converted
     */
    private boolean isValidInt(TextField input) {
        if (input.getText().isEmpty()) {
            return true;
        }
        //returns false in try block if integer is negative
        try {
            int intNum = Integer.parseInt(input.getText());
            if (!isPositive(intNum)) {
                return false;
            }
            //catches exception
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if input can be converted to a float
     * @param input: input from TextField
     * @return boolean value, true for can be converted
     */
    private boolean isValidFloat(TextField input) {
        if (input.getText().isEmpty()) {
            return true;
        }
        //returns false in try block if float is negative
        try {
            float floatNum = Float.parseFloat(input.getText());
            if (!isPositive(floatNum)) {
                return false;
            }
            //catches exception
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if input is positive
     * @return boolean value, true if positive
     */
    private boolean isPositive(float number) {
        //returns a number greater or equal to 0
        return number >= 0;
    }

    private float string2Float1D(String sNumber) {
        //formats string to a floating point number to one decimal and returns it
        NumberFormat formatter = new DecimalFormat("#0.0");
        return Float.parseFloat(formatter.format(Float.parseFloat(sNumber)));
    }
}



