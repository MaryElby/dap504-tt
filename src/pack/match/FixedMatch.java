package pack.match;

import pack.player.Player;
/**
 FixedMatch extends the Match class with an overloaded determineWinner method (inheritance)
 For use when player2 is a dummy
 **/
class FixedMatch extends Match {
    private static final int numberOfGames = 0;

    public FixedMatch(tt_gui theGui,Player player1, Player player2) {
        super(theGui,player1, player2, numberOfGames);
    }

    /** determineWinner (polymorphism)
     * in a fixed match the winner has to be player1
    Even though this is a derived class from Match it cannot see Match's private variables without the getter
     */
    protected Player determineWinner(tt_gui theGui) {

            System.out.println(getPlayer1().getFirstName() + " got a bye!");
            theGui.addReport(getPlayer1().getFirstName() + " got a bye!");
            return getPlayer1();
    }
}
