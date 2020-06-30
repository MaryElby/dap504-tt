package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
//the PlayersList class deals with the various lists of players - one is generated in each round -
// and the master list which is generated at the beginning
//For the master list, it gets the required number of players from a JSON file that must be present in the data folder of the program
public class PlayersList {
    private int numberOfPlayers;
    private int numberOfByes;
    /**
     setter for numberOfPlayers
     **/
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    /**
     setter for numberOfByes
     **/
    public void setNumberOfByes(int numberOfByes) {
        this.numberOfByes = numberOfByes;
    }

    /**
     getter for list size
     **/
    public int getSize() {
        return this.playersList.size();
    }
    /**
     getter for numberOfByes
     **/
    public int getNumberOfByes() {
        return (this.numberOfByes);
    }
    /**
     getter for numberOfPlayers
     **/
    public int getNumberOfPlayers() {
        return (this.numberOfPlayers);
    }

    public List<Player> playersList = new ArrayList<Player>();
    /**
     constructor
     **/
    public PlayersList(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

    }

    /**
     sets the given player to inactive
     **/
    public void setInactive(int playerToSet){
        this.playersList.get(playerToSet).setActive(false);
    }
    /**
     creates the required number of players from JSON file
     **/
    public void createPlayerList(int numberOfPlayers) throws FileNotFoundException {


        //read JSON and loop through players
        String jsonFile = "data/players.json";
        Gson gson = new Gson();
        FileReader fileReader;
        fileReader = new FileReader(jsonFile);
        JsonReader jsonReader = new JsonReader(fileReader);

        ReadJson[] data = gson.fromJson(jsonReader, ReadJson[].class);

            for (int i = 0; i < numberOfPlayers; i++) {
                ReadJson readJson = data[i];
                //System.out.println(readJson.getFirstName() + " " + readJson.getLastName());
                playersList.add(new Player(readJson.getFirstName(), readJson.getLastName(), i));
            }
//            for (Player thePlayer : playersList) {
//                System.out.println("first name (in_list) = " + thePlayer.firstName);
//            }
    }
    //these are public because they are called from the Round to add players to the list for the next round
    /**
     adds the given player to the list
     **/
    public void addPlayer(Player thePlayer){
        playersList.add(thePlayer);

    }

    /**
     finds the given player in the master list and sets their round reached to the given round
     **/
    public void SetLoser(PlayersList masterList, Player theLoser, int theRound) {
        Player thePlayer;

        //find the player in the master list by their ID and set their round reached.
        for (int i=0;i< masterList.getSize();i++             )
        {
            thePlayer = masterList.playersList.get(i);
            if (thePlayer.getPlayerID() == theLoser.getPlayerID()) {
                //we have found the player in the master list
                thePlayer.setRoundReached(theRound);
                //System.out.println("set round reached for " + thePlayer.getFirstName() + " to " + theRound);

            }
        }
    }

/**
    adds the given number of dummy players required to the list
 **/
    public void addByes(int numberOfPlayers,int numberOfByes){
        this.numberOfByes = numberOfByes;
        for (int i = 0; i < numberOfByes; i++) {

            //add in a dummy player to the list
            //uses overloaded constructor (Polymorphism)
            playersList.add(new Player(numberOfPlayers+i));
        }
    }

    /**
     finds the given player in the master list and adds the games won to their total
     **/
    public void AddGamesWon(PlayersList masterList, Player whichPlayer, int theGamesWon) {
        Player thePlayer;

        //find the player in the master list by their ID and set their round reached.
        for (int i=0;i< masterList.getSize();i++             )
        {
            thePlayer = masterList.playersList.get(i);
            if (thePlayer.getPlayerID() == whichPlayer.getPlayerID()) {
                //we have found the player in the master list
                thePlayer.setGamesWon(theGamesWon);
                System.out.println("added " + theGamesWon +" to the total games won for " + thePlayer.getFirstName() );

            }
        }
    }
    /**
     writes a list of players with their round reached, leaves out dummy players if reqd
     **/
    public void writePlayersResults(int dummyDisplay, PlayersList thePlayers){
            for (int i=0;i< thePlayers.getSize();i++){
                Player thisPlayer = thePlayers.playersList.get(i);
                if (dummyDisplay==0 && !thisPlayer.isDummy()) {
                    System.out.println("The player " + i + " - " + thisPlayer.getFirstName() + " " + thisPlayer.getLastName() + " reached round " + thisPlayer.getRoundReached() + " and won "+ thisPlayer.getGamesWon()+" in total");
                }
            }
    }

}
