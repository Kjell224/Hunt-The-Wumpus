// Amrit Gill
// March 1, 2024
// Period 5
// Hunt the Wumpus - Player Class 
//On me amrit is selling our team.

package Player;

import Cave.Cell;
import java.util.Scanner;
import java.util.Random;
import Cave.Cave;

public class Player {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    public String name; // This variable is for the name of the Player
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

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public Player(){
    
    }
    ///////////////////////
    // Methods
    //////////////////////

    public Cell getPlayerPos(){
        playerPos = cave.getCell(cave.getPlayerCell()); // cell
        return playerPos;
    }

    // This method gets the name of the Player
    public String getName(){
        Scanner s = new Scanner(System.in);
        System.out.println("What is your name?");
        name = s.nextLine();
        System.out.println("Hello " + name + "! Welcome to Hunt the Wumpus!");

        s.close();

        return name;
    }

    // This method gives gold to the player
    public void giveGold(){
        if(triviaAnswer == true){
            gold = gold++;
        } else if (triviaAnswer == false){
            if (gold == 0){
                health = health--;
            }
            else if (gold >= 1){
                gold = gold--;
            }
        }
    }


    public int turnTracker(){
        return turns;
    }

    public int highScore(){
        score = 100 - turns + gold + (5 * arrows) + points;

        return score;
    }


    public void attack(Cell c){
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

    public int win(){
        if (wumpushealth == 0){
            points += 50;
        }

        return points;
    }
}