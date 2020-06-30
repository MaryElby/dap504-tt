import com.company.Player;
import com.company.PlayersList;
import pack.match.Round;

/**
 * Tournament class.  Contains rounds
 */
public class Tournament extends AbstractTournament {
    /**
     * work out how many rounds are needed based on the number of players
     * @param numberOfPlayers
     * @return number of rounds
     */
    int numRounds;
    double prizePot;

    public int getNumRounds() {
        return numRounds;
    }
    public int setNumberOfRounds(int numberOfPlayers){
        int rounds=0;
        int counter=numberOfPlayers;
        System.out.println("Number of players = " + numberOfPlayers);
        while (counter >1){
            counter=counter/2;
            rounds++;
            //System.out.println("Counter " + counter + " Rounds " + rounds);
        }
        //rounds = (int) (2* Math.exp(numberOfPlayers));
        System.out.println("final count of Rounds "+ rounds);

        this.numRounds=rounds;
        return rounds;
    }

    /**
     * work out how many dummy players are needed to run a tournament
     * @param numberOfPlayers
     * @return number of byes
     */
    public int GetNumberOfByes(int numberOfPlayers){
        //start at 1 and keep doubling until counter > numberOfPlayers.
        //then the number of byes needed is counter - numberOfPlayers
     int numByes=0;
     int counter=1;


     while (counter< numberOfPlayers){
         counter= counter *2;
         if (counter > numberOfPlayers){
             numByes = counter - numberOfPlayers;
         }
     }
        System.out.println("Player count = " + numberOfPlayers + ". Number of byes needed=" + numByes);

     return numByes;

    }

    void runTournament(PlayersList thePlayers,PlayersList theMasterList) {
        //run the tournament
        for (int i = 0; i < this.numRounds; i++) {
            //System.out.println("Round " + i+1);
            Round thisRound = new Round(i + 1);
            thePlayers = thisRound.doPairing(thePlayers, theMasterList);
        }
        //once that is done we should know who the winner of the tournament is ... the only one left in the list
        Player testPlayer = thePlayers.playersList.get(0);
        System.out.println("And the winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID());
        this.presentPrize(testPlayer, this.prizePot);
        //testPlayer.setRoundReached(numberOfRounds);
        //set winner's round reached otherwise it stays at 0
        theMasterList.SetLoser(theMasterList, testPlayer, numRounds + 1);

        theMasterList.writePlayersResults(0, theMasterList);
    }
}

