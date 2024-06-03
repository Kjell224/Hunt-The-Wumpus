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
    public     int[] playerPos;
    public     int[] hazardPos;
    public     int[] batsPos;
    public     int[] pitsPos;
    private    Cell[][] map = new Cell[5][6];;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public gameLocations2() throws FileNotFoundException{
        hints     = new ArrayList<String>();
        wumpusPos = 0;
        playerPos = new int[2];
        hazardPos = new int[2];
        batsPos   = new int[2];
        pitsPos   = new int[2];
        initializeHints();
        initializeCave();
        initializeHazards();
    }

    ///////////////////////
    // Methods
    //////////////////////

    public int[] findHazard(int[] pPos){
        typeOfHazard = "";
        giveWarning(typeOfHazard);
        return hazardPos;
    }

    public int[] getWumpusLocation(){ return wumpusPos; }

    public int[] getPlayerLocation(){ return playerPos; }

    public void initializeHints() throws FileNotFoundException{
        try{
            File data = new File("../HuntTheWumpus/Trivia/Questions.csv");
            Scanner readFile = new Scanner(data);
            while(readFile.hasNextLine()){
                hints.add(readFile.nextLine().split(",")[3]);
            }
        } catch(IOException e){
            System.out.println("Error in writing file.");
            e.printStackTrace();
        }
    }

    public String giveHint() throws FileNotFoundException{ 
        int randNum = (int) (Math.random() * (hints.size()));
        return hints.remove(randNum);
    }

    public String giveWarning(String warnType){
        if(warnType.equals("SuperBats")) 
            return "Bats Nearby.";
        else if(warnType.equals("Pit")) 
            return "I feel a draft."; 
        else if(warnType.equals("Wumpus")) 
            return "I smell a Wumpus!";
        return warnType;
    }

    public int shootArrow(int arrowCount){
        boolean isValid = false; 
        while(!isValid){
        //TODO: Create findAdjacentRooms() Method for Valid Moves 
            System.out.print("Where would you like to shoot?");
            String direction = user.next();
        }
        arrowCount--;  
        return arrowCount;
    }

    private ArrayList<int[]> findAdjacentRooms(){ 
        //Need Cave To Be Figured Out
        ArrayList<int[]> adjacentRooms = new ArrayList<int[]>();
        
        return adjacentRooms;
    }

    private void initializeCave() throws FileNotFoundException{
       // Cell[][] map = cave.getMap();
        
        File file = new File("../HuntTheWumpus/Cave/WH1.csv");
        Scanner readFile = new Scanner(file);
        String[] data = readFile.nextLine().split(",");
        int i = 0;
        for(int y = 0; y < map.length; y++){
          for(int x = 0; x < map[0].length; x++){
            map[y][x] = new Cell(data[i]);
            i++;
          }
        }
    }

    private void initializeHazards() {
        for(int r = 0; r < map.length; r++){
            for(int c = 0; c < map[0].length; c++){
                // Checks wumpus
                if(map[r][c].getType().equals("Wumpus"))
                    wumpusPos = map[r][c].getCellNum;
                // Checks bats
                else if(map[r][c].getType().equals("Bats")){
                    if(batsPos[0] == 0)
                        batsPos[0] = map[r][c].getCellNum;
                    else 
                        batsPos[1] = map[r][c].getCellNum;
                }
                // Checks pit
                else if(map[r][c].getType().equals("Pit")){
                    if(pitPos[0] == 0)
                        pitPos[0] = map[r][c].getCellNum;
                    else 
                        pitPos[1] = map[r][c].getCellNum;
                }
                //Checks player
                else if(map[r][c].getType().equals("Player"))
                    playerPos = map[r][c].getCellNum;
            }
        }
    }
    public void updateLocation(){
        
    }


}
