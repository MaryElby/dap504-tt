//moved this into the match package so that it could access the match's protected getplayer1 and getplayer2 methods
package pack.match;

import com.company.Player;
import com.company.PlayersList;
import pack.match.FixedMatch;
import pack.match.Match;

public class Round {
    //encapsulating the round number as it is only used internally.  in fact, is only used for display purposes
    private int roundNumber;
    //the Round class is responsible for creating pairings
    //for the matches within it.
    //It is passed a list of players and pairs them up
    //then sends each pair to a Match.  Returning a winner from each match as a list of players

    //constructor
    public Round(int RoundNumber) {
        this.roundNumber = RoundNumber;
    }

    //this is the heart of the Round object - to draw pairs from the list and send them off to matches
    public PlayersList doPairing(PlayersList thePlayers, PlayersList masterList){
        int numPlayers = thePlayers.getNumberOfPlayers();
        int numByes= thePlayers.getNumberOfByes();
        int numPairs = thePlayers.getSize() /2;
        System.out.println("Round " + this.roundNumber +" We have " + numPlayers + " so that's " + numPairs + " pairs");
        for (int i=0;i< thePlayers.getSize();i++){
            Player testPlayer = thePlayers.playersList.get(i);
            System.out.println("player "+ i+" is " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.getPlayerID() );

        }

        //make a new list for the winners of this round's matches
        //this will be the list sent to the next round
        PlayersList thisRoundWinners = new PlayersList(numPairs);

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
                    System.out.println("better pick p1 again, this player("+ player1 +") has already been eliminated");
                } else {
                    if (thePlayers.playersList.get(player1).getRoundReached() == this.roundNumber) {
                        System.out.println("better pick p1 again, this player("+ player1 +") has already played this round");
                    } else {
                        //don't allow dummy player in the player1 slot.  this stops dummy players being drawn against each other.
                        if (thePlayers.playersList.get(player1).isDummy()==true){
                            System.out.println("dummy p1 player("+ player1 +"), better pick again");
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
                //actually the roundReached also shows this
                //but left it in in case we want to use it for, say, recording an injured player who doesn't lose but still can't play
                if (thePlayers.playersList.get(player2).isActive() == false)
                {
                    System.out.println("better pick p2 again, this player ("+ player2 +") has already been removed");
                }
                else
                {
                    if (thePlayers.playersList.get(player2).getRoundReached() == this.roundNumber)
                    {
                        System.out.println("better pick p2 again, this player("+ player2 +") has already played this round");
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
                            System.out.println("need to make sure we have a dummy p2 player - rejected this player("+ player2 +")");
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
            //System.out.println("Count of Pairs is now " + countPairs);

            //need to declare the match here because a different object will be instantiated depending on whether
            //there is a dummy player drawn for the match
            Match theMatch;
            if (thePlayers.playersList.get(player2).isDummy()==true){
                //use the fixed match child class so that the match is not played
                //player1 gets a bye
                 theMatch = new FixedMatch(thePlayers.playersList.get(player1),thePlayers.playersList.get(player2));
            }
            else {
                //2 ordinary players - run a proper match for them
                 theMatch = new Match(thePlayers.playersList.get(player1), thePlayers.playersList.get(player2));
            }


            //Set statuses for the players
            //Should this be in the match class?
            Player theWinner = theMatch.setWinner();
            int iWinner = thePlayers.playersList.indexOf(theWinner);
            System.out.println("Player going through to next round is " + thePlayers.playersList.indexOf(theWinner));
            //Player theLoser = theMatch.setLoser();
            Player theLoser;
            if ( iWinner==player1) {
                theLoser = theMatch.getPlayer2();
            }
            else{
                theLoser = theMatch.getPlayer1();
            }

            //Set the round reached in the master list for the player who lost
            //System.out.println("Player being knocked out is " + thePlayers.playersList.indexOf(theLoser));
            masterList.SetLoser(masterList,theLoser,this.roundNumber);

            //System.out.println("The player " + theWinner.firstName + " " +  theWinner.lastName + " won the match ");
            //System.out.println("The player " + theLoser.firstName + " " +  theLoser.lastName + " is going home ");

            theLoser.setActive(false);

            //Add the winner to the list for the next round
            thisRoundWinners.addPlayer(theWinner);

        }
        System.out.println("so now we have " + thisRoundWinners.getSize());

        return thisRoundWinners;
    }

}