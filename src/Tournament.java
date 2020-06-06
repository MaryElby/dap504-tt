public class Tournament {
    //work out how many rounds are needed based on the number of players
    public int GetNumberOfRounds(int numberOfPlayers){
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
}
