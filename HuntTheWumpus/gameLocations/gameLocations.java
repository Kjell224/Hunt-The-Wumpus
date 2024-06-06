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
  private ArrayList<String> hints;
  public int wumpusPos;
  public int playerPos;
  public int[] batsPos;
  public int[] pitsPos;


  ///////////////////////
  // Constructor(s)
  //////////////////////
  public gameLocations() throws FileNotFoundException{
    //Array List of Hints to make it easy to Modify
    hints     = new ArrayList<String>();
    //4 Different Hazards (Wumpus Exclusive)
    batsPos = new int[2];
    pitsPos = new int[2];
    initializeHints();
  }

  ///////////////////////
  // Methods
  //////////////////////

  //Initialize the ArrayList hints to include all answers from Questions.csv to give to the player
  public void initializeHints() throws FileNotFoundException{
    try{
      //Initialize File Questions.csv and a Scanner to read the file
      File data = new File("HuntTheWumpus/Trivia/Questions.csv");
      Scanner readFile = new Scanner(data);
      //Read through the file
      while(readFile.hasNextLine()){
        String currentLine = readFile.nextLine();
        //Array of the Line in Questions.csv
        String[] splitLine = currentLine.split(",");   
        hints.add(splitLine[2]); // Get the answer portion and fill up ArrayList hints
      }
      readFile.close(); // Close the Scanner
    } catch(IOException e){ // Catch Exceptions
        System.out.println("Error in writing file.");
        e.printStackTrace();
    }
  }

  public String giveHint() throws FileNotFoundException{ 
    //Random number from 0-length of Hints, to give to player
    int randNum = (int) (Math.random() * (hints.size()));
    return hints.remove(randNum); //Remove so hints don't get repeated
  }

  public String giveWarning(String warnType){
    //Return certain warnings depending on the String warnType (or cell type)
    if(warnType.equals("SuperBats")) 
      return "Bats Nearby.";
    else if(warnType.equals("Pit")) 
      return "I feel a draft."; 
    else if(warnType.equals("Wumpus")) 
      return "I smell a Wumpus!";
    return warnType;
  }



  public void findHazard(Cave cave, int pPos){
    int i = 0; //Index to add to hazardPos
    ArrayList<Integer> adjRooms = cave.getNeighbors(cave.getCell(pPos)); //Array List of Cells near Player
    //For each cell next to the player...
    for(Integer c : adjRooms){ 
      Cell curCell = cave.getCell(c); //Get the cell from the number
      //If the cellType is NOT an empty String
      if(curCell.getType() != ""){
        //Give a warning depending on the type
        System.out.println(giveWarning(curCell.getType()));
        //If the type is NOT a wumpus
        if(curCell.getType() != "Wumpus"){
          //Add the number of the cell it is in to HazardPos
          //If it isn't Wumpus it means it is Bats or Pits
          hazardPos[i] = c;
          i++;
        }
      }
    } 
  }

/*
  * There are two situations where trivia is not needed:
  * First situtation is when the player is moving to a null cell
  * Second situtation is when the player is moving to a cell with a bat
    * When this happens a method setRandomBatsLocation() is called 
  */
public void updateLocations(int currentPos, int newPos){
    cells[currentPos - 1 ].setType("null");
    cells[currentPos - 1].setPlayer(false);
    cells[newPos - 1].setPlayer(true);
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
        cells[playerPos].setPlayer(false);
        cells[batsPos[batNum]].setType("null");
        playerPos = rndPlayerCell + 1;
        batsPos[batNum] = rndBatCell + 1;
    }
    else setRandomBatsLocation(batNum);

}

  //* **** Getters & Setters **** *//
  public int[] getHazardsLocation(){ return hazardPos; }

  public int getWumpusLocation(){ return wumpusPos; }

  public void setWumpusLocation(int newLoc){ wumpusPos = newLoc; }

  public int getPlayerLocation(){ return playerPos; }

  public void setPlayerLocation(int newLoc){ playerPos = newLoc; }

      

}