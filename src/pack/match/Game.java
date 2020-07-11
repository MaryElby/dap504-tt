package pack.match;

import java.util.Random;

/**
 * Game plays points by tossing a virtual coin to decide who to award the point to.
 * When either a player meets the win criteria or the total points played has reached the points threshold, the game is over.
 * This class can only be accessed within the match package
 */
class Game {

    private int player1Points=0;
    private int player2Points=0;
    //some fixed values for game
    static private final int pointsMax=31;
    static private final int pointsToWin=11;
    static private final int minPointsGap=2;

    /**
     * Getter for player1Points
     * @return number of points won by player1
     * package-private
     */
    int getPlayer1Points() {
        return player1Points;
    }
    /**
     * Getter for player2Points
     * @return number of points won by player2
     * package-private
     */
    int getPlayer2Points() {
        return player2Points;
    }

    /**
     * playGame - this is the game's primary purpose
     * @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     */
    protected void playGame(tt_gui theGui) {

        int pointCounter = 0;

        //toss a coin to decide who wins the point
        while (pointCounter < pointsMax) {
            //int coin = tossCoin();
            int coin = tossCoin(2);
            if (coin ==1) {
                player1Points++;
            }
            else{
                player2Points++;
                }

            pointCounter++;
            //if one player has reached 11 and is 2 ahead of the other the game is over.
            //get-out to stop game going on for ever for evenly matched players.
            if ((player1Points>=pointsToWin && player2Points<=player1Points-minPointsGap) || (player2Points>=pointsToWin && player1Points<=player2Points-minPointsGap) || (pointCounter==pointsMax))
            {

                if (pointCounter==pointsMax){
                    theGui.addReport("What a marathon!");
                }
                return;
            }

        }
    }
    /**
     * tossCoin - generates a random number < 1 and rounds it.  Returns the result cast to an int.
     * 1 is a point for player1, 0 is a point for player2
     */

    private static int tossCoin(int upperRange){
            //toss a virtual coin to determine winner of a point

        Random random = new Random();
        return random.nextInt(upperRange);
    }
}
