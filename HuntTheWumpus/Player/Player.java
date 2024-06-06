// Amrit Gill
// March 1, 2024
// Period 5
// Hunt the Wumpus - Player Class 
//On me amrit is selling our team.

package Player;

import Cave.Cell;
import java.util.Scanner;
import java.util.Random;

public class Player {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    public String name;
    private boolean triviaAnswer;
    private int gold = 0;
    public int health = 1;
    public String choice;
    public int arrows = 3;
    public int playerPos;
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

    public int setPlayerPos(int playerPos){
        return this.playerPos = playerPos;
    }

    public int getPlayerPos(){
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

    // This method makes the player move
    public Cell move(Cell c){
        return c;
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

    // This method allows the player to choose which spot they would like to move to
    public void chooseMove(){
        Scanner s = new Scanner(System.in);
        System.out.println("Where would you like to move? Up, Down, Left, or Right? or Would you like to Shoot?");
        choice = s.nextLine();
        if (choice.substring(0,1).equalsIgnoreCase("R")){
            move(choice.substring(0,1));
            playerPos++;
        } else if (choice.substring(0,1).equalsIgnoreCase("L")){
            move(choice.substring(0,1));
            playerPos--;
        } else if (choice.substring(0,1).equalsIgnoreCase("U")){
            move(choice.substring(0,1));
            playerPos++;
        } else if (choice.substring(0,1).equalsIgnoreCase("D")){
            move(choice.substring(0,1));
            playerPos--;
        } else if(choice.substring(0,1).equalsIgnoreCase("S")){    
            arrows--;
        }else {
            System.out.println();
            System.out.println("Sorry you did not enter one of the following options. Please try again.");
            System.out.println();
            turns++;
            chooseMove();
        }
    }

    public int turnTracker(){
        returns turns;
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