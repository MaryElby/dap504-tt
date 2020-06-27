package pack.match;

import com.company.Player;

public class Match {
    //player1 is set as the winner in FixedMatch so it needs to be visible to that
    //a protected variable can only be seen by classes in the same package or by derived classes
    //can either do this by making player1 protected or by implementing a getter in this class
    private Player player1;
    private Player player2;

    //constructor
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    protected Player setWinner() {
        Player theWinner = player1;
        Player theLoser = player2;
        System.out.println("The player " + theWinner.getFirstName() + " " +  theWinner.getLastName() + " won the match ");
        System.out.println("The player " + theLoser.getFirstName() + " " +  theLoser.getLastName() + " is going home ");
        return player1;
    }

    protected Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    protected Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


    public Player setLoser() {
        return player1;
    }

    public void getWinnerAndLoser(Player winner, Player loser){
        winner = this.player2;
        loser = this.player1;
    }
}
