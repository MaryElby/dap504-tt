import com.company.Player;
import com.company.PlayersList;
//import pack.match.Round;

import java.io.FileNotFoundException;

/**
 * main - entry point for program
 */
public class Main {

    /**
     * execution starts here
     * @param args arguments if needed
     * @throws FileNotFoundException standard exception for if the JSON is not found
     */
    public static void doTournament() throws FileNotFoundException {

        // set prize fund and number of players
        /*:TODO need to get values from user rather than hardcode them*/
        double thePrizePot=0;
        String aStringPrize="";
        int numberOfPlayers = 5;

        //Create a tournament with the selected number of players and prize fund.
        Tournament theTournament= new Tournament(numberOfPlayers,thePrizePot,aStringPrize);

        //The tournament will have created as many bye players as needed to
        //make up the required number of players to run
        int numberOfByes = theTournament.getNumByes();
        int totalPlayers = numberOfPlayers+numberOfByes;
        //prob overkill but we could get these numbers from the tournament instead if we wanted e.g.
        //int totalPlayers = theTournament.getNumPlayers()+theTournament.getNumByes();
        //Create a list of the total players for the tournament

        PlayersList thePlayers = new PlayersList();
        //Add real players from JSON.  this will throw an error if it can't find the file so we have a handler
        try {
            thePlayers.createPlayerList(numberOfPlayers);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Disaster! Cannot find file of players");
            throw e;
        }


        //Add the right number of dummy players to make the tournament rum
        thePlayers.addByes(numberOfPlayers,numberOfByes);

        //Make another list the same as the first - this one will not be cut down each round so it is the
        //only list that contains all of the players.  We will need it after the tournament for reporting
        //There should be a way to just copy the list we've already made but failed to find one
        PlayersList masterList = new PlayersList();
        masterList.createPlayerList(numberOfPlayers);
        masterList.addByes(numberOfPlayers,numberOfByes);

        //hand over to the Tournament class to run the tournament
       theTournament.runTournament( thePlayers, masterList);

    }


}
