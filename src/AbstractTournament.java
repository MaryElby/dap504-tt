import com.company.Player;
import java.text.NumberFormat;

/**
 * Abstract class to enforce the setRounds method in derived classes
 * and also to create a default presentPrize for the class
 */
public abstract class AbstractTournament {
    /**
     variable for prize money
     **/
    private double prizePot;
    /**
     getter for prizePot
     **/
    public double getPrizePot() {
        return prizePot;
    }
    /**
     setter for prizePot
     **/
    public void setPrizePot(double prizePot) {
        this.prizePot = prizePot;
    }

    /**
     Abstract method signature ensures that every Tournament created sets its number of rounds.
     this could be different depending on what type of tournament it is
     so every tournament can have its own unique method but they must implement it.
     for example a doubles tournament would have a different number of rounds than singles with the same number of players
     */
    abstract int setNumberOfRounds(int numberOfPlayers);

    /**
    a concrete method to present the entire prize fund to the winner.  This could be overridden if different prize allocation is required.
    if a subclass does not implement its own presentPrize method then this one will be used.
     Meaning that there will always be a prize presentation that can be called at the end of every tournament.
    the NumberFormat from standard java library adds the currency symbol from the default locale and gives the right number of places after the decimal point
    :TODO if no prize money then maybe need to do something different - or shall we handle that in override in the Tournament class?
    **/
    void presentPrize(Player theWinner, double thePrize){
        System.out.println(theWinner.getFirstName() + " " + theWinner.getLastName()+ " takes the prize of " + NumberFormat.getCurrencyInstance().format(thePrize) + ". Well done!");
    }


}
