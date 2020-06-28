package pack.match;

import com.company.Player;

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
     **/
    protected Player getPlayer1() {
        return player1;
    }
    /**
     setter for player1
     **/
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    /**
     getter for player2
     **/
    protected Player getPlayer2() {
        return player2;
    }
    /**
     setter for player2
     **/
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    //constructor
    public Match(Player player1, Player player2, int numberOfGames) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberOfGames = numberOfGames;
    }
    /**
     sets the winner of a match
     **/
    protected Player setWinner() {
        Player theWinner = player1;
        Player theLoser = player2;
        System.out.println("The player " + theWinner.getFirstName() + " " +  theWinner.getLastName() + " won the match ");
        System.out.println("The player " + theLoser.getFirstName() + " " +  theLoser.getLastName() + " is going home ");
        return player1;
    }

    protected Player determineWinner()
    {
        int numGamesPlayed=0;
        while (numGamesPlayed < numberOfGames)
        {


            Game theGame = new Game();


            theGame.playGame();
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
