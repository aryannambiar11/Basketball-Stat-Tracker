package com.example.basketballstattracker.nba;

import java.util.*;

/**
 * AdvancedStats
 * class that has all the AdvancedStats functions below along with variables and arraylists
 */
public class AdvancedStats {
    /**
     * outputTopPlayerStat
     * Function outputs the top player of stat given by user
     */
    public static String outputTopPlayerStat(String stat, HashMap<String, Conference> conferenceHashMap) {
        String message = "";
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (String key : conferenceHashMap.keySet()) {
            for (Team team : conferenceHashMap.get(key).getTeams()) {
                allPlayers.addAll(team.getPlayerList());
            }
        }
        message += "Top Players of " + stat + " are: \n";
        switch (stat) {
            case "POINTS" -> {
                allPlayers.sort(new playerComparatorByPoints());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getPoints() + "\n";
                }
            }
            case "ASSISTS" -> {
                allPlayers.sort(new playerComparatorByAssists());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getAssists() + "\n";
                }
            }
            case "REBOUNDS" -> {
                allPlayers.sort(new playerComparatorByRebounds());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getRebounds() + "\n";
                }
            }
            case "STEALS" -> {
                allPlayers.sort(new playerComparatorBySteals());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getSteals() + "\n";
                }
            }
            case "BLOCKS" -> {
                allPlayers.sort(new playerComparatorByBlocks());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getBlocks() + "\n";
                }
            }
            case "TURNOVERS" -> {
                allPlayers.sort(new playerComparatorByTurnovers());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getTurnovers() + "\n";
                }
            }
            case "FG" -> {
                allPlayers.sort(new playerComparatorByFG());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getFG() + "\n";
                }
            }
            case "THREEPT" -> {
                allPlayers.sort(new playerComparatorByThreePT());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getThreePT() + "\n";
                }
            }
            case "FT" -> {
                allPlayers.sort(new playerComparatorByFT());
                for (int i = 0; i < 3; i++) {
                    message += allPlayers.get(i).getName() + " with " + allPlayers.get(i).getFT() + "\n";
                }
            }
            default -> {
                message += "Invalid stat input";
            }
        }
        return message;
    }

    static class playerComparatorByPoints implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getPoints(), o1.getPoints());
        }
    }

    static class playerComparatorByAssists implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getAssists(), o1.getAssists());
        }
    }

    static class playerComparatorByRebounds implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getRebounds(), o1.getRebounds());
        }
    }

    static class playerComparatorBySteals implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getSteals(), o1.getSteals());
        }
    }

    static class playerComparatorByBlocks implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getBlocks(), o1.getBlocks());
        }
    }

    static class playerComparatorByTurnovers implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getTurnovers(), o1.getTurnovers());
        }
    }

    static class playerComparatorByFG implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getFG(), o1.getFG());
        }
    }

    static class playerComparatorByThreePT implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getThreePT(), o1.getThreePT());
        }
    }

    static class playerComparatorByFT implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return Float.compare(o2.getFT(), o1.getFT());
        }
    }
}