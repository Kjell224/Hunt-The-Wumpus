/////////////////////
//Import
////////////////////
package Cave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cave {
  /////////////
  //Properties
  /////////////
  private File wallMap; // csv file
  private Scanner reader; //readers that reads csv map file
  private String map; // the full string map from the csv file
  public Cell[] cellsArray = new Cell[30]; // ***************** ALL CELLS IN ACENDING ORDER! ************
  /////////////////
  //Constuctor(s)
  ///////////////
  public Cave() throws FileNotFoundException {
    this.wallMap = new File("HuntTheWumpus\\Cave\\Maps.csv"); // grab pathname
    this.reader = new Scanner(wallMap); // read the file
    this.map = getMapFromCsv(); // csv information (rotates the maps)
    createCells(this.map); // from information generates the cells info
    System.out.println("Player cell : " + getPlayerCell());
    System.out.println("Wumpus cell: " + getWumpusCell());
  }
  ///////////
  //Methods
  //////////
  private String getMapFromCsv() throws FileNotFoundException {
    String map = reader.nextLine();
    String restOfLines = "";
    while(reader.hasNextLine()){
      restOfLines += reader.nextLine() + "\n";
    }
    try {
      FileWriter fw = new FileWriter(wallMap);
      fw.write(restOfLines + map);
      fw.close();
    } catch(Exception e) {
      System.out.println("writing back into Walls.csv failed " +  e);
    }
    return map;
  }
  
  private void createCells(String info) throws FileNotFoundException {
    String[] cellInfo = info.split(",");
    for(int i = 0; i < 30; i++){
      Cell cell = new Cell(cellInfo[i]);
      cellsArray[i] = cell;
    }
  } 

  // ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓ RETURNS CELL ARRAYLIST ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓

  // RETURNS ARRAYLIST OF CELLS GIVEN A CELL
  public ArrayList<Cell> getCellNeighbors(Cell c){
    ArrayList<Cell> neighbors = new ArrayList<Cell>();
    ArrayList<Integer> cellValues = c.getAccesibleNeighbors();
    for(int i = 0; i < cellValues.size(); i++){
      neighbors.add(getCellsArray()[cellValues.get(i) - 1]);
    }
    return neighbors;
  }
  // RETURNS ARRAYLIST OF CELLS GIVEN A INT
  public ArrayList<Cell> getCellNeighbors(int num){
    ArrayList<Cell> neighbors = new ArrayList<Cell>();
    Cell c = getCell(num);
    ArrayList<Integer> cellValues = c.getAccesibleNeighbors();
    for(int i = 0; i < cellValues.size(); i++){
      neighbors.add(getCellsArray()[cellValues.get(i) - 1]);
    }
    return neighbors;
  }
  

  // ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓ RETURNS INTEGER ARRAYLIST ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓
  
  public ArrayList<Integer> getNeighbors(int num){
    return getCell(num).getAccesibleNeighbors();
  }

  public ArrayList<Integer> getNeighbors(Cell cell){
    return cell.getAccesibleNeighbors();
  }
  

  // CHECKS IF POSSIBLE TO MOVE TO A CELL GIVEN A CELLS DESTINATION
  public boolean isValid(Cell current, Cell target){
     ArrayList<Cell> neighbors = getCellNeighbors(current);
    for(int i = 0; i < neighbors.size(); i++){
      if( neighbors.get(i).getCellNum() == target.getCellNum() ){
        return true;
      }
    }
    return false;
  }

  public int getPlayerCell(){
    for(int i = 1; i <= cellsArray.length; i++){
      if(getCell(i).getPlayer()){
        return getCell(i).getCellNum();
      }
    }
    return -1;
  }

  public int getWumpusCell(){
    for(int i = 1; i <= cellsArray.length; i++){
      if(getCell(i).getWumpus()){
        return getCell(i).getCellNum();
      }
    }
    return -1;
  }

  /* 
  public void printCellsNeighbors(Cell c){
    ArrayList<Integer> nums = c.getAccesibleNeighbors();
    for(int i = 0; i < nums.size(); i++){
      System.out.println(getCellsArray()[nums.get(i)].toString());
    }
  }
  */
  
  public Cell[] getCellsArray(){
    return cellsArray;
  }
  
  //this will return ACTUAL cell num (not the offset index)
  public Cell getCell(int num){
    return cellsArray[num - 1];
  }
  
  public String getMap(){
    return this.map;
  }

}