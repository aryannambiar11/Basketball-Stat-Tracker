package com.example.basketballstattracker;

import com.example.basketballstattracker.nba.*;
import com.example.basketballstattracker.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static javafx.application.Application.launch;

/**
 * Course: CPSC 233 W22
 * Name: Justin Chu & Aryan Nambiar
 * Date: March 24, 2022
 * Tutorial: T04 - Amir
 * Basketball Stat Tracker Semester Project Demo 2
 */

/**
 * Main
 * class that runs the main tracking program and has a menu to choose options from to start the tracker
 */
public class Main extends Application {
    // Global variables of type HashMap so that all functions can access the data
    public static HashMap<String, Conference> conferenceHashMap = new HashMap<>();
    public static HashMap<String, Team> teamHashMap = new HashMap<>();
    public static HashMap<String, Player> playerHashMap = new HashMap<>();

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        List args = getParameters().getRaw();
        if (args.size() > 0) {
            for (Object argument : args) {
                // load data from reader function
                Reader.loadData(argument.toString());
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        //Students edit here to set up the scene
        stage.setTitle("Basketball Stat Tracking Program");
        stage.setScene(scene);
        stage.show();
    }
}

