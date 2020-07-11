package pack.player;
/**
 Player class.  Holds all the needed attributes of a player
 **/
public class Player {
//make all the attributes private so they can only be accessed with getters/setters/methods in this class (Encapsulation)
    private String firstName,lastName;
    //private int playerID =0;   //need to be able to match players in master list to players in round list
    private long playerID ;   //need to be able to match players in master list to players in round list
    private boolean active=true; //every player starts as active
    private int roundReached=0; //every player starts at round 0
    private int gamesWon=0; //every player starts with 0 games won
    private boolean dummy; //default is false.  this is changed in the constructor for dummy players

    /**
     getter for gamesWon
     @return gamesWon int
     **/
    public int getGamesWon() {
        return gamesWon;
    }
    /**
     setter for gamesWon
     @param gamesWon int
     note: adds the incoming number to the player's total rather than replacing it
     **/
    public void setGamesWon(int gamesWon) {
        this.gamesWon+= gamesWon;
    }

    /**
     getter for roundReached
     @return roundReached int
     **/
    public int getRoundReached() {
        return roundReached;
    }
    /**
     setter for roundReached
     @param roundReached int
     **/
    public void setRoundReached(int roundReached) {
        this.roundReached = roundReached;
    }
    /**
     getter for active
     @return active boolean
     **/
    public boolean isActive() {
        return active;
    }
    /**
     setter for active
     @param active boolean
     **/
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     getter for lastName
     @return lastName string
     **/
    public String getLastName() {
        return lastName;
    }
    /**
     getter for firstName
     @return firstName string
     **/
    public String getFirstName() {
        return firstName;
    }
    /**
     getter for playerID
     this is the unique identifier for the player which links the master playerList with the round playerList
     @return playerID int
     **/
    //public int getPlayerID() {        return playerID;    }
    public long getPlayerID() {
        return playerID;
    }
    /**
     getter for isDummy
     @return dummy boolean
     **/
    public boolean isDummy() {
        return dummy;
    }
    /**
     * constructor (overloaded) Polymorphism
     this is the constructor for a normal (real) player
     @param firstName string
     @param lastName string
     @param id  int
     **/
    //public Player(String firstName, String lastName, int id) {
public Player(String firstName, String lastName, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerID = id;
        this.dummy= false;
    }
    /**
     * constructor (overloaded)
     this is the constructor for a dummy player
     Only needs one parameter.
     The different method signature is what the program uses to decide which constructor to use
     *
     * @param id int*/
    public Player(long id) {

        this.firstName="Bye";
        this.lastName="Bye";
        this.playerID=id;
        this.dummy=true;
    }

}
