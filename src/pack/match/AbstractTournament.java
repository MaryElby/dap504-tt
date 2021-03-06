package pack.match;

import pack.player.Player;
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
     Abstract method signatures ensure that every Tournament created sets its number of rounds and byes.
     this could be different depending on what type of tournament it is
     so every tournament can have its own unique method but they must implement it.
     for example a doubles tournament would have a different number of rounds than singles with the same number of players
     @param numberOfPlayers - number of players (both real and dummy)
     @return numberOfRounds - the number of rounds needed based on the total number of players
     */
    abstract int setNumberOfRounds(int numberOfPlayers);
    abstract  int calcNumberOfByes(int numberOfPlayers);

    /**
    a concrete method to present the entire prize fund to the winner.  This could be overridden if different prize allocation is required.
    if a subclass does not implement its own presentPrize method then this one will be used.
     Meaning that there will always be a prize presentation that can be called at the end of every tournament.
    the NumberFormat from standard java library adds the currency symbol from the default locale and gives the right number of places after the decimal point
     @param theWinner Player The player who is being awarded the prize
     @param thePrize double Prize money
     @param otherPrize string - any non-cash prize e.g. trophy, flowers, chocolates
    **/
    void presentPrize(tt_gui theGui,Player theWinner, double thePrize,String otherPrize)
    {
        String prizeStr;
        //format the prize string depending on the parameters chosen
        if ((thePrize==0) && (otherPrize.contentEquals(""))) {
            prizeStr = "kudos";
        }
        else
        {
            if (thePrize>0) {
                try {
                    prizeStr = NumberFormat.getCurrencyInstance().format(thePrize);
                    if (!otherPrize.contentEquals("") ) {
                        prizeStr = prizeStr + " and " + otherPrize;
                    }
                } catch (NumberFormatException e) {
                    prizeStr = otherPrize;
                }
            }
            else{
                    prizeStr =  otherPrize;
                }

        }

        //print out the winner and their prize
        theGui.addReport(theWinner.getFirstName() + " " + theWinner.getLastName()+ " takes the prize of " + prizeStr + ". Well done!");
    }


}
