//moved this into the match package so that it could access the match's protected getplayer1 and getplayer2 methods
package pack.match;

import com.company.Player;
import com.company.PlayersList;

/**
 *     the Round class is responsible for creating pairings
 *     for the matches within it.
 *     It is passed a list of players and pairs them up
 *     then sends each pair to a Match.  Returning a winner from each match as a list of players
 */
public class Round {
    //encapsulating the round number as it is only used internally.  in fact, is only used for display purposes
    private final int roundNumber;
    private final int numberOfGames;

    /**
     * constructor
     * @param RoundNumber int - the number of the round.  we use it to calculate how many games each match will be and for display in the results
     */
    public Round(tt_gui theGui,int RoundNumber) {
        this.roundNumber = RoundNumber;
        numberOfGames= (RoundNumber*2)+1;
        System.out.println("this round the matches are " + numberOfGames + " games each.");
        theGui.addReport("Round "+roundNumber +". This round the matches are " + numberOfGames + " games each.");
    }

    /**
     *    this is the heart of the Round object - to draw pairs from the list and send them off to matches
     *    It also deals with updating the players' stats for the round, for reporting at the end of the tournament
     * @param thePlayers PlayersList - list of players in this round
     * @param masterList PlayersList - master list of all players in the tournament
     * @return PlayersList - list of players for the next round
     **/
     public PlayersList doPairing(tt_gui theGui,PlayersList thePlayers, PlayersList masterList){
        int numPlayers = thePlayers.getTotalPlayers();
        int numByes= thePlayers.getNumberOfByes();
        int numPairs = thePlayers.getSize() /2;
        System.out.println("Round " + this.roundNumber +" We have " + thePlayers.getSize() + " so that's " + numPairs + " pairs");
        theGui.addReport("Round " + this.roundNumber +" We have " + thePlayers.getSize() + " so that's " + numPairs + " pairs");
        for (int i=0;i< thePlayers.getSize();i++){
            Player testPlayer = thePlayers.playersList.get(i);
            System.out.println("player "+ i+" is " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID() );
            theGui.addReport("player "+ i+" is " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID() );
        }

        //make a new list for the winners of this round's matches - we know the size is half the size of the incoming list
        //this will be the list sent to the next round
        PlayersList thisRoundWinners = new PlayersList();

        boolean goodChoice1 = false;
        boolean goodChoice2 = false;
        int player1=0;
        int player2=0;
        //for (int i=1;i< numPairs;i++)
        int countPairs=0;
        int countByes=0;
        while (countPairs < numPairs)
        {
            goodChoice1 = false;
            while (goodChoice1 ==false)
            {
                //pick a random element from the player array.
                //if the player has not already been chosen or knocked out
                //then they are selected for the pairing
                //also we screen out the dummy players from the player1 selection
                //to avoid having two dummy players drawn against each other
                player1 = (int) (Math.random() * thePlayers.getSize() );

                if (thePlayers.playersList.get(player1).isActive() == false) {
                    //System.out.println("better pick p1 again, this player("+ player1 +") has already been eliminated");
                } else {
                    if (thePlayers.playersList.get(player1).getRoundReached() == this.roundNumber) {
                        //System.out.println("better pick p1 again, this player("+ player1 +") has already played this round");
                    } else {
                        //don't allow dummy player in the player1 slot.  this stops dummy players being drawn against each other.
                        if (thePlayers.playersList.get(player1).isDummy()==true){
                            //System.out.println("dummy p1 player("+ player1 +"), better pick again");
                        }
                        else {
                            //System.out.println("this number is free, let's use it");
                            goodChoice1 = true;
                            Player myPlayer = thePlayers.playersList.get(player1);
                            myPlayer.setRoundReached(this.roundNumber);
                            //System.out.println(myPlayer.roundReached);
                        }
                    }
                }
            }

            goodChoice2 = false;
            while (goodChoice2 ==false)
            {
                //much the same as player1 selection except that dummy players can be selected as player2
                //pick a random element from the player array.
                //if the player has not already been chosen or knocked out
                //then they are selected for the pairing
                player2 = (int) (Math.random() * thePlayers.getSize()) ;

                //the active check is a quick way of showing if the selected player has already been eliminated.
                //actually the roundReached also shows if the player has already played in this round
                //but left it in in case we want to use it for, say, recording an injured player who doesn't lose but still can't play
                if (thePlayers.playersList.get(player2).isActive() == false)
                {
                    //System.out.println("better pick p2 again, this player ("+ player2 +") has already been removed");
                }
                else
                {
                    if (thePlayers.playersList.get(player2).getRoundReached() == this.roundNumber)
                    {
                        //System.out.println("better pick p2 again, this player("+ player2 +") has already played this round");
                    }
                    else
                    {
                        //if the number of pairs left to match is the same as the number of dummy players left to allocate then need a dummy in all of the rest of the pairings
                        //otherwise we will end up with 2 dummies left and they can't play each other since neither can be player1
                        //causes a perpetual loop
                        //if ((numPairs - countPairs == thePlayers.getNumberOfByes() - countByes) && thePlayers.playersList.get(player2).dummy == false)
                        if ((numPairs - countPairs == numByes - countByes) && thePlayers.playersList.get(player2).isDummy() == false)
                        {
                             //reject the real player
                            //System.out.println("need to make sure we have a dummy p2 player - rejected this player("+ player2 +")");
                        }
                        else
                        {
                            //System.out.println("this number is free, let's use it");
                            goodChoice2 = true;
                            Player myPlayer = thePlayers.playersList.get(player2);
                            myPlayer.setRoundReached(this.roundNumber);
                            //System.out.println(myPlayer.roundReached);
                            //Add 1 to the local count of dummy players paired
                            if (myPlayer.isDummy()){
                                countByes++;
                            }

                        }

                    }

                }

            }
            ///Add 1 to the local count of pairs created
            countPairs++;
            System.out.println("Pairing "+ countPairs + " Picked players "+ player1 + " and "+ player2);
            theGui.addReport("Pairing "+ countPairs + " Picked players "+ player1 + " and "+ player2);
            //System.out.println("Count of Pairs is now " + countPairs);

            //need to declare the match here because a different object will be instantiated depending on whether
            //there is a dummy player drawn for the match
            Match theMatch;
            if (thePlayers.playersList.get(player2).isDummy()==true){
                //use the fixed match child class so that the match is not played
                //player1 gets a bye
                 theMatch = new FixedMatch(theGui,thePlayers.playersList.get(player1),thePlayers.playersList.get(player2));
            }
            else {
                //2 ordinary players - run a proper match for them.  The number of games in a match can be variable if required
                 theMatch = new Match(theGui,thePlayers.playersList.get(player1), thePlayers.playersList.get(player2), numberOfGames);
            }


            //Set statuses for the players
            //Should this be in the match class?
            int player1gamesWon=0,player2gamesWon=0;
            Player theWinner = theMatch.determineWinner(theGui);

            player1gamesWon= theMatch.getPlayer1Games();
            player2gamesWon = theMatch.getPlayer2Games();


            int iWinner = thePlayers.playersList.indexOf(theWinner);
            int theLoserGamesWon=0;
            int theWinnerGamesWon=0;

            System.out.println("Player going through to next round is " + thePlayers.playersList.indexOf(theWinner));

            //Map the winner and loser to player1 and player2
            Player theLoser;
            if ( iWinner==player1) {
                theLoser = theMatch.getPlayer2();
                theLoserGamesWon=player2gamesWon;
                theWinnerGamesWon=player1gamesWon;
            }
            else{
                theLoser = theMatch.getPlayer1();
                theLoserGamesWon=player1gamesWon;
                theWinnerGamesWon=player2gamesWon;
            }

            //Set the round reached in the master list for the player who lost
            //System.out.println("Player being knocked out is " + thePlayers.playersList.indexOf(theLoser));
            masterList.SetLoser(masterList,theLoser,this.roundNumber);
            //keep a running total of games won in the master list so we can report on it at the end
            masterList.AddGamesWon(masterList,theWinner,theWinnerGamesWon);
            masterList.AddGamesWon(masterList,theLoser,theLoserGamesWon);

            System.out.println("The player " + theWinner.getFirstName() + " " + theWinner.getLastName() + " won the match ");
            System.out.println("The player " + theLoser.getFirstName() + " " + theLoser.getLastName() + " is going home ");
            if (!theLoser.isDummy()) {
                theGui.addReport(theLoser.getFirstName() + " " + theLoser.getLastName() + " is going home.");
            }

            //Set the loser to be inactive - this stops them being selected
            theLoser.setActive(false);

            //Add the winner to the list for the next round
            thisRoundWinners.addPlayer(theWinner);
         }

        System.out.println("so now we have " + thisRoundWinners.getSize());

        return thisRoundWinners;
    }

}
