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
        if (turns % 5 == 0 | turns % 6 == 0 | turns % 7 == 0 | turns % 8 == 0 | turns % 9 == 0 | turns % 10 == 0){
            wumpusPos++;
        }

        return wumpusPos;
    }

    public int teleport(){
        
        if (turns % 1 == 0){

        }
    }
}
