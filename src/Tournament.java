import com.company.Player;
import com.company.PlayersList;
import pack.match.Round;

/**
 * Tournament class.  Contains rounds
 * this class is an implementation of the abstract class AbstractTournament
 * it gives us some structure about what needs implementing and provides a default prize giving
 */
public class Tournament extends AbstractTournament {

    private int numRounds;
    private final double prizePot;
    private int numPlayers;
    private int numByes;



    /**
     getter for numByes
     @return numByes
     **/
    public int getNumByes() {
        return numByes;
    }

    /**
     * constructor
     * create a tournament for given number of players with given prize pot
     * @param numberOfPlayers int - number of real players who want to play in the tournament
     * @param prizePot double - the prize fund (if there is one)
     */
        public Tournament(int numberOfPlayers, double prizePot) {
        this.numByes = calcNumberOfByes(numberOfPlayers);
        this.numPlayers = numberOfPlayers;
        this.numRounds = setNumberOfRounds(numPlayers+numByes);
        this.prizePot = prizePot;
    }

    /**
     * work out how many rounds are needed based on the number of players
     * @param numberOfPlayers int total number of players in the tournament
     * @return number of rounds int calculated number of rounds needed
     */
    protected int setNumberOfRounds(int numberOfPlayers){
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
     * @param numberOfPlayers int - number of real players
     * @return number of byes int - calculated number of dummy players needed
     */
    private int calcNumberOfByes(int numberOfPlayers){
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

    /**
     * runs a tournament given two lists of players
     * @param thePlayers PlayersList - the list of players for the first round
     * @param theMasterList PlayersList -  a copy of thePlayers that will not be overwritten by subsequent rounds
     */
    void runTournament(PlayersList thePlayers,PlayersList theMasterList) {
        //run the tournament
        for (int i = 0; i < this.numRounds; i++) {
            //System.out.println("Round " + i+1);
            Round thisRound = new Round(i + 1);
            thePlayers = thisRound.doPairing(thePlayers, theMasterList);
        }
        //once that is done we should know who the winner of the tournament is ... the only one left in the thePlayers list
        Player testPlayer = thePlayers.playersList.get(0);
        //set winner's round reached otherwise it stays at 0
        theMasterList.SetLoser(theMasterList, testPlayer, numRounds + 1);
        System.out.println("And the tournament winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID());

        //present the prize
        this.presentPrize(testPlayer, this.prizePot);

        //write out the results for each player
        //this is where we need the master list which still has all the players in it
        //Can write out the dummy players as well if wanted by changing the first parameter to true.
        //Good for testing but not so much for proper tournament
        theMasterList.writePlayersResults(false, theMasterList);
    }
}

