package pack.match;

import pack.player.Player;

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

    /**
     getter for player1Games
     * @return int the number of games won by player1 in the match
     **/
    protected int getPlayer1Games() {
        return player1Games;
    }
    /**
     getter for player2Games
     * @return int the number of games won by player2 in the match
     **/
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
     @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     @param player1 Player - the player who has been drawn as player1
     @param player2 Player - the player who has been drawn as player2
     @param numberOfGames int - the number of games that the match requires.  This could stay the same throughout the tournament or be changed in each round
     **/
    public Match(tt_gui theGui,Player player1, Player player2, int numberOfGames) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberOfGames = numberOfGames;
    }

    /**
     plays up to the prescribed number of games and keeps score.
     When all games have been played, returns the winner of the match
     The match is over once one player gets over half of the max number of games
     * @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     * @return Player - the winning player
     **/
    protected Player determineWinner(tt_gui theGui)
    {
        int numGamesPlayed = 0;
        while (numGamesPlayed < numberOfGames) {

            //Declare and run a game
            Game theGame = new Game();
            theGame.playGame();
            //process the result
            if (theGame.getPlayer1Points() > theGame.getPlayer2Points()) {
                //System.out.println("Win for player 1!");
                theGui.addReport("Game won by "+getPlayer1().getFirstName() + " " + theGame.getPlayer1Points() +" to "+theGame.getPlayer2Points());
                player1Games++;
            } else {
                //System.out.println("Win for player 2!");
                theGui.addReport("Game won by "+getPlayer2().getFirstName() + " " + theGame.getPlayer2Points() +" to "+theGame.getPlayer1Points());
                player2Games++;

            }
            numGamesPlayed++;
            //if one of the players has won more than half the games in the match then they are the winner
            //so force exit out of the loop by setting the number of games played to the max number
            if ((player1Games * 2 > numberOfGames) || (player2Games * 2 > numberOfGames)) {
                numGamesPlayed = numberOfGames;
            }
        }

        //once enough games have been played, declare a winner
        if (player1Games > player2Games) {
            //System.out.println(player1.getFirstName()+" won the match " + player1Games + " games to " + player2Games);
            theGui.addReport(player1.getFirstName()+" won the match " + player1Games + " games to " + player2Games);
            if (player1Games == player2Games + 1) {
                System.out.println("That was a close one!");
                theGui.addReport("That was a close one!");
            } else {
                //System.out.println(player1.getFirstName()+ " dominated!");
                theGui.addReport(player1.getFirstName()+ " dominated!");
            }
            return player1;


        } else {
            //System.out.println(player2.getFirstName()+" won the match " + player2Games + " games to " + player1Games);
            theGui.addReport(player2.getFirstName() +" won the match " + player2Games + " games to " + player1Games);
            if (player2Games == player1Games + 1) {
                theGui.addReport("That was a close one!");
            } else {
                //System.out.println("Player 2 dominated!");
                theGui.addReport(player2.getFirstName()+ " dominated!");
            }
            return player2;
        }

    }

 }
