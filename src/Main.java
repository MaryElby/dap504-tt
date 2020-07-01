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
     */
    public static void main(String[] args) throws FileNotFoundException {

        // set prize fund and number of players
        /*:TODO need to get values from user rather than hardcode them*/
        double thePrizePot=12572.64256;
        int numberOfPlayers = 5;

        //Create a tournament with the selected number of players and prize fund.
        Tournament theTournament= new Tournament(numberOfPlayers,thePrizePot);

        //The tournament will have created as many bye players as needed to
        //get the required number of players to run
        int numberOfByes = theTournament.getNumByes();
        int totalPlayers = numberOfPlayers+numberOfByes;
        //Create the list of players for the tournament
        PlayersList thePlayers = new PlayersList(totalPlayers);
        thePlayers.createPlayerList(numberOfPlayers);
        thePlayers.addByes(numberOfPlayers,numberOfByes);

        //Make another list the same as the first - this one will not be cut down each round so it is the
        //only list that contains all of the players.  We will need it after the tournament for reporting
        PlayersList masterList = new PlayersList(totalPlayers);
        masterList.createPlayerList(numberOfPlayers);
        masterList.addByes(numberOfPlayers,numberOfByes);

        //hand over to the Tournament class to run the tournament
       theTournament.runTournament( thePlayers, masterList);

    }
}
