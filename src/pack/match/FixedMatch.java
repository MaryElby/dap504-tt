package pack.match;

import com.company.Player;
/**
 FixedMatch extends the Match class with an overloaded setWinner method
 For use when the player2 is a dummy
 **/
public class FixedMatch extends Match {
    public FixedMatch(Player player1, Player player2) {
        super(player1, player2);
    }
    /**
     sets the winner of a fixed match
     **/
    protected Player setWinner() {
        //in a fixed match the winner has to be player1
        //even though this is a derived class from Match it cannot see Match's private variables without the getter
        //We could have accessed them directly by making them protected and having the two classes in their own package
        //to protect against other classes in the solution
        System.out.println(getPlayer1().getFirstName() + " got a bye!");
        return getPlayer1();
    }
}
