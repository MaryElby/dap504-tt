import com.company.Player;
import com.company.PlayersList;
import com.company.Round;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Tournament theTournament= new Tournament();
        theTournament.prizePot = 1234.4256;
        int numberOfPlayers = 5;

        int numberOfByes = theTournament.GetNumberOfByes(numberOfPlayers);
        int totalPlayers = numberOfPlayers+numberOfByes;
        int numberOfRounds = theTournament.setNumberOfRounds(totalPlayers);
        PlayersList thePlayers = new PlayersList(totalPlayers);
        //thePlayers.createListOfPlayers();
        thePlayers.createPlayerList(numberOfPlayers);
        thePlayers.addByes(numberOfPlayers,numberOfByes);
        thePlayers.setNumberOfByes(numberOfByes);
        thePlayers.setNumberOfPlayers(numberOfPlayers);

        PlayersList masterList = new PlayersList(totalPlayers);
        masterList.createPlayerList(numberOfPlayers);
        masterList.setNumberOfByes(numberOfByes);
        masterList.addByes(numberOfPlayers,numberOfByes);


        for (int i=0;i < numberOfRounds;i++){
            //System.out.println("Round " + i+1);
            Round thisRound = new Round(i+1);
            thePlayers=thisRound.doPairing(thePlayers,masterList);
        }
        //once that is done we should know who the winner of the tournament is ... the only one left in the list
        Player testPlayer = thePlayers.playersList.get(0);

        System.out.println("And the winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.playerID + " " + testPlayer.hashCode());
        theTournament.presentPrize(testPlayer,theTournament.prizePot);
        //testPlayer.setRoundReached(numberOfRounds);
        //set winner's round reached otherwise it stays at 0
        masterList.SetLoser(masterList,testPlayer,numberOfRounds+1);

        masterList.writePlayersResults(masterList);

    }
}
