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

package gameLocations;
import Cave.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class gameLocations {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private static final Scanner console = new Scanner(System.in);
    private String   typeOfHazard;
    private ArrayList<String> hints;


    /** Positions **/
    public     int wumpusPos;
    public     int playerPos;
    public   int[] hazardPos;


    ///////////////////////
    // Constructor(s)
    //////////////////////
    public gameLocations() throws FileNotFoundException{
        hints     = new ArrayList<String>();
        hazardPos = new int[4];
        initializeHints();
        //initializeCave();
    }

    ///////////////////////
    // Methods
    //////////////////////

    public void findHazard(int[] pPos, Cave cave){
        Cell[][] map = cave.getMap();
        ArrayList<Cell> adjRooms = cave.allAdjacents(map[pPos[0]][pPos[1]]);
        for(Cell c : adjRooms){
            if(c.getType() != ""){
                System.out.println(giveWarning(c.getType()));
            }
        } 
    }

    public int getWumpusLocation(){ return wumpusPos; }

    public void setWumpusLocation(int newLoc){ wumpusPos = newLoc; }

    public void setPlayerLocation(int newLoc){ playerPos = newLoc; }

    public int getPlayerLocation(){ return playerPos; }

    public void initializeHints() throws FileNotFoundException{
        try{
            File data = new File("HuntTheWumpus/Trivia/Questions.csv");
            Scanner readFile = new Scanner(data);
            while(readFile.hasNextLine()){
                String currentLine = readFile.nextLine();
                String[] splitLine = currentLine.split(",");    
                hints.add(splitLine[3]);
            }
            readFile.close();
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

    public void initializeCave(Cave cave) {
        Cell[][] map = cave.getMap();
        int wumpusPlace = (int) (Math.random() * 31);
        int bat1Place = (int) (Math.random() * 31);
        int bat2Place = (int) (Math.random() * 31);
        int pit1Place = (int) (Math.random() * 31);
        int pit2Place = (int) (Math.random() * 31);
        int count = 0;
        for (int i = 0; i < map.length; i++) {
          for (int j = 0; j < map[0].length; j++) {
            count += 1;
            if (count == wumpusPlace) {
              map[i][j].setType("Wumpus");
            } else if (count == bat1Place) {
              while (bat1Place == wumpusPlace) {
                bat1Place = (int) (Math.random() * 31);
              }
              map[i][j].setType("SuperBats");
            } else if (count == bat2Place) {
              while (bat2Place == wumpusPlace) {
                bat2Place = (int) (Math.random() * 31);
              }
              map[i][j].setType("SuperBats");
            } else if (count == pit1Place) {
              while (pit1Place == wumpusPlace) {
                pit1Place = (int) (Math.random() * 31);
              }
              map[i][j].setType("Pit");
            } else if (count == pit2Place) {
              while (pit2Place == wumpusPlace) {
                pit2Place = (int) (Math.random() * 31);
              }
              map[i][j].setType("Pit");
            }
          }
        }
      }

}