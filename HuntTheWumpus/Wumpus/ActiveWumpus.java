// Amrit Gill
// May 16, 2024
// Period 5
// Hunt the Wumpus - Active Wumpus Class

package Wumpus;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import Cave.Cave;
import Cave.Cell;

public class ActiveWumpus extends LazyWumpus {
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
        System.out.println(getLocation().getCellNum());
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
            move();
            this.rnd = getNewRnd();
            this.currentTurns = 0;
        }
        return wumpusPos.getCellNum();
    }

    public int teleport(){
        int rnd = (int) Math.random() * 20 + 1;
        if (rnd == 20){
            wumpusPos = cave.getRandomCell();
        }
        return wumpusPos.getCellNum();
    }

    public boolean loseTrivia(){
        if (playerCorrect >= 3){
            return true;
        } else {
            return false;
        }
    }

    public int triviaLose(){
        if (loseTrivia()){
            int rnd = (int) Math.random() * 2 + 1;
            moveRooms(rnd);
        }
        return wumpusPos.getCellNum();
    }
        public static void main(String args[]) throws FileNotFoundException{
        ActiveWumpus wumpus = new ActiveWumpus();
    }

}
