package com.company;

public class Player {

    public String firstName,lastName;
    public int playerID =0;
    public boolean active=true;
    public int roundReached=0;
    public boolean dummy=false;

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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
