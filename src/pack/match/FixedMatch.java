package pack.match;

import com.company.Player;
import pack.match.Match;

public class FixedMatch extends Match {
    public FixedMatch(Player player1, Player player2) {
        super(player1, player2);
    }

    public Player setWinner() {
        //in a fixed match the winner has to be player1
        System.out.println(player1.firstName + " got a bye!");
        return player1;
    }
}
