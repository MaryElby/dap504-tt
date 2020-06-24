package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PlayersList {
    int numberOfPlayers;

    public List<Player> playersList = new ArrayList<Player>();
    public PlayersList(int numberOfRounds) {
        this.numberOfPlayers = numberOfRounds;

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
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
    public void addPlayer(Player thePlayer){
        playersList.add(thePlayer);

    }

    public int getSize() {
        return this.playersList.size();
    }

    public void addByes(int numberOfPlayers,int numberOfByes){
        for (int i = 0; i < numberOfByes; i++) {

            //add in a dummy player to the list
            playersList.add(new Player(numberOfPlayers+i));
        }
    }

    public void writePlayersResults(PlayersList thePlayers){
            for (int i=0;i< thePlayers.getSize();i++){
                Player thisPlayer = thePlayers.playersList.get(i);
                System.out.println("The player "+ i+" - " + thisPlayer.firstName + " " + thisPlayer.lastName + " reached round "+ thisPlayer.roundReached);

            }
    }


    public void SetLoser(PlayersList masterList, Player theLoser, int theRound) {
        Player thePlayer;


        for (int i=0;i< masterList.getSize();i++             )
        {
            thePlayer = masterList.playersList.get(i);
            if (thePlayer.playerID == theLoser.playerID) {
            //we have found the player in the master list
                thePlayer.setRoundReached(theRound);
                System.out.println("set round reached for " + thePlayer.getFirstName() + " to " + theRound);

            }
        }
    }
}
