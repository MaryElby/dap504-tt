package pack.match;

import com.company.Player;

/**
 * Match is responsible for generating games until the game limit is reached
 * and for declaring a winner
 */
public class Match {
    //player1 is set as the winner in FixedMatch so it needs to be visible to that
    //a protected variable can only be seen by classes in the same package or by derived classes
    //can either do this by making player1 protected or by implementing a getter in this class
    private Player player1;
    private Player player2;
    private int numberOfGames;
    private int player1Games=0;
    private int player2Games=0;

    protected int getPlayer1Games() {
        return player1Games;
    }

    protected int getPlayer2Games() {
        return player2Games;
    }


    /**
     getter for player1
     * @return Player the player who has been drawn as player 1
     **/
    protected Player getPlayer1() {
        return player1;
    }
    /**
     setter for player1
     @param player1 Player - the player who we want to set as player1
     **/
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    /**
     getter for player2
     * @return Player the player who has been drawn as player 2
     **/
    protected Player getPlayer2() {
        return player2;
    }
    /**
     setter for player2
     @param player2 Player - the player who we want to set as player2
     **/
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
    constructor
     @param player1 Player - the player who has been drawn as player1
     @param player2 Player - the player who has been drawn as player2
     @param numberOfGames int - the number of games that the match requires.  This could stay the same throughout the tournament or be changed in each round
     **/
    public Match(Player player1, Player player2, int numberOfGames) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberOfGames = numberOfGames;
    }

    /**
     plays the prescribed number of games and keeps score.
     When all the games have been played, returns the winner of the match
     :TODO Do we need to be a bit cleverer about the number of games played?  In a 5 game match, the winner is the first to 3
     and the match is over once one player gets to 3
     @return Player - the winning player
     **/
    protected Player determineWinner()
    {
        int numGamesPlayed=0;
        while (numGamesPlayed < numberOfGames)
        {

            //Declare and run a game
            Game theGame = new Game();
            theGame.playGame();
            //process the result
            if (theGame.getPlayer1Points() > theGame.getPlayer2Points())
            {
                System.out.println("Win for player 1!");
                player1Games++;
            }
            else
            {
                System.out.println("Win for player 2!");
                player2Games++;

            }
            numGamesPlayed++;
        }

        //once enough games have been played, declare a winner
        if (player1Games > player2Games)
        {
            System.out.println("player1 won the match " + player1Games + " games to " + player2Games);
            return player1;
        }
        else
        {
            System.out.println("player2 won the match " + player2Games + " games to " + player1Games);
            return player2;
        }

    }

 }
