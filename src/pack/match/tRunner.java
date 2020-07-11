package pack.match;


import pack.player.PlayersList;


import java.io.FileNotFoundException;

/**
 * tournament runner class.  This is called from the GUI
 */
class tRunner {
    /**
     * execution starts here
     * @param theGui tt_gui - Handle for the GUI so we can ask it to print messages to its textbox
     * @param numPlayers int - number of players selected by user.  Int has a limit of 128.
     * @param cashPrize double - cash prize selected by user.  May be null if required.
     * @param nonCashPrize String - non-cash prize entered by user.  May be null if required.
     * @throws FileNotFoundException exception - standard exception for if the JSON is not found.
     */
    //package-private to protect against other packages
    void doTournament(tt_gui theGui, int numPlayers, double cashPrize, String nonCashPrize) throws FileNotFoundException {
        //Create a tournament with the selected number of players and prizes.
        Tournament theTournament= new Tournament(theGui,numPlayers,cashPrize,nonCashPrize);

        //The tournament will have created as many bye players as needed to
        //make up the required number of players to run
        int numberOfByes = theTournament.getNumByes();
        int totalPlayers = numPlayers+numberOfByes;

        //prob overkill but we could get these numbers from the tournament instead if we wanted e.g.
        //int totalPlayers = theTournament.getNumPlayers()+theTournament.getNumByes();
        //Create a list of the total players for the tournament

        PlayersList thePlayers = new PlayersList();
        //Add real players from JSON.  this will throw an error if it can't find the file so we have a handler
        try {
            thePlayers.createPlayerList(numPlayers);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Disaster! Cannot find file of players");
            throw e;
        }

        //Add the right number of dummy players to make the tournament rum
        thePlayers.addByes(numPlayers,numberOfByes);

        //Make another list the same as the first - this one will not be cut down each round so it is the
        //only list that contains all of the players at the end of the tournament.
        // We will need it after the tournament for reporting
        //There should be a way to just copy the list we've already made but failed to find one
        PlayersList masterList = new PlayersList();
        masterList.createPlayerList(numPlayers);
        masterList.addByes(numPlayers,numberOfByes);

        //hand over to the Tournament object to run the tournament
       theTournament.runTournament( theGui,thePlayers, masterList);

    }


}
