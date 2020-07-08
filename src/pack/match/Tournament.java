package pack.match;

import com.company.Player;
import com.company.PlayersList;

import java.text.NumberFormat;

/**
 * Tournament class.  Contains rounds
 * this class is an implementation of the abstract class AbstractTournament
 * it gives us some structure about what needs implementing and provides a default prize giving
 */
public class Tournament extends AbstractTournament {

    private int numRounds;
    private final double prizePot;
    private String prize;
    //private int numPlayers;
    private long numPlayers;
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
     * @param theGui tt_gui the instance of the GUI that owns the match report text.  For posting the tournament feedback to the gui.
     * @param numberOfPlayers int - number of real players who want to play in the tournament
     * @param prizePot double - the prize fund (if there is one)
     * @param otherPrize string - non-cash prize (if there is one)
     */
        //public Tournament(tt_gui theGui, int numberOfPlayers, double prizePot, String otherPrize) {
        public Tournament(tt_gui theGui, long numberOfPlayers, double prizePot, String otherPrize) {
        this.numByes = calcNumberOfByes(numberOfPlayers);

        this.numPlayers = numberOfPlayers;
        this.numRounds = setNumberOfRounds(numPlayers+numByes);
        this.prizePot = prizePot;
        //setPrizePot(prizePot);
            try {
                this.prize = otherPrize;

                    if (this.prize.contentEquals("") ) {
                        theGui.addReport(numRounds + " rounds.  Total prize = " + NumberFormat.getCurrencyInstance().format(prizePot));
                    }
                    else
                    {
                        theGui.addReport(numRounds + " rounds.  Total prize = " + NumberFormat.getCurrencyInstance().format(prizePot) + " and " + this.prize);
                    }
            }
            catch (NullPointerException e){
                theGui.addReport(numRounds + " rounds.  Total prize = " + NumberFormat.getCurrencyInstance().format(prizePot));
            }

            theGui.addReport("Player count = " + numberOfPlayers + ". Number of byes needed=" + numByes);


    }

    /**
     * work out how many rounds are needed based on the number of players
     * @param numberOfPlayers int total number of players in the tournament
     * @return number of rounds int calculated number of rounds needed
     */
    //protected int setNumberOfRounds(int numberOfPlayers){
        protected int setNumberOfRounds(long numberOfPlayers){
        int rounds=0;
        //int counter=numberOfPlayers;
        long counter=numberOfPlayers;
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
    //private int calcNumberOfByes(int numberOfPlayers){
    private int calcNumberOfByes(long numberOfPlayers){
        //start at 1 and keep doubling until counter > numberOfPlayers.
        //then the number of byes needed is counter - numberOfPlayers
     int numByes=0;
     //int counter=1;
     long counter=1;

     while (counter< numberOfPlayers){
         counter= counter *2;
         if (counter > numberOfPlayers){
             //numByes = counter - numberOfPlayers;
             numByes = (int) (counter - numberOfPlayers);
         }
     }
        //System.out.println("Player count = " + numberOfPlayers + ". Number of byes needed=" + numByes);

     return numByes;

    }

    /**
     * runs a tournament given two lists of players
     * @param thePlayers PlayersList - the list of players for the first round
     * @param theMasterList PlayersList -  a copy of thePlayers that will not be overwritten by subsequent rounds
     */
    void runTournament(tt_gui theGui, PlayersList thePlayers, PlayersList theMasterList) {
        //show the list of players
        for (int i=0;i< thePlayers.getSize();i++){
            Player thisPlayer = thePlayers.playersList.get(i);
            if (!thisPlayer.isDummy())  {
                //System.out.println("The player " + i + " - " + thisPlayer.getFirstName() + " " + thisPlayer.getLastName() + " reached round " + thisPlayer.getRoundReached() + " and won "+ thisPlayer.getGamesWon()+" in total");
                theGui.addReport( "Player " + (i+1) + " is " + thisPlayer.getFirstName() + " " + thisPlayer.getLastName());
            }
        }
        //run the tournament
        for (int i = 0; i < this.numRounds; i++) {
            //System.out.println("Round " + i+1);
            Round thisRound = new Round(theGui,i + 1);
            thePlayers = thisRound.doPairing(theGui,thePlayers, theMasterList);
        }
        //once that is done we should know who the winner of the tournament is ... the only one left in the thePlayers list
        Player testPlayer = thePlayers.playersList.get(0);
        //set winner's round reached otherwise it stays at 0
        theMasterList.SetLoser(theMasterList, testPlayer, numRounds + 1);
        //System.out.println("And the tournament winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID());
        theGui.addReport("\nAnd the tournament winner is <drum roll please> ... \n" + testPlayer.getFirstName() + " " + testPlayer.getLastName() );
        //theGui.addReport("And the tournament winner is <drum roll please> ... " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID());
        //present the prize
        this.presentPrize(theGui,testPlayer, prizePot,this.prize);
        //Write out the podium players
        theMasterList.writePodium(theGui,theMasterList,this.numRounds);
        //write out the results for each player
        //this is where we need the master list which still has all the players in it
        //Can write out the dummy players as well if wanted by changing the first parameter to true.
        //Good for testing but not so much for proper tournament
        theMasterList.writePlayersResults(theGui,false, theMasterList);

    }
}

