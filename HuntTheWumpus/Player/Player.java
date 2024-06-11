// Amrit Gill
// March 1, 2024
// Period 5
// Hunt the Wumpus - Player Class 

package Player;

import Cave.Cell;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import Cave.Cave;

public class Player {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    public String name; 
    public Cave cave;
    private boolean triviaAnswer;
    private int gold = 0;
    public int health = 1;
    public String choice;
    public int arrows = 3;
    public Cell playerPos;
    public int turns = 0;
    public int score;
    public int points;
    public int wumpushealth = 1;
    public ArrayList<Integer> explored;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public Player(Cave cave){
        this.cave = cave;
        this.playerPos = getPlayerPos();
        this.explored = new ArrayList<Integer>();
    }
    ///////////////////////
    // Methods
    //////////////////////

    // This method gets the player position
    public Cell getPlayerPos(){
        playerPos = cave.getCell(cave.getPlayerCell()); // cell
        return playerPos;
    }

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int hp){
        this.health = hp;
    }

    // gameLocation updates player position
    public void turn(int cellNum){
        turns++;
        if(gold < 100){
            gold++;
        }
        if(isNewCell(cellNum) == false){
            explored.add(cellNum);
        }
    }

    public boolean isNewCell(int num){
    for(int i = 0; i < explored.size(); i++){
        if(num == explored.get(i)){
            return true;
        }
    }
        return false;
    }

    // This method gives gold to the player
    public void playTrivia(boolean triviaAnswer){
        if(triviaAnswer == true){
            gold++;
        } else if (triviaAnswer == false){
            if (gold >= 1) {
                gold--;
            } else {
                health--;
            }
        }
    }


    // This method tracks how many turns the player has taken
    public int getTurns(){
        return turns;
    }

    // This method is for when the player attacks the wumpus
    public void attack(){
        arrows--;
    }

    // This method is for when the player takes damage
    public int takeDamage(){
        health--;
        return health;
    }

    // This method is for when the player dies
    public boolean death(){
        if (health == 0){
            return true;
        } else {
            return false;
        }
    }

    // This method is for when the player wins, it is give 50 points upon killing the wumpus
    public int win(){
        if (wumpushealth == 0){
            points += 50;
        }

        return points;
    }

    public ArrayList<Integer> getExplored(){
        return this.explored;
    }

}
