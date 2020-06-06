package com.company;

import java.util.ArrayList;
import java.util.List;

public class PlayersList {
    int numberOfPlayers;

    List<Player> playersList = new ArrayList<Player>();
    public PlayersList(int numberOfRounds) {
        this.numberOfPlayers = numberOfRounds;

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
public void removePlayer(int playerToGo){
        this.playersList.remove(playerToGo);
}
    public void setInactive(int playerToSet){
        this.playersList.get(playerToSet).setActive(false);
    }
    public void createListOfPlayers(){

        for (int i = 0; i < this.numberOfPlayers; i++) {
            playersList.add( new Player("Bob","Smith"+i,0));
        }
//        for (int i=0;i< this.playersList.size();i++){
//            System.out.println("The player "+ i+" is " + playersList.get(i).firstName + " " +  playersList.get(i).lastName+ " " + playersList.get(i).hashCode());
//
//        }
//        Player theWinner = playersList.get(4);

//        System.out.println("we will keep " + playersList.indexOf(theWinner));
//
//        int playerToRemove = playersList.indexOf(theWinner);
//        System.out.println("removing " + playerToRemove);
//
//        playersList.remove(playerToRemove);
//
//        for (int i=0;i< this.playersList.size();i++){
//            System.out.println("The player "+ i+" is " + playersList.get(i).firstName + " " +  playersList.get(i).lastName + " " + playersList.get(i).hashCode());
//
//        }
    }

    public void addPlayer(Player thePlayer){
        playersList.add(thePlayer);

    }

    public int getSize() {
        return this.playersList.size();
    }
}
