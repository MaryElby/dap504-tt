import com.company.Match;
import com.company.Player;
import com.company.PlayersList;
import com.company.Round;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Tournament theTournament= new Tournament();
        int numberOfPlayers = 5;

        int numberOfByes = theTournament.GetNumberOfByes(numberOfPlayers);
        int totalPlayers = numberOfPlayers+numberOfByes;
        int numberOfRounds = theTournament.GetNumberOfRounds(totalPlayers);
        PlayersList thePlayers = new PlayersList(totalPlayers);
        //thePlayers.createListOfPlayers();
        thePlayers.createPlayerList(numberOfPlayers);
        thePlayers.addByes(numberOfPlayers,numberOfByes);

        PlayersList masterList = new PlayersList(totalPlayers);
        masterList.createPlayerList(numberOfPlayers);
        masterList.addByes(numberOfPlayers,numberOfByes);


        for (int i=0;i < numberOfRounds;i++){
            //System.out.println("Round " + i+1);
            Round thisRound = new Round(thePlayers,i+1);
            thePlayers=thisRound.doPairing(thePlayers,masterList);
        }
        //once that is done we should know who the winner of the tournament is ... the only one left in the list
        Player testPlayer = thePlayers.playersList.get(0);
        System.out.println("And the winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.playerID + " " + testPlayer.hashCode());
        //testPlayer.setRoundReached(numberOfRounds);
        masterList.SetLoser(masterList,testPlayer,numberOfRounds+1);

        masterList.writePlayersResults(masterList);

    }
}
