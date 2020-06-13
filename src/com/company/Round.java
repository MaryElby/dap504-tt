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



    public PlayersList doPairing(PlayersList thePlayers){
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
                //player1 = (int) (Math.random() * numPlayers - 1);
                player1 = (int) (Math.random() * numPlayers );

                if (thePlayers.playersList.get(player1).isActive() == false) {
                    //System.out.println("better pick again, this player has already been removed");
                } else {
                    if (thePlayers.playersList.get(player1).roundReached == this.roundNumber) {
                        //System.out.println("better pick again, this player has already been picked for this round");
                    } else {
                        //System.out.println("this number is free, let's use it");
                        goodChoice1 = true;
                        Player myPlayer = thePlayers.playersList.get(player1);
                        myPlayer.setRoundReached(this.roundNumber);
                        //System.out.println(myPlayer.roundReached);
                    }
                }
            }

            goodChoice2 = false;
            while (goodChoice2 ==false)
            {
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

            Match theMatch = new Match(thePlayers.playersList.get(player1),thePlayers.playersList.get(player2));


            //theMatch.getWinnerAndLoser(theWinner, theLoser);
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

            System.out.println("Player being knocked out is " + thePlayers.playersList.indexOf(theLoser));

            //int iLoser = thePlayers.playersList.indexOf(theLoser);
            //int iWinner = thePlayers.playersList.indexOf(theWinner);
            System.out.println("The player " + theWinner.firstName + " " +  theWinner.lastName + " won the match ");
            System.out.println("The player " + theLoser.firstName + " " +  theLoser.lastName + " is going home ");
            //System.out.println("The player " + thePlayers.playersList.get(iWinner).firstName + " " +  thePlayers.playersList.get(iWinner).lastName + " beat "+ thePlayers.playersList.get(iLoser).firstName + " " +  thePlayers.playersList.get(iLoser).lastName );
            //System.out.println("The player " + thePlayers.playersList.get(iLoser).firstName + " " +  thePlayers.playersList.get(iLoser).lastName + " was knocked out of the tournament!");

            theLoser.setActive(false);
            //thePlayers.removePlayer(thePlayers.playersList.indexOf(theLoser));

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
