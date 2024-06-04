// Amrit Gill
// March 1, 2024
// Period 5
// Hunt the Wumpus - Player Class

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
    public String move(String c){
        return c;
    }

    // This method gives gold to the player
    public void giveGold(){
        if(triviaAnswer == true){
            gold = gold + 50;
        } else if (triviaAnswer == false){
            if (gold == 0){
                health = health - 1;
            }
            else if (gold >= 50){
                gold = gold - 50;
            }
        }
    }
/* 
    // This method allows the player to choose which spot they would like to move to
    public void chooseMove(){
        Scanner s = new Scanner(System.in);
        System.out.println("Where would you like to move? Up, Down, Left, or Right? or Would you like to Shoot?");
        choice = s.nextLine();
        if (choice.substring(0,1).equalsIgnoreCase("R")){
            move(choice.substring(0,1));
            playerPos[0]++;
        } else if (choice.substring(0,1).equalsIgnoreCase("L")){
            move(choice.substring(0,1));
            playerPos[0]--;
        } else if (choice.substring(0,1).equalsIgnoreCase("U")){
            move(choice.substring(0,1));
            playerPos[1]++;
        } else if (choice.substring(0,1).equalsIgnoreCase("D")){
            move(choice.substring(0,1));
            playerPos[1]--;
        } else if(choice.substring(0,1).equalsIgnoreCase("S")){    
            arrows--;
        }else {
            System.out.println();
            System.out.println("Sorry you did not enter one of the following options. Please try again.");
            System.out.println();
            chooseMove();
        }
    }
*/
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
}