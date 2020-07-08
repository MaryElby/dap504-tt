package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pack.match.tt_gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**the PlayersList class deals with the various lists of players - one is generated in each round -
and the initial and master lists which are generated at the beginning of the tournament.
For the initial tournament and master list, it gets the required number of players from a JSON file that must be present
in the data folder of the program
 */
public class PlayersList {
    private int numberOfPlayers;
    private int numberOfByes;
    //private int totalPlayers;
    public List<Player> playersList = new ArrayList<>();
    /**
     getter for totalPlayers
     @return totalPlayers - the total number of players in this list
     **/
    public int getTotalPlayers() {
        return this.numberOfPlayers+this.numberOfByes;
    }


    /**
     getter for list size
     @return int - the number of elements in this list
     **/
    public int getSize() {
        return this.playersList.size();

    }
    /**
     getter for numberOfByes
     @return numberOfByes - the number of dummy players in this list
     **/
    public int getNumberOfByes() {
        return (this.numberOfByes);
    }
    /**
     getter for numberOfPlayers
     @return int - the number of real players in this list
     **/
    public int getNumberOfPlayers() {

        return (this.numberOfPlayers);
    }


    /**
     constructor
        we don't need to tell the constructor how many elements are in the list
        so we don't need any parameters
     **/

    public PlayersList() {
    }
    /**
     creates the required number of players from JSON file
     @param numberOfPlayers int - the number of real players to add to the list
     @throws FileNotFoundException exception - if the JSON file is not found
     **/
    public void createPlayerList(int numberOfPlayers) throws FileNotFoundException {

        //read JSON and loop through players
        String jsonFile = "data/players.json";
        Gson gson = new Gson();
        FileReader fileReader;
        fileReader = new FileReader(jsonFile);
        JsonReader jsonReader = new JsonReader(fileReader);
        this.numberOfPlayers=numberOfPlayers;
        ReadJson[] data = gson.fromJson(jsonReader, ReadJson[].class);

            //for (int i = 0; i < numberOfPlayers; i++) {
            for (int i = 0; i < numberOfPlayers; i++) {
                ReadJson readJson =  data[i];
                //Add the player to the list
                playersList.add(new Player(readJson.getFirstName(), readJson.getLastName(), i));
            }

    }

    //these are public because they are called from the Round to add players to the list for the next round
    /**
     adds the given player to the list
     @param thePlayer Player - the player to add to the list
     **/
    public void addPlayer(Player thePlayer){
        playersList.add(thePlayer);

    }

    /**
     finds the given player in the master list and sets their round reached to the given round
     @param masterList PlayersList - master list containing all players
     @param theLoser  Player - the player we want to set as having lost
     @param theRound  int - which round did they get knocked out in
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
 @param numberOfPlayers int - how many real players are in the list.  used to make sure the new players have unique IDs
 @param numberOfByes int - how many dummy players are wanted
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
     @param masterList PlayersList - the full list of players
     @param whichPlayer which player (by ID) we are updating
     @param theGamesWon how many games to add to the player's cumulative score.
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
                //System.out.println("added " + theGamesWon +" to the total games won for " + thePlayer.getFirstName() );

            }
        }
    }
    /**
     Writes out a list of players with their round reached, leaves out dummy players if reqd.
     * @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     * @param dummyDisplay boolean - whether we want to show the stats for the dummy players in the tournament
     * @param thePlayers PlayersList - this is the master list we made at the start so contains all of the players
     We could run for a round list if we wanted to summarise the round for some reason
     **/
    public void writePlayersResults(tt_gui theGui, boolean dummyDisplay, PlayersList thePlayers){

        theGui.addReport( "\nTournament Report");
            for (int i=0;i< thePlayers.getSize();i++){
                Player thisPlayer = thePlayers.playersList.get(i);
                if (dummyDisplay || !thisPlayer.isDummy()) {
                    //System.out.println("The player " + i + " - " + thisPlayer.getFirstName() + " " + thisPlayer.getLastName() + " reached round " + thisPlayer.getRoundReached() + " and won "+ thisPlayer.getGamesWon()+" in total");
                    theGui.addReport( thisPlayer.getFirstName() + " " + thisPlayer.getLastName() + " reached round " + thisPlayer.getRoundReached() + " and won "+ thisPlayer.getGamesWon()+" games in total");
                }
            }
    }

    /**
     * writePodium - writes out the top 3 in order.  If there is a tie for 3rd place both players are written out
     * this is called from the tournament so needs to be public as that is in a different package
     * @param theGui tt_gui - handle for the GUI so we can send messages to it to be displayed
     * @param thePlayers PlayersList - list of all the players in the tournament
     * @param numRounds int - the number of rounds that were in the tournament
     */
    public void writePodium(tt_gui theGui, PlayersList thePlayers,int numRounds) {
        int roundReached;
        int topPlayerIndex = -1;
        int secondPlayerIndex = -1;
        int thirdPlayerGames;
        int maxthirdPlayerGames = -1;
        int thirdPlayerIndex = -1;
        int jointthirdPlayerIndex = -1;

        theGui.addReport( "\nThe Podium");

        for (int i = 0; i < thePlayers.getSize(); i++) {
            Player thisPlayer = thePlayers.playersList.get(i);
            if (!thisPlayer.isDummy()) {
                roundReached = thisPlayer.getRoundReached();
                if (roundReached == numRounds + 1) {
                    topPlayerIndex = i;
                    //maxRoundReached = roundReached;
                } else {
                    if (roundReached == numRounds) {
                        secondPlayerIndex = i;
                    } else {
                        if (roundReached == numRounds - 1) {
                            thirdPlayerGames = thisPlayer.getGamesWon();

                            if (thirdPlayerGames > maxthirdPlayerGames) {
                                maxthirdPlayerGames = thirdPlayerGames;
                                thirdPlayerIndex = i;
                            } else {
                                if (thirdPlayerGames == maxthirdPlayerGames) {
                                    //maxthirdPlayerGames = thirdPlayerGames;
                                    jointthirdPlayerIndex = i;
                                }
                            }
                        }
                    }
                }
            }
        }

        Player thePlayer = thePlayers.playersList.get(topPlayerIndex);
        //theGui.addReport("Winning player is " + thePlayers.playersList.get(topPlayerIndex));
        theGui.addReport("The winning player is " + thePlayer.getFirstName() + " " + thePlayer.getLastName() + ". " + " They won " + thePlayer.getGamesWon() + " games in total");
        thePlayer = thePlayers.playersList.get(secondPlayerIndex);
        theGui.addReport("The second place player is " + thePlayer.getFirstName() + " " + thePlayer.getLastName() + ". " + " They won " + thePlayer.getGamesWon() + " games in total");
        thePlayer = thePlayers.playersList.get(thirdPlayerIndex);
        if (jointthirdPlayerIndex > -1) {
            Player jointThird = thePlayers.playersList.get(jointthirdPlayerIndex);
            theGui.addReport("There was a tie for third place between " + thePlayer.getFirstName() + " " + thePlayer.getLastName() + " and " + jointThird.getFirstName() + " " + jointThird.getLastName() + ". \nThey won " + thePlayer.getGamesWon() + " total games each");
        } else {
            theGui.addReport("The third place player is " + thePlayer.getFirstName() + " " + thePlayer.getLastName() + ". " + " They won " + thePlayer.getGamesWon() + " games in total");
        }

    }
}
