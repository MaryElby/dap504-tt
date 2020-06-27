import com.company.Player;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

public abstract class AbstractTournament {
/*
Abstract method signature ensures that every Tournament created sets its number of rounds.
this could be different depending on what type of tournament it is
so every tournament can have its own unique method but they must implement it.
for example a doubles tournament would have a different number of rounds than singles with the same number of players
*/
    protected  double prizePot;
    abstract int setNumberOfRounds(int numberOfPlayers);

    //a concrete method to present the entire prize fund to the winner.  This could be overridden if different prize allocation is required.
    //if a subclass does not implement its own presentPrize method then this one will be used.
    // Meaning that there will always be a prize presentation that can be called at the end of every tournament.
    //the NumberFormat adds the currency symbol from the default locale and gives the right number of places after the decimal point
    void presentPrize(Player theWinner, double thePrize){
        System.out.println(theWinner.getFirstName() + " " + theWinner.getLastName()+ " takes the prize of " + NumberFormat.getCurrencyInstance().format(thePrize) + ". Well done!");
    }
}
