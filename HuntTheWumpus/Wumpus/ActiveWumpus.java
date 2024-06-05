// Amrit Gill
// May 16, 2024
// Period 5
// Hunt the Wumpus - Active Wumpus Class

package Wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ActiveWumpus {
    /////////////////
    // PROPERTIES
    /////////////////
    public int health = 1;
    public int wumpusPos;
    public int turns;

    /////////////////
    // CONSTRUCTOR(S)
    /////////////////
    public ActiveWumpus(){
        
    }

    /////////////////
    // METHODS
    /////////////////
    public int getLocation(){
        return wumpusPos;
    }

    public int setLocation(int wumpusPos){
        return this.wumpusPos = wumpusPos;
    }

// Precondition: the String state must be "asleep" or "awake"
    public String state(String state){
        return state;
    }

    public int turnMove(){
        
    }
}
