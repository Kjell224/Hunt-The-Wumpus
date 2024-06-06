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
    public int playerCorrect;

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
        double rnd = Math.random() * 20 + 1
        int num = (int) rnd;
        if (turns % 1 == 0 && num = 5){
            double rnd2 = Math.random * 10 + 1;
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

                wumpusPos += 2;
            }
        }
    }
}
