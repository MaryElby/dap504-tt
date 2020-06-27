/**
 * Tournament class.  Contains rounds
 */
public class Tournament extends AbstractTournament {
    /**
     * work out how many rounds are needed based on the number of players
     * @param numberOfPlayers
     * @return number of rounds
     */
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
}
