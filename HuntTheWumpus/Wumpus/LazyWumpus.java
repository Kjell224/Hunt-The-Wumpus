// Amrit Gill
// May 16, 2024
// Period 5
// Hunt the Wumpus - Lazy Wumpus Class

package Wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LazyWumpus {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    public int arrows = 3;
    public int whealth = 1;
    public int phealth = 1;
    private int numQ;
    public int wumpusPos;
    public int playerCorrect;
    public String state;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public LazyWumpus(){

    }

    ///////////////////////
    // Methods
    //////////////////////

    public int getLocation(){
        return wumpusPos;
    }

    public int setLocation(int wumpusPos){
        return this.wumpusPos = wumpusPos;
    }

    // Precondition: the String state must be "asleep", "awake", or "moving"
    public String state(String state){
        return this.state = state;
    }

    public void miss(){
        if ((arrows == 2 && whealth != 0) | (arrows == 1 && whealth != 0) | (arrows == 0 && whealth != 0)){
            wumpusPos += 2;
        }
    }
    public boolean loseTrivia(){
        if (playerCorrect == 3){
            return true;
        } else {
            return false;
        }
    }

    public int move(){
        if (loseTrivia()){
            wumpusPos += 1 | wumpusPos + 2 | wumpusPos + 3;
        } else {
            return wumpusPos;
        }

        return wumpusPos;
    }

    public int stateMove(){
        if (state.equalsIgnoreCase("asleep")){
            return wumpusPos;
        } else if (state.equalsIgnoreCase("awake")){
            return wumpusPos;
        } else if (state.equalsIgnoreCase("moving")){
            wumpusPos++;
        }

        return wumpusPos;
    }

    public boolean WumpusWins(){
        if (whealth != 0 && playerDeath() == true){
            return true;
        } else {
            return false;
        }
    }

    public boolean WumpusLoses(){
        if (whealth == 0){
            return true;
        } else{
            return false;
        }
    }

    // This method is for when the player dies
    public boolean playerDeath(){
        if (phealth == 0){
            return true;
        } else {
            return false;
        }
    }
}