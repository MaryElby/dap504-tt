package com.company;

public class Player {
//make all the attributes private so they can only be accessed with getters/setters/methods in this class
    private String firstName,lastName;
    private int playerID =0;   //need to be able to match players in master list to players in round list
    private boolean active=true; //every player starts as active
    private int roundReached=0; //every player starts at round 0
    private boolean dummy=false; //this is changed in the constructor for dummy players

    public int getRoundReached() {
        return roundReached;
    }

    public void setRoundReached(int roundReached) {
        this.roundReached = roundReached;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public Player(String firstName, String lastName, int id) {
        //this is the constructor for a normal (real) player
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerID = id;
        this.dummy= false;
    }

    public Player(int id) {
        //this is the constructor for a dummy player that acts as a bye for the real player
        this.firstName="Bye";
        this.lastName="Bye";
        this.playerID=id;
        this.dummy=true;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean isDummy() {
        return dummy;
    }

}
