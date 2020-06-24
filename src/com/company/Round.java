package com.company;

import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class Round {
    public int roundNumber;
    //the Round class is responsible for creating pairings
    //for the matches within it.
    //It is passed a list of players and pairs them up
    //then sends each pair to a Match.  Returning a winner from each match as a list of players

    public Round(PlayersList thePlayers,int RoundNumber) {
        this.roundNumber = RoundNumber;
    }



    public PlayersList doPairing(PlayersList thePlayers,PlayersList masterList){
        int numPlayers = thePlayers.getNumberOfPlayers();
        int numPairs = numPlayers /2;
        System.out.println("Round " + this.roundNumber +" We have " + numPlayers + " so that's " + numPairs + " pairs");
        for (int i=0;i< thePlayers.getSize();i++){
            Player testPlayer = thePlayers.playersList.get(i);
            System.out.println("player "+ i+" is " + testPlayer.getFirstName() + " " + testPlayer.getLastName() + " " + testPlayer.hashCode());

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
                player1 = (int) (Math.random() * numPlayers );

                if (thePlayers.playersList.get(player1).isActive() == false) {
                    System.out.println("better pick again, this player has already been removed");
                } else {
                    if (thePlayers.playersList.get(player1).roundReached == this.roundNumber) {
                        //System.out.println("better pick again, this player has already been picked for this round");
                    } else {
                        //don't allow dummy player in the player1 slot.  this stops dummy players being drawn against each other.
                        if (thePlayers.playersList.get(player1).dummy==true){
                            //System.out.println("dummy player, better pick again");
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
                player2 = (int) (Math.random() * numPlayers) ;

                if (thePlayers.playersList.get(player2).isActive() == false)
                {
                    //System.out.println("better pick again, this player has already been removed");
                }
                else
                {
                    if (thePlayers.playersList.get(player2).roundReached == this.roundNumber)
                    {
                        //System.out.println("better pick again, this player has already been picked for this round");
                    }
                    else
                    {
                        //System.out.println("this number is free, let's use it");
                        goodChoice2 = true;
                        Player myPlayer = thePlayers.playersList.get(player2);
                        myPlayer.setRoundReached(this.roundNumber);
                        //System.out.println(myPlayer.roundReached);

                    }
                }
            }

            System.out.println("Picked players "+ player1 + " and "+ player2);
            //need to declare the match here because a different object will be instantiated depending on whether
            //there is a dummy player drawn for the match
            Match theMatch;
            if (thePlayers.playersList.get(player2).dummy==true){
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
            System.out.println("Player being knocked out is " + thePlayers.playersList.indexOf(theLoser));
            masterList.SetLoser(masterList,theLoser,this.roundNumber);

            //System.out.println("The player " + theWinner.firstName + " " +  theWinner.lastName + " won the match ");
            //System.out.println("The player " + theLoser.firstName + " " +  theLoser.lastName + " is going home ");

            //theLoser.setActive(false);

            //Add the winner to the list for the next round
            thisRoundWinners.addPlayer(theWinner);
////
////        for (int i=0;i< this.playersList.size();i++){
////            System.out.println("The player "+ i+" is " + playersList.get(i).firstName + " " +  playersList.get(i).lastName + " " + playersList.get(i).hashCode());
////
////        }*/

            //System.out.println("picked " + countPairs + " pairs");
            countPairs+=1;
        }
        System.out.println("so now we have " + thisRoundWinners.getSize());

        return thisRoundWinners;
    }

}
