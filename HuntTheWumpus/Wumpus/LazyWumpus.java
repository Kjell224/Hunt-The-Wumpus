// Amrit Gill
// May 16, 2024
// Period 5
// Hunt the Wumpus - Lazy Wumpus Class

package Wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import Cave.Cave;
import Cave.Cell;

public class LazyWumpus {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    public int arrows = 3;
    public int whealth = 1;
    public int phealth = 1;
    public Cell wumpusPos;
    public int playerCorrect;
    public String state;
    public Cave cave;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public LazyWumpus(){

    }

    ///////////////////////
    // Methods
    //////////////////////

    public Cell getLocation(){
        wumpusPos = cave.getCell(cave.getWumpusCell());
        return wumpusPos;
    }


    // Precondition: the String state must be "asleep", "awake", or "moving"
    public void setState(String state){
        this.state = state;
    }
    
    public String getState(){
        return state;
    }

    public void miss(){
        if ((arrows == 2 && whealth != 0) | (arrows == 1 && whealth != 0) | (arrows == 0 && whealth != 0)){
            wumpusPos = cave.getRandomAccesibleCell(wumpusPos);
            wumpusPos = cave.getRandomAccesibleCell(wumpusPos);
        }
    }
    public boolean loseTrivia(){
        if (playerCorrect == 3){
            return true;
        } else {
            return false;
        }
    }

    public Cell move(){
        if (loseTrivia()){
            int rnd = (int) Math.random() * 3 + 1;
            for (int i = 0; i < 3; i++){
                wumpusPos = cave.getRandomAccesibleCell(wumpusPos);
            }
        } else {
            return wumpusPos;
        }

        return wumpusPos;
    }

    public Cell stateMove(){
        if (state.equalsIgnoreCase("asleep")){
            return wumpusPos;
        } else if (state.equalsIgnoreCase("awake")){
            return wumpusPos;
        } else if (state.equalsIgnoreCase("moving")){
            wumpusPos = cave.getRandomAccesibleCell(wumpusPos);
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
