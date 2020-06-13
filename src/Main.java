import com.company.Match;
import com.company.Player;
import com.company.PlayersList;
import com.company.Round;

import java.io.FileNotFoundException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Tournament theTournament= new Tournament();
        int numberOfPlayers = 16;
        int numberOfRounds = theTournament.GetNumberOfRounds(numberOfPlayers);

        PlayersList thePlayers = new PlayersList(numberOfPlayers);
        //thePlayers.createListOfPlayers();
        thePlayers.createPlayerList(numberOfPlayers);

        for (int i=0;i < numberOfRounds;i++){
            //System.out.println("Round " + i+1);
            Round thisRound = new Round(thePlayers,i+1);
            thePlayers=thisRound.doPairing(thePlayers);
        }
        //once that is done we should know who the winner of the tournament is
        Player testPlayer = thePlayers.playersList.get(0);
        System.out.println("And the winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.hashCode());


    }
}
