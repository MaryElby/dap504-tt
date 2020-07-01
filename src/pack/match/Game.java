package pack.match;

import com.company.Player;

/**
 * Game plays points by tossing a virtual coin to decide who to award the point to.
 * When either a player meets the win criteria or the total points played has reached the points threshold, the game is over.
 */
public class Game {

    private int player1Points=0;
    private int player2Points=0;
    //some fixed values for game
    static private final int pointsMax=31;
    static private final int pointsToWin=11;
    static private final int minPointsGap=2;

    /**
     * Getter for player1Points
     * @return number of points won by player1
     */
    public int getPlayer1Points() {
        return player1Points;
    }
    /**
     * Getter for player2Points
     * @return number of points won by player2
     */
    public int getPlayer2Points() {
        return player2Points;
    }

    /**
     * playGame - this is the game's primary purpose
     */
    protected void playGame() {

        int pointCounter = 0;

        while (pointCounter < pointsMax) {
            int coin = tossCoin();
            if (coin ==1) {
                player1Points++;
            }
            else{
                player2Points++;
                }

            //if one player has reached 11 and is 2 ahead of the other the game is over.
            //get-out to stop game going on for ever for evenly matched players.
            if ((player1Points>=pointsToWin && player2Points<=player1Points-minPointsGap) || (player2Points>=pointsToWin && player1Points<=player2Points-minPointsGap) || (pointCounter==pointsMax))
            {
                System.out.println("Game finished " + player1Points + " to " +player2Points);
                if (pointCounter==pointsMax){
                    System.out.println("What a marathon!");
                }
                return;
            }
            pointCounter++;

        }
    }
    /**
     * tossCoin - generates a random number < 1 and multiplies it by 2.  Returns the result rounded to an int.
     * 1 is
     */
    private int tossCoin(){
        //toss a coin to determine winner of a point
        return  (int) (Math.random() * 2) ;
    }
}
