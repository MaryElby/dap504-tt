package com.company;
/**
 Player class.  Holds all the needed attributes of a player
 **/
public class Player {
//make all the attributes private so they can only be accessed with getters/setters/methods in this class
    private String firstName,lastName;
    private int playerID =0;   //need to be able to match players in master list to players in round list
    private boolean active=true; //every player starts as active
    private int roundReached=0; //every player starts at round 0
    private boolean dummy=false; //this is changed in the constructor for dummy players
    /**
     getter for roundReached
     **/
    public int getRoundReached() {
        return roundReached;
    }
    /**
     setter for roundReached
     **/
    public void setRoundReached(int roundReached) {
        this.roundReached = roundReached;
    }
    /**
     getter for active
     **/
    public boolean isActive() {
        return active;
    }
    /**
     setter for active
     **/
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     getter for lastName
     **/
    public String getLastName() {
        return lastName;
    }
    /**
     getter for firstName
     **/
    public String getFirstName() {
        return firstName;
    }
    /**
     getter for playerID
     **/
    public int getPlayerID() {
        return playerID;
    }
    /**
     getter for dummy
     **/
    public boolean isDummy() {
        return dummy;
    }
    /**
     * constructor
     this is the constructor for a normal (real) player
     **/

    public Player(String firstName, String lastName, int id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.playerID = id;
        this.dummy= false;
    }
    /**
     * constructor
     this is the constructor for a dummy player
     **/
    public Player(int id) {

        this.firstName="Bye";
        this.lastName="Bye";
        this.playerID=id;
        this.dummy=true;
    }



}
