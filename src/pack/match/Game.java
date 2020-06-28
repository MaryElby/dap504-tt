package pack.match;

import com.company.Player;

public class Game {

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    private int player1Points=0;
    private int player2Points=0;
    //some fixed values for game
    static private final int pointsMax=31;
    static private final int pointsToWin=11;
    static private final int minPointsGap=2;

    private int setPointWinner(){return 0;}

    protected void playGame() {
        //Game theGame = new Game();

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
                return;
            }
            pointCounter++;

        }
    }
    private int tossCoin(){
        //toss a coin to determine winner of a point
        return  (int) (Math.random() * 2) ;
    }
}
