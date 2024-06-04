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
    public int[] wumpusPos;
    public int playerCorrect;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public LazyWumpus(){
        wumpusPos = new int[2];
    }

    ///////////////////////
    // Methods
    //////////////////////

    public int[] getLocation(){
        return wumpusPos;
    }

    // Precondition: the String state must be "asleep", "awake", or "moving"
    public String state(String state){
        return state;
    }
/* 
    public void miss(){
        if ((arrows == 2 && whealth != 0) | (arrows == 1 && whealth != 0) | (arrows == 0 && whealth != 0)){
            wumpusPos[0] += 2 | wumpusPos[1] + 2;
        }
    }
*/
    public boolean loseTrivia(){
        if (playerCorrect == 3){
            return true;
        } else {
            return false;
        }
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