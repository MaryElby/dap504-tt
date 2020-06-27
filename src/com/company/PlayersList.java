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

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setNumberOfByes(int numberOfByes) {
        this.numberOfByes = numberOfByes;
    }



    public List<Player> playersList = new ArrayList<Player>();
    public PlayersList(int numberOfRounds) {
        this.numberOfPlayers = numberOfRounds;

    }

    public void removePlayer(int playerToGo){        this.playersList.remove(playerToGo);}
    public void setInactive(int playerToSet){
        this.playersList.get(playerToSet).setActive(false);
    }

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
    public void addPlayer(Player thePlayer){
        playersList.add(thePlayer);

    }
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

    public int getSize() {
        return this.playersList.size();
    }

    //adds the number of dummy players required
    public void addByes(int numberOfPlayers,int numberOfByes){
        for (int i = 0; i < numberOfByes; i++) {

            //add in a dummy player to the list
            //uses overloaded constructor (Polymorphism)
            playersList.add(new Player(numberOfPlayers+i));
        }
    }

    public void writePlayersResults(PlayersList thePlayers) {
        for (int i = 0; i < thePlayers.getSize(); i++) {
            Player thisPlayer = thePlayers.playersList.get(i);
            System.out.println("The player " + i + " - " + thisPlayer.getFirstName() + " " + thisPlayer.getLastName() + " reached round " + thisPlayer.getRoundReached());

        }
    }

    public void writePlayersResults(int dummyDisplay, PlayersList thePlayers){
            for (int i=0;i< thePlayers.getSize();i++){
                Player thisPlayer = thePlayers.playersList.get(i);
                if (dummyDisplay==0 && !thisPlayer.isDummy()) {
                    System.out.println("The player " + i + " - " + thisPlayer.getFirstName() + " " + thisPlayer.getLastName() + " reached round " + thisPlayer.getRoundReached());
                }
            }
    }

    public int getNumberOfByes() {
        return (this.numberOfByes);
    }

    public int getNumberOfPlayers() {
        return (this.numberOfPlayers);
    }
}
