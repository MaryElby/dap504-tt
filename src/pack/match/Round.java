//moved this into the match package so that it could access the match's protected getplayer1 and getplayer2 methods
package pack.match;

import pack.player.Player;
import pack.player.PlayersList;

/**
 *     the Round class is responsible for creating pairings
 *     for the matches within it.
 *     It is passed a list of players and pairs them up
 *     then sends each pair to a Match.  Returning a winner from each match as a list of players
 *     package-private
 */
class Round {
    //encapsulating the round number as it is only used internally.
    private final int roundNumber;
    private final int numberOfGames;

    /**
     * constructor      package-private
     * @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     * @param RoundNumber int - the number of the round.  we use it to calculate how many games each match will be and for display in the results
     */
    Round(tt_gui theGui, int RoundNumber) {
        this.roundNumber = RoundNumber;
        numberOfGames= (RoundNumber*2)+1;
        theGui.addReport("\nRound "+roundNumber +". This round the matches are " + numberOfGames + " games each.");
    }

    /**
     *   doPairing - this is the heart of the Round object - to draw pairs from the list and send them off to matches
     *    It also deals with updating the players' stats for the round, for reporting at the end of the tournament
     * @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     * @param thePlayers PlayersList - list of players in this round
     * @param masterList PlayersList - master list of all players in the tournament
     * @return PlayersList - list of players for the next round
     **/
    PlayersList doPairing(tt_gui theGui, PlayersList thePlayers, PlayersList masterList){
        int numPlayers = thePlayers.getTotalPlayers();

        int numByes= thePlayers.getNumberOfByes();
        int numPairs = thePlayers.getSize() /2;

        //make a new list for the winners of this round's matches - although we don't have to specify the size,
        // we know it will be half the size of the incoming list
        //this will be the list sent to the next round
        PlayersList thisRoundWinners = new PlayersList();

        boolean goodChoice1;  //boolean to hold whether the player1 picked is eligible
        boolean goodChoice2;  //boolean to hold whether the player2 picked is eligible
        int player1=0; // the list element number for the player1 candidate
        int player2=0; // the list element number for the player2 candidate
        int countPairs=0;  //counter of the pairs drawn so far
        int countByes=0;   //counter of the number of dummy players drawn so far

        while (countPairs < numPairs)
        {
            goodChoice1 = false;
            while (!goodChoice1)
            {
                //pick a random element from the player array.
                //if the player has not already been chosen or knocked out
                //then they are selected for the pairing
                //also we screen out the dummy players from the player1 selection
                //to avoid having two dummy players drawn against each other
                player1 = (int) (Math.random() * thePlayers.getSize() );
                if (thePlayers.playersList.get(player1).isActive()) {
                    if (!(thePlayers.playersList.get(player1).getRoundReached() == this.roundNumber)) {
                        //don't allow dummy player in the player1 slot.  this stops dummy players being drawn against each other.
                        if (!(thePlayers.playersList.get(player1).isDummy())){
                            //player has been checked and is usable.  Set the round reached so that it isn't chosen again this round.
                            goodChoice1 = true;
                            Player myPlayer = thePlayers.playersList.get(player1);
                            myPlayer.setRoundReached(this.roundNumber);
                        }
                    }
                }
            }

            goodChoice2 = false;
            while (!goodChoice2)
            {
                //much the same as player1 selection except that dummy players can be selected as player2
                //pick a random element from the player array.
                //if the player has not already been chosen or knocked out
                //then they are selected for the pairing
                player2 = (int) (Math.random() * thePlayers.getSize()) ;

                //the active check is a quick way of showing if the selected player has already been eliminated.
                //actually the roundReached also shows if the player has already played in this round
                //but left it in in case we want to use it for, say, recording an injured player who doesn't lose but still can't play
                if (thePlayers.playersList.get(player2).isActive()) {
                    if (!(thePlayers.playersList.get(player2).getRoundReached() == this.roundNumber))
                    {
                        //if the number of pairs left to match is the same as the number of dummy players left to allocate then need a dummy in all of the rest of the pairings
                        //otherwise we will end up with 2 dummies left and they can't play each other since neither can be player1
                        //causes a perpetual loop
                        if (! ((numPairs - countPairs == numByes - countByes) && !thePlayers.playersList.get(player2).isDummy()))
                        {
                            //a valid player - set their round reached
                            goodChoice2 = true;
                            Player myPlayer = thePlayers.playersList.get(player2);
                            myPlayer.setRoundReached(this.roundNumber);
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


            //need to declare the match here because a different object will be instantiated depending on whether
            //there is a dummy player drawn for the match
            Match theMatch;
            if (thePlayers.playersList.get(player2).isDummy()){
                theGui.addReport("Match "+ countPairs + ": " + thePlayers.playersList.get(player1).getFirstName() + " " + thePlayers.playersList.get(player1).getLastName());
                //use the fixed match child class so that the match is not played
                //player1 gets a bye
                 theMatch = new FixedMatch(theGui,thePlayers.playersList.get(player1),thePlayers.playersList.get(player2));
            }
            else {
                //2 ordinary players - run a proper match for them.  The number of games in a match can be variable if required
                 theGui.addReport("Match "+ countPairs + ": " + thePlayers.playersList.get(player1).getFirstName() + " " + thePlayers.playersList.get(player1).getLastName() +" vs "+ thePlayers.playersList.get(player2).getFirstName() + " " + thePlayers.playersList.get(player2).getLastName());
                 theMatch = new Match(theGui,thePlayers.playersList.get(player1), thePlayers.playersList.get(player2), numberOfGames);
            }

            //find out the winner and the games scores
            int player1gamesWon,player2gamesWon;
            Player theWinner = theMatch.determineWinner(theGui);

            player1gamesWon= theMatch.getPlayer1Games();
            player2gamesWon = theMatch.getPlayer2Games();


            int iWinner = thePlayers.playersList.indexOf(theWinner);
            int theLoserGamesWon;
            int theWinnerGamesWon;

            //System.out.println("Player going through to next round is " + thePlayers.playersList.indexOf(theWinner));

            //Map the winner and loser to player1 and player2
            //feels v clumsy but it does what we want
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
            masterList.SetLoser(masterList,theLoser,this.roundNumber);
            //keep a running total of games won in the master list so we can report on it at the end
            masterList.AddGamesWon(masterList,theWinner,theWinnerGamesWon);
            masterList.AddGamesWon(masterList,theLoser,theLoserGamesWon);
            //don't print the going home message if this is a dummy player
            if (!theLoser.isDummy()) {
                theGui.addReport(theLoser.getFirstName() + " " + theLoser.getLastName() + " is going home.");
            }

            //Set the loser to be inactive - this stops them being selected again in this round
            theLoser.setActive(false);

            //Add the winner to the list for the next round
            thisRoundWinners.addPlayer(theWinner);
         }
        return thisRoundWinners;
    }

}
