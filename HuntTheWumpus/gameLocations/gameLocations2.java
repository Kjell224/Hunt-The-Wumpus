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
    public     int   wumpusPos;
    public     int playerPos;
    public     int[] hazardPos;
    public     int[] batsPos;
    public     int[] pitsPos;
    private    Cell[] cells = new Cell[30];
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
        initialize();
    }

    ///////////////////////
    // Methods
    //////////////////////
    private void initializeCave() throws FileNotFoundException{
        File file = new File("../HuntTheWumpus/Cave/WH1.csv");
        Scanner readFile = new Scanner(file);
        String[] data = readFile.nextLine().split(",");
        for(int i = 0; i < 30; i++){
            cells[i] = new Cell(data[i]);
        }
        readFile.close();
    }

    private void initialize() {
        for(int r = 0; r < 30; r++){
            // Checks wumpus
            if(cells[r].getWumpus())
                wumpusPos = cells[r].getCellNum();
            // Checks bats
            else if(cells[r].getType().equals("Bats")){
                if(batsPos[0] == 0)
                    batsPos[0] = cells[r].getCellNum();
                else 
                    batsPos[1] = cells[r].getCellNum();
            }
            // Checks pit
            else if(cells[r].getType().equals("Pit")){
                if(pitsPos[0] == 0)
                    pitsPos[0] = cells[r].getCellNum();
                else 
                    pitsPos[1] = cells[r].getCellNum();
            }
            //Checks player
            else if(cells[r].getPlayer())
                playerPos = cells[r].getCellNum();
        
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
    public void updateLocations(int currentPos, int newPos){
        cells[currentPos - 1 ].setType("null");
        cells[newPos - 1].setType("Player");
        playerPos = newPos;
        /* 
        if(cells[newPos-1].getType().equals("Bats")){
            if(cells[newPos-1].getCellNum() == batsPos[0]) setRandomBatsLocation(0);
            else setRandomBatsLocation(1);
        }
        cells[currentPos - 1 ].setType("null");
        cells[newPos - 1].setType("Player");
        playerPos = newPos;
        
        else if(cells[newPos-1].getType().equals("Pit")){

        }
        else if(cells[newPos - 1].getType().equals("Wumpus")){

        }
       */
    
    }
    /*
     * setRandomBatsLocation is a method that is private to the gameLocations class
     * 
     */
    public void setRandomBatsLocation(int batNum){
        int rndBatCell = (int) (Math.random() * 30);
        int rndPlayerCell = (int) (Math.random() * 30);
        if(cells[rndPlayerCell].getType().equals("null") && (cells[rndBatCell].getType().equals("null") || rndBatCell + 1 == playerPos)){
            cells[rndPlayerCell].setPlayer(true);
            cells[rndBatCell].setType("Bat");
            playerPos = rndPlayerCell + 1;
            batsPos[batNum] = rndBatCell + 1;
        }
        else setRandomBatsLocation(batNum);

    }



}
