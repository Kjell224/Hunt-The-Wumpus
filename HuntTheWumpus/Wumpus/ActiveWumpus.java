// Amrit Gill
// May 16, 2024
// Period 5
// Hunt the Wumpus - Active Wumpus Class

package Wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import Cave.Cave;
import Cave.Cell;

public class ActiveWumpus {
    /////////////////
    // PROPERTIES
    /////////////////
    public int health = 1;
    public Cell wumpusPos;
    public int turns;
    public int playerCorrect;
    public Cave cave;
    public int rnd;
    public int currentTurns; // this is the amount of turns to keep track of every "cycle" of the 5-10 wumpus moves

    /////////////////
    // CONSTRUCTOR(S)
    /////////////////
    public ActiveWumpus(){
        this.rnd = getNewRnd();
    }

    /////////////////
    // METHODS
    /////////////////

    // This method gets the location of the wumpus
    public Cell getLocation(){
        wumpusPos = cave.getCell(cave.getWumpusCell());

        return wumpusPos;
    }

// Precondition: the String state must be "asleep" or "awake"
    public String state(String state){
        return state;
    }
    public int getNewRnd(){
        return (int) Math.random() * 6 + 5;
    }

    public int turnMove(){
        if (currentTurns == rnd){
            wumpusPos++;
            this.rnd = getNewRnd();
            this.currentTurns = 0;
        }

        return wumpusPos;
    }

    public int teleport(){
        double rnd = Math.random() * 20 + 1;
        int num = (int) rnd;
        if (turns % 1 == 0 && num == 5){
            double rnd2 = Math.random() * 10 + 1;
            int num2 = (int) rnd2;

            wumpusPos += num2;
        }

        return wumpusPos;
    }

    public boolean loseTrivia(){
        if (playerCorrect == 3){
            return true;
        } else {
            return false;
        }
    }

    public int triviaLose(){
        if (loseTrivia()){
            for (int i = 0; i < 3; i++){
                turns += i;
                currentTurns += i;
                wumpusPos += 2;
            }
        }

        return wumpusPos;
    }
}
