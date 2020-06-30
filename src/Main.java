import com.company.Player;
import com.company.PlayersList;
//import pack.match.Round;

import java.io.FileNotFoundException;

/**
 * main - entry point for program
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        double thePrizePot=12572.64256;
        int numberOfPlayers = 5;

        Tournament theTournament= new Tournament(numberOfPlayers,thePrizePot);

        int numberOfByes = theTournament.getNumByes();
        int totalPlayers = numberOfPlayers+numberOfByes;
        //int numberOfRounds = theTournament.setNumberOfRounds(totalPlayers);
        PlayersList thePlayers = new PlayersList(totalPlayers);
        thePlayers.createPlayerList(numberOfPlayers);
        thePlayers.addByes(numberOfPlayers,numberOfByes);

        //Make another list the same as the first - this one will not be cut down each round so it is the
        //only list that contains all of the players.  We will need it after the tournament for reporting
        PlayersList masterList = new PlayersList(totalPlayers);
        masterList.createPlayerList(numberOfPlayers);
        masterList.addByes(numberOfPlayers,numberOfByes);

        //run the tournament
        //this moved to tournament class
       theTournament.runTournament( thePlayers, masterList);


    }
}
