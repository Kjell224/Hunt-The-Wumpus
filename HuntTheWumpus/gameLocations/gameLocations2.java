// Aadhav and Arunim
// February 16, 2024
// Period 5
// Hunt the Wumpus - Game Locations Class

/*
 *  ABOUT THE OBJECT  *
 *  The gameLocations object tracks the locations of all objects in the current game. The tasks it performs are as follows:
 *      - Store and interact with the cave used for this game
 *      - Keep track of where the hazards are
 *      - Keep track of where the wumpus is
 *      - Keep track of where the player is
 *      - Control arrow shooting
 *      - Give any necessary warnings
 *      - Obtain hints to help the player
 *  MORE INFO  *
 *   Hazards (Wumpus is immune to all hazards):
 *      - Bottomless Pits
 *          - Two rooms have bottomless pits in them
 *          - You can save yourself by answering 2/3 trivia questions correctly
 *          - If you escape the pit, you will spawn back where you started the game
 *      - Super Bats
 *          - Two rooms have super bats in them
 *          - The bats will take you to some room at random
 *          - They fly away into another random room in the cave after taking you
 *      - No room has more than one hazard
 *      - The wumpus is not considered as a hazard
 *   Warnings:
 *      - You will be given a warning whenever a hazard or wumpus is in an adjacent room
 *          - Wumpus: I smell a Wumpus!
 *          - Bat: Bats Nearby.
 *          - Pit: I feel a draft.
 *   Arrows: 
 *      - Player starts game with 3 arrows
 *      - Shoot arrows into any adjacent room connected by a tunnel
 */

package HuntTheWumpus.gameLocations;
import HuntTheWumpus.Cave.*;
import HuntTheWumpus.gameControl.*;
import HuntTheWumpus.gameLocations.*;
import HuntTheWumpus.Player.*;
import HuntTheWumpus.Sound.*;
import HuntTheWumpus.Trivia.*;
import HuntTheWumpus.UI.*;
import HuntTheWumpus.Wumpus.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Cave.Cell;

import java.io.File;
import java.io.IOException;

public class gameLocations2 {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private static final Scanner user = new Scanner(System.in);
    //private Cave     Cave;
    private String   typeOfHazard;
    private ArrayList<String> hints;

    /** Positions **/
    private     int   wumpusPos;
    private     int playerPos;
    private     int[] hazardPos;
    private     int[] batsPos;
    private     int[] pitsPos;
    public      Cell[] cells = new Cell[30];
    ///////////////////////
    // Constructor(s)
    ///////////////////////
    public gameLocations2() throws FileNotFoundException{
        hints     = new ArrayList<String>();
        wumpusPos = 0;
        playerPos = 0;
        hazardPos = new int[2];
        batsPos   = new int[2];
        pitsPos   = new int[2];
        initializeCave();
        initializeHazards();
    }

    ///////////////////////
    // Methods
    //////////////////////

    // done
    /* 
    private void initializeCave(Cave c) throws FileNotFoundException{
        File file = new File("../HuntTheWumpus/Cave/WH1.csv");
        Scanner readFile = new Scanner(file);
        String[] data = readFile.nextLine().split(",");
        for(int i = 0; i < 30; i++){
            c.cellsArray[i] = new Cell(data[i]);
        }
        readFile.close();
    }
    */
    // done
    private void initializeTypes(Cave c) { 
        for(int i = 0; i < 30; i++){
            // Checks wumpus 
            if(c.cellsArray[i].getWumpus())
                wumpusPos = c.cellsArray[i].getCellNum();
            // Checks bats 
            else if(c.cellsArray[i].getType().equals("Bats")){
                if(batsPos[0] == 0) 
                    batsPos[0] = c.cellsArray[i].getCellNum();
                else 
                    batsPos[1] = c.cellsArray[i].getCellNum();
            }
            // Checks pit
            else if(c.cellsArray[i].getType().equals("Pit")){
                if(pitsPos[0] == 0)
                    pitsPos[0] = c.cellsArray[i].getCellNum();
                else 
                    pitsPos[1] = c.cellsArray[i].getCellNum();
            }
            //Checks player
            else if(c.cellsArray[i].getPlayer())
                playerPos = c.cellsArray[i].getCellNum();

        }
    }
    // so what should we do?
    /*  Preconditions
     *  newPos has to be adjacent to currentPos
     *  
     * 
     * 
    */

    /*
     * There are two situations where trivia is not needed:
     * First situtation is when the player is moving to a null cell
     * Second situtation is when the player is moving to a cell with a bat
        * When this happens a method setRandomBatsLocation() is called 
     */
   
    /*
     * setRandomBatsLocation is a method that is private to the gameLocations class
     * 
     */
    // done 
    // GameControl use this when the player enters a room with a bat in it
    private void setRandomBatsLocation(Cave c, int startingCellNum){
        // Both are random locations 
        int rndBatCell = (int) (Math.random() * 30); // (0-30) not a mistake
        int rndPlayerCell = (int) (Math.random() * 30); // (0-30) not a mistake
        // If the cell that the player is going to is null AND if the cell that the bat is going to is null OR is the player's old position
        if(c.cellsArray[rndPlayerCell].getType().equals("null") && (c.cellsArray[rndBatCell].getType().equals("null") || rndBatCell + 1 == playerPos)){
            // Move the player to the new random cell
            c.cellsArray[rndPlayerCell].setPlayer(true);
            // Move the bat to the new random cell
            c.cellsArray[rndBatCell].setType("Bat");
            // Set the old cell the player was in back to null
            c.cellsArray[playerPos - 1].setPlayer(false);
            // Update gL variable playerPos to match new location of player
            playerPos = rndPlayerCell + 1;
            
            if(c.cellsArray[batsPos[0] - 1].getCellNum == startingCellNum){
                c.cellsArray[batPos[0] - 1].setType("null");
                batsPos[0] = rndBatCell + 1;
            }
            else{
                c.cellsArray[batPos[1] - 1].setType("null");
                batsPos[1] = rndBatCell + 1;
            }
        }
        // if the random numbers do not fit the requirements
            // Then run the method again and get new random numbers that have a possibility of working.
        else setRandomBatsLocation();
    }

    public void setRandomWumpusLocaton(){
        int rnd = Math.random()
    }



}
